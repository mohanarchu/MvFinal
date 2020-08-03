package mv.hospital.payment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hospital.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mv.hospital.AddressDetails;
import mv.hospital.Order.OrderPresenter;
import mv.hospital.Order.OrderView;
import mv.hospital.ProductDB.ProductDb;
import mv.hospital.ProductDB.proLocalArray;
import mv.hospital.ShoppingMain;
import mv.hospital.profile.Shared;

public class MerchantCheckoutActivity extends AppCompatActivity implements OrderView {


    ProgressDialog progressDialog;
    TextView postParamsTextView;
    ProductDb productDb;
    OrderPresenter orderPresenter;
    @BindView(R.id.transaction_image)
    ImageView transactionImage;
    @BindView(R.id.transationStatus)
    TextView transationStatus;
    @BindView(R.id.transationId)
    TextView transationId;
    @BindView(R.id.transationAmount)
    TextView transationAmount;
    @BindView(R.id.activity_merchant_checkout)
    RelativeLayout activityMerchantCheckout;
    @BindView(R.id.successImage)
    ImageView successImage;
    @BindView(R.id.ok_button)
    CardView okButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_checkout);
        ButterKnife.bind(this);
        productDb = new ProductDb(getApplicationContext());
        orderPresenter = new OrderPresenter(getApplicationContext(), this);
        Intent intent = getIntent();
        if (intent != null) {
            transationId.setText(intent.getStringExtra("id"));
            transationStatus.setText(intent.getStringExtra("status"));
            if (intent.getStringExtra("status").equals("success")) {
                successImage.setVisibility(View.VISIBLE);
            } else {
                transactionImage.setVisibility(View.VISIBLE);
            }
            transationAmount.setText(intent.getStringExtra("amount"));
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("table","OrderMaster");
            jsonObject.addProperty("refernce","OrderId");
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("TxnId",transationId.getText().toString());
            jsonObject1.addProperty("TxnStatus",transationStatus.getText().toString());
            jsonObject1.addProperty("TxnMsg",transationStatus.getText().toString());
            jsonObject.add("data",jsonObject1);
            orderPresenter.updateOrder(jsonObject, AddressDetails.orderIds);
        }
        sendEmail();
    }

    void sendEmail() {
        String date  = new SimpleDateFormat("dd-MM-yyyy HH:mm",
                Locale.getDefault()).format(new Date());
        int total = 0;
        if (getIntent().getStringExtra("status").equals("success")) {
            JsonObject jsonObject = new JsonObject();
            JsonArray jsonElements = new JsonArray();
            for (int i=0;i<productDb.getAllProducts().size();i++) {
                JsonObject productJson = new JsonObject();
                proLocalArray array = productDb.getAllProducts().get(i);
                productJson.addProperty("code",array.getCode());
                productJson.addProperty("name",array.getName());
                productJson.addProperty("size",array.getSize());
                productJson.addProperty("color",array.getColor());
                productJson.addProperty("quantity",array.getQuantity());
                productJson.addProperty("prize",currencyFormat(array.getPrice()));
                productJson.addProperty("amount",currencyFormat(array.getTotalAmount()));
                total = total + Integer.parseInt(array.getTotalAmount());
                jsonElements.add(productJson);
            }
            jsonObject.add("products",jsonElements);
            jsonObject.addProperty("email",Shared.email(getApplicationContext()));
            jsonObject.addProperty("orderNo",AddressDetails.orderIds);
            jsonObject.addProperty("orderDate",date);
            jsonObject.addProperty("txnId",getIntent().getStringExtra("id"));
            jsonObject .addProperty("sippingCost",currencyFormat("0"));
            jsonObject .addProperty("grandTotal",currencyFormat(String.valueOf(total)));
            jsonObject.addProperty("subTotal",currencyFormat(String.valueOf(total)));
            jsonObject.addProperty("txnStatus","success");
            jsonObject.addProperty("address",AddressDetails.shipAddress);
            jsonObject.addProperty("name",AddressDetails.shipName);
            jsonObject.addProperty("city",AddressDetails.shipCity);
            jsonObject.addProperty("zipCode",AddressDetails.shipZipCode);
            jsonObject.addProperty("state",AddressDetails.shipState);
            jsonObject.addProperty("contact",AddressDetails.shipContact);
            jsonObject.addProperty("country","India");
            jsonObject.addProperty("email", AddressDetails.shipEmail);
            jsonObject.addProperty("status",true);
            orderPresenter .sendOrderMail(jsonObject);
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("orderNo",AddressDetails.orderIds);
            jsonObject.addProperty("orderDate",date);
            jsonObject.addProperty("txnStatus","Failed");
            jsonObject.addProperty("status",false);
            jsonObject.addProperty("email",Shared.email(getApplicationContext()));
            orderPresenter .sendOrderMail(jsonObject);
        }
    }


    @Override
    public void showProgress() {
//        progressDialog = new ProgressDialog(MerchantCheckoutActivity.this);
//        progressDialog.setMessage("Please wait..");
//        progressDialog.show();
    }

    @Override
    public void hideProgress() {
//        progressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void sucess(String id) {

    }

    @Override
    public void placed() {
        productDb.deleteTables();
    }


    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return "\u20B9"+" "+formatter.format(Double.parseDouble(amount));
    }
    @Override
    public void onBackPressed() {

    }

    @OnClick(R.id.ok_button)
    public void onViewClicked() {
        productDb.deleteTables();
        startActivity(new Intent(getApplicationContext(), ShoppingMain.class));
        finish();
    }
}