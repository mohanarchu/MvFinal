package mv.hospital;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mv.hospital.Order.OrderPresenter;
import mv.hospital.Order.OrderView;
import mv.hospital.ProductDB.ProductDb;
import mv.hospital.ProductDB.proLocalArray;

import com.google.gson.Gson;
import com.hospital.R;

import mv.hospital.Shop.address.AddressActivity;
import mv.hospital.Shop.address.AddressAdapter;
import mv.hospital.Shop.address.AddressPojo;
import mv.hospital.Shop.address.AddressPresenter;
import mv.hospital.Shop.address.StatePojo;
import mv.hospital.cart.OldOrders.OldPojo;
import mv.hospital.cart.orders.UtilityClass;
import mv.hospital.payment.MerchantCheckoutActivity;
import mv.hospital.payment.MyPayments;
import mv.hospital.profile.Shared;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressDetails extends AppCompatActivity implements OrderView , AddressPresenter.AddressView{


    ProductDb productDb;
    OrderPresenter orderPresenter;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.dPhone)
    EditText dPhone;
    @BindView(R.id.dEmail)
    EditText dEmail;
    @BindView(R.id.dStreet)
    EditText dStreet;
    @BindView(R.id.dState)
    EditText dState;
    @BindView(R.id.dCity)
    EditText dCity;
    @BindView(R.id.dCountry)
    EditText dCountry;
    @BindView(R.id.dPincode)
    EditText dPincode;
    @BindView(R.id.makePayment)
    LinearLayout makePayment;
    @BindView(R.id.sameAs)
    CheckBox sameAs;
    @BindView(R.id.address_recycler1)
    RecyclerView addressRecycler;
    @BindView(R.id.addNewAddress)
    CardView addnewAddress;
    String amount,type;
    AddressPresenter addressPresenter;
    ProgressDialog progressDialog;
    AddressAdapter addressAdapter;
    ArrayList<JsonObject> orderArrays = new ArrayList<>();
    public static String orderIds = "";
    public static String shipName = "",shipAddress="",shipCity="",shipZipCode="",shipState="",sipCountry="india",shipContact="",shipEmail="";

    boolean isFirstAddress= false;
    AddressPojo.Result[] addressResult;
    int oldPosition = 0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_details);
        ButterKnife.bind(this);
        setRecycler();
        productDb = new ProductDb(getApplicationContext());
        orderPresenter = new OrderPresenter(getApplicationContext(), this);
        addressPresenter = new AddressPresenter(getApplicationContext(),this);
        addressPresenter.getList(Shared.id(getApplicationContext()));

        Intent intent = getIntent();
        if (intent != null){
            amount = intent.getStringExtra("amount");
            type = intent.getStringExtra("type");
        }

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        makePayment = findViewById(R.id.makePayment);
        makePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty() ||   dPhone.getText().toString().isEmpty() ||
                   dState.getText().toString().isEmpty() || dStreet.getText().toString().isEmpty() || dCity.getText().toString().isEmpty() ||
                    dCountry.getText().toString().isEmpty() || dPincode.getText().toString().isEmpty() ){
                        showToast("Fill all details");
                }else {
                    if(!isValid(dPhone.getText().toString())) {
                        showToast("Enter valid mobile number");
                    } else {

                        shipName = name.getText().toString();
                        shipEmail = dEmail.getText().toString();
                        shipAddress =  dStreet.getText().toString();
                        shipCity =  dCity.getText().toString();
                        shipContact =  dPhone.getText().toString();
                        shipZipCode = dPincode.getText().toString();
                        shipState =   dState.getText().toString();
                        postOrder();
                    }
                }
            }
        });
        addnewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), AddressActivity.class);
                intent1.putExtra("isFirst",isFirstAddress);
                startActivityForResult(intent1,100);
            }
        });
        sameAs.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                name.setText(Shared.name(getApplicationContext()));
                dEmail.setText(Shared.email(getApplicationContext()));
                dPhone.setText(Shared.phone(getApplicationContext()));
                dStreet.setText(Shared.address(getApplicationContext()));
                dState.setText(Shared.state(getApplicationContext()));
                dCity.setText(Shared.city(getApplicationContext()));
                dCountry.setText("India");
                dPincode.setText(Shared.pincode(getApplicationContext()));
            }else {
                name.setText("");
                dEmail.setText("");
                dPincode.setText("");
                dPhone.setText("");
                dStreet.setText("");
                dState.setText("");
                dCountry.setText("");
                dCity.setText("");
            }
        });


    }
    void setRecycler() {
        addressAdapter = new AddressAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        addressRecycler.setLayoutManager(linearLayoutManager);
        addressRecycler.setAdapter(addressAdapter);

        addressAdapter.setListener(new AddressAdapter.Listener() {
            @Override
            public void selectedAddress(AddressPojo.Result result,int pos) {

                if (addressResult != null) {
                    addressResult[oldPosition].setPrimary("0");
                    addressResult[pos].setPrimary("1");
                    addressAdapter.setList(addressResult);
                    addressAdapter.notifyDataSetChanged();
                    oldPosition = pos;
                    name.setText(result.getName());
                    dEmail.setText("");
                    dPhone.setText(result.getPhone());
                    dStreet.setText(result.getAddress());
                    dState.setText(result.getState());
                    dCity.setText(result.getCity());
                    dCountry.setText("India");
                    dPincode.setText(result.getPostalcode());
                }
            }

            @Override
            public void setPrimary(String id) {

            }
        });
    }


    public static boolean isValid(String s)
    {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    void postOrder() { {
            JsonObject jsonObject = new JsonObject();
            String date  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                    Locale.getDefault()).format(new Date());
            jsonObject.addProperty("OrderDate", date);
            jsonObject.addProperty("OrderAmount", amount);
            jsonObject.addProperty("UserId", Shared.id(getApplicationContext()));
            jsonObject.addProperty("Name", name.getText().toString());
            if (!Shared.address(getApplicationContext()).equals("")){
                jsonObject.addProperty("Address", Shared.address(getApplicationContext()));
                jsonObject.addProperty("City", Shared.city(getApplicationContext()));
                jsonObject.addProperty("State", Shared.state(getApplicationContext()));
                jsonObject.addProperty("Country", "India");
                jsonObject.addProperty("ZipCode", Shared.pincode(getApplicationContext()));
            }else {
                jsonObject.addProperty("Address", dStreet.getText().toString());
                jsonObject.addProperty("City", dCity.getText().toString());
                jsonObject.addProperty("State", dState.getText().toString());
                jsonObject.addProperty("Country", dCountry.getText().toString());
                jsonObject.addProperty("ZipCode", dPincode.getText().toString());
            }

            jsonObject.addProperty("ContactNo", Shared.phone(getApplicationContext()));
            jsonObject.addProperty("EmailId", Shared.email(getApplicationContext()));
            jsonObject.addProperty("ShipName", name.getText().toString());
            jsonObject.addProperty("ShipAddress", dStreet.getText().toString());
            jsonObject.addProperty("ShipCity", dCity.getText().toString());
            jsonObject.addProperty("ShipState", dState.getText().toString());
            jsonObject.addProperty("ShipCountry", dCountry.getText().toString());
            jsonObject.addProperty("ShipZipCode", dPincode.getText().toString());
            jsonObject.addProperty("ShipContactNo", dPhone.getText().toString());
            jsonObject.addProperty("ShipEmailId", dEmail.getText().toString());
            jsonObject.addProperty("TxnStatus", "NULL");
            jsonObject.addProperty("TxnId", "NULL");
            jsonObject.addProperty("TxnMsg", "NULL");
            jsonObject.addProperty("ShippingCost", "");
            jsonObject.addProperty("SubTotal", amount);
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.add("data", jsonObject);
            jsonObject1.addProperty("multipleInsert", false);
            jsonObject1.addProperty("table", "OrderMaster");
            orderPresenter.makeOrder(jsonObject1);

        }
    }

    void postProducts(String orderid) {
        JsonArray jsonElements = new JsonArray();
        if (type.equals("1")){
            for (int i = 0; i < productDb.getAllProducts().size(); i++) {
                proLocalArray pa = productDb.getAllProducts().get(i);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("OrderId", orderid);
                jsonObject.addProperty("ProductId", pa.getId());
                jsonObject.addProperty("CategoryName", pa.getCode());
                jsonObject.addProperty("ProductName", pa.getName());
                jsonObject.addProperty("ProductCode", pa.getCode());
                jsonObject.addProperty("ProductPrice", pa.getPrice());
                jsonObject.addProperty("ProductSize", pa.getSize());
                jsonObject.addProperty("Quantity", pa.getQuantity());
                jsonObject.addProperty("Color", pa.getColor());
                jsonObject.addProperty("Amount", pa.getTotalAmount());
                jsonObject.addProperty("ProductdiscountPrice", "0.0");
                jsonElements.add(jsonObject);
            }
        } else {
            for (int i = 0; i < UtilityClass.getInstance().getList().length; i++){
                OldPojo.Result or= UtilityClass.getInstance().getList()[i];
                JsonObject jsonObject = new JsonObject();
              //  jsonObject.addProperty("OrderId", id);
//                jsonObject.addProperty("ProductId", or.getProductId()[0]);
//                jsonObject.addProperty("CategoryName", or.getCategoryName());
//                jsonObject.addProperty("ProductName", or.getProductName());
//                jsonObject.addProperty("ProductCode", or.getProductCode());
//                jsonObject.addProperty("ProductPrice", or.getProductPrice());
//                jsonObject.addProperty("ProductSize", or.getProductSize());
//                jsonObject.addProperty("Quantity", or.getQuantity());
//                jsonObject.addProperty("Color", or.getColor());
//                jsonObject.addProperty("Amount", or.getAmount());
//                jsonObject.addProperty("ProductdiscountPrice", "0.0");
//                jsonElements.add(jsonObject);
            }
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("data", jsonElements);
        jsonObject.addProperty("table", "OrderDetails");
        jsonObject.addProperty("multipleInsert", true);
        orderArrays.add(jsonObject);
        orderPresenter.postProducts(jsonObject);
    }

    void makePayment() {
        Intent intent1 = new Intent(AddressDetails.this, MyPayments.class);
        intent1.putExtra("name",name.getText().toString());
        intent1.putExtra("email",dEmail.getText().toString());
        intent1.putExtra("mobileNumber",dPhone.getText().toString());
        intent1.putExtra("amount",amount);
        startActivity(intent1);
        finish();
    }

    @Override
    public void showProgress() {
        progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();
    }

    @Override
    public void showResult(AddressPojo.Result[] results) {
        if (results.length == 0) {
            isFirstAddress = true;
        }
        addressResult = results;
        addressAdapter.setList(results);
        addressAdapter.notifyDataSetChanged();
        for (int i=0 ;i<results.length;i++) {
            AddressPojo.Result re = results[i];
            if (re.getPrimary().equals("1")) {
                oldPosition = i;
                name.setText(re.getName());
                dEmail.setText("");
                dPhone.setText(re.getPhone());
                dStreet.setText(re.getAddress());
                dState.setText(re.getState());
                dCity.setText(re.getCity());
                dCountry.setText("India");
                dPincode.setText(re.getPostalcode());
            }
        }
    }

    @Override
    public void showStates(StatePojo.Result[] results) {
        
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showDetails(String message) {
        if (message.equals("Success")) {
            addressPresenter.getList(Shared.id(getApplicationContext()));
        }
    }


    @Override
    public void showToast(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void sucess(String id) {
        orderIds = id;
        postProducts(id);
    }

    @Override
    public void placed() {
//        startActivity(new Intent(getApplicationContext(), ShoppingMain.class));
//        finish();
        makePayment();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            addressPresenter.getList(Shared.id(getApplicationContext()));
        }
    }
}
