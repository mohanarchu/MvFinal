package com.example.hospital.Shop.reviews;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.R;
import com.example.hospital.base.BaseActivity;
import com.example.hospital.cart.pojo.ProductCommomPojo;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewActivity extends BaseActivity implements ReviewPresenter.ReviewView {

    @BindView(R.id.all_reviews)
    RecyclerView allReviews;
    ProgressDialog progressDialog;
    ReviewPresenter reviewPresenter;

    @Override
    protected int layoutRes() {
        return R.layout.activity_review;
    }

    @Override
    protected void onViewBound() {
        reviewPresenter =new ReviewPresenter(getApplicationContext(),this);
        Intent intent = getIntent();
        if (intent != null) {
            reviewPresenter.getList("0", "200",true,intent.getStringExtra("id"));
        }
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(ReviewActivity.this);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
      if (progressDialog != null) {
          progressDialog.dismiss();
      }
    }

    @Override
    public void showToast(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showReviews(ProductCommomPojo.ProductReview[] review) {
        ReviiewAdapter reviiewAdapter =  new ReviiewAdapter();
        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        allReviews.setLayoutManager(linearLayoutManager);
        allReviews.setAdapter(reviiewAdapter);
        reviiewAdapter.setReview(review);
        reviiewAdapter.notifyDataSetChanged();
    }
}
