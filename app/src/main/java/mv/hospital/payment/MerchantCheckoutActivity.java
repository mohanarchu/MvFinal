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

import com.google.gson.JsonObject;
import com.hospital.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mv.hospital.AddressDetails;
import mv.hospital.Order.OrderPresenter;
import mv.hospital.Order.OrderView;
import mv.hospital.ProductDB.ProductDb;
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