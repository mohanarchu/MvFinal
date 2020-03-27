package com.example.hospital.payment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hospital.Order.OrderPresenter;
import com.example.hospital.Order.OrderView;
import com.example.hospital.ProductDB.ProductDb;
import com.example.hospital.R;
import com.example.hospital.ShoppingMain;
import com.example.hospital.profile.Shared;
import com.google.gson.JsonObject;

public class MerchantCheckoutActivity extends AppCompatActivity  implements OrderView {


    ProgressDialog progressDialog;
    TextView postParamsTextView;
    ProductDb productDb;
    OrderPresenter orderPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_checkout);
        postParamsTextView   = (TextView) findViewById(R.id.text_view_post_params);
        productDb = new ProductDb(getApplicationContext());
        orderPresenter = new OrderPresenter(getApplicationContext(),this);
        //post data received by this activity contains all params posted to webview in transaction request.
        String postData = getIntent().getStringExtra("postData");
        orderPresenter.makeOrder(Shared.getOrderArrays().get(0));
       // postParamsTextView.setText("Merchant's post data : "+postData);

    }

    @Override
    public void showProgress() {
        progressDialog =new ProgressDialog(MerchantCheckoutActivity.this);
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
        JsonObject jsonObject = Shared.getOrderArrays().get(1);
        jsonObject.addProperty("OrderId",id);
        orderPresenter.postProducts(jsonObject);

    }

    @Override
    public void placed() {
        postParamsTextView.setText("Thanks for ordering ,\n Your order has been placed successfully");
        productDb.deleteTables();
        startActivity(new Intent(getApplicationContext(), ShoppingMain.class));
        finish();
    }
}