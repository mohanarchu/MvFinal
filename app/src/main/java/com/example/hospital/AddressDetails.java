package com.example.hospital;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.hospital.Order.OrderPresenter;
import com.example.hospital.Order.OrderView;
import com.example.hospital.ProductDB.ProductDb;
import com.example.hospital.ProductDB.proLocalArray;
import com.example.hospital.Shop.Sopping;
import com.example.hospital.cart.OldOrders.OldPojo;
import com.example.hospital.cart.orders.UtilityClass;
import com.example.hospital.payment.MerchantCheckoutActivity;
import com.example.hospital.payment.MyPayments;
import com.example.hospital.profile.Shared;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.moshi.Json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressDetails extends AppCompatActivity implements OrderView {


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
    String amount,type;

    ProgressDialog progressDialog;

    ArrayList<JsonObject> orderArrays = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_details);
        ButterKnife.bind(this);
        productDb = new ProductDb(getApplicationContext());
        orderPresenter = new OrderPresenter(getApplicationContext(), this);


        Intent intent = getIntent();
        if (intent != null){
            amount = intent.getStringExtra("amount");
            type = intent.getStringExtra("type");

            // showToast(amount);
            //  Log.i("TAg","My orders"+UtilityClass.getInstance().getList()[0].getQuantity());

        }

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        makePayment = findViewById(R.id.makePayment);
        makePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().isEmpty() || dEmail.getText().toString().isEmpty() || dPhone.getText().toString().isEmpty() ||
                   dState.getText().toString().isEmpty() || dStreet.getText().toString().isEmpty() || dCity.getText().toString().isEmpty() ||
                    dCountry.getText().toString().isEmpty() || dPincode.getText().toString().isEmpty() ){
                        showToast("Fill all details");
                }else {
                    if (!isValidEmail(dEmail.getText().toString())){
                        showToast("Enter valid email");
                    } else if(!isValid(dPhone.getText().toString())){
                        showToast("Enter valid mobile number");

                    }else {
                         postOrder();


                    }
                }

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
    public static boolean isValid(String s)
    {

        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    void postOrder() {
        {
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
            // orderPresenter.makeOrder(jsonObject1);
            orderArrays.add(jsonObject1);
            postProducts();


        }
    }

    void postProducts() {
        JsonArray jsonElements = new JsonArray();

        if (type.equals("1")){
            for (int i = 0; i < productDb.getAllProducts().size(); i++) {
                proLocalArray pa = productDb.getAllProducts().get(i);
                JsonObject jsonObject = new JsonObject();
              //  jsonObject.addProperty("OrderId", id);
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
        }  else {
            for (int i = 0; i <UtilityClass.getInstance().getList().length; i++){
                OldPojo.Result or= UtilityClass.getInstance().getList()[i];
                JsonObject jsonObject = new JsonObject();
              //  jsonObject.addProperty("OrderId", id);
                jsonObject.addProperty("ProductId", or.getProductId()[0]);
                jsonObject.addProperty("CategoryName", or.getCategoryName());
                jsonObject.addProperty("ProductName", or.getProductName());
                jsonObject.addProperty("ProductCode", or.getProductCode());
                jsonObject.addProperty("ProductPrice", or.getProductPrice());
                jsonObject.addProperty("ProductSize", or.getProductSize());
                jsonObject.addProperty("Quantity", or.getQuantity());
                jsonObject.addProperty("Color", or.getColor());
                jsonObject.addProperty("Amount", or.getAmount());
                jsonObject.addProperty("ProductdiscountPrice", "0.0");
                jsonElements.add(jsonObject);
            }
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("data", jsonElements);
        jsonObject.addProperty("table", "OrderDetails");
        jsonObject.addProperty("multipleInsert", true);
        orderArrays.add(jsonObject);
        Shared.setOrderArrays(orderArrays);
        Intent intent1 = new Intent(AddressDetails.this, MyPayments.class);
        intent1.putExtra("name",name.getText().toString());
        intent1.putExtra("email",dEmail.getText().toString());
        intent1.putExtra("mobileNumber",dPhone.getText().toString());
        intent1.putExtra("amount",amount);
        startActivity(intent1);
        finish();
       //   orderPresenter.postProducts(jsonObject);

    }

    @Override
    public void showProgress() {

        progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();

    }

    @Override
    public void hideProgress() {

        progressDialog.dismiss();
    }


    @Override
    public void showToast(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void sucess(String id) {
        postProducts();
    }

    @Override
    public void placed() {
        startActivity(new Intent(getApplicationContext(), ShoppingMain.class));
        finish();
    }
}
