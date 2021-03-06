package mv.hospital.cart.OldOrders;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mv.hospital.AddressDetails;
import mv.hospital.Networks.ApiUrl;
import com.hospital.R;
import mv.hospital.base.BaseActivity;
import mv.hospital.cart.ViewProduct;
import mv.hospital.cart.orders.UtilityClass;
import mv.hospital.profile.Shared;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class OldOrders extends BaseActivity implements OldOrdersView {

    @BindView(R.id.oldOrderRecycler)
    RecyclerView oldOrderRecycler;
    @BindView(R.id.reorderItems)
    LinearLayout reorderItems;
    ProgressDialog progressDialog;
    OldOrderPresenter oldOrderPresenter;
    OldPojo.Result[] result;
    MaterialRatingBar postratingBar;
    EditText descriptionReview;
    CardView postRating;
    TextView productnameReview;
    Dialog otpDialog;
    String reviewId = "0",productId = "0";
    boolean newRating = false;
    @Override
    protected void onViewBound() {
        oldOrderPresenter = new OldOrderPresenter(getApplicationContext(), this);
        Intent intent = getIntent();

        if (intent != null) {
            oldOrderPresenter.getDetails(intent.getStringExtra("key"));
        }
        reorderItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result != null){
                    Intent  intent = new Intent(OldOrders.this, AddressDetails.class);
                    UtilityClass.getInstance().setList(result);
                    intent.putExtra("type","2");
                    intent.putExtra("amount",getIntent().getStringExtra("amount"));
                    startActivity(intent);
                }
            }
        });
        otpDialog = new Dialog(this);
        otpDialog.setContentView(R.layout.rating_design);
        otpDialog.getWindow().setTitleColor(getResources().getColor(R.color.colorPrimary));
        postratingBar = otpDialog. findViewById(R.id.post_rating_tab);
        descriptionReview = otpDialog. findViewById(R.id.review_descriptions);
        postRating  = otpDialog. findViewById(R.id.post_rating);
        productnameReview = otpDialog.findViewById(R.id.productname_review);
        postRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!newRating) {
                    String date  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                            Locale.getDefault()).format(new Date());
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("ReviewId",reviewId );
                    jsonObject.addProperty("Description",descriptionReview.getText().toString());
                    jsonObject.addProperty("Rating", postratingBar.getRating());
                    jsonObject.addProperty("ReviewDate", date);
                    oldOrderPresenter.updateReview(jsonObject);
                } else {
                    String date  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                            Locale.getDefault()).format(new Date());
                    JsonObject json= new JsonObject();
                    json.addProperty("table","ProductsReview");
                    json.addProperty("multipleInsert",false);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("ProductId",productId);
                    jsonObject.addProperty("Description",descriptionReview.getText().toString());
                    jsonObject.addProperty("Rating", postratingBar.getRating());
                    jsonObject.addProperty("ReviewDate", date);
                    jsonObject.addProperty("UserId", Shared.id(getApplicationContext()));
                    json.add("data",jsonObject);
                    oldOrderPresenter.createReview(json);

                }
            }
        });
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_old_orders;
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("Please wait");
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
        if (otpDialog.isShowing()) {
            otpDialog.dismiss();
            oldOrderPresenter.getDetails(getIntent().getStringExtra("key"));
        }
    }

    @Override
    public void showToast(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDetails(OldPojo.Result[] results) {

        result =results;
        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        oldOrderRecycler.setLayoutManager(linearLayoutManager);
        if (results.length != 0){
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(results);
            oldOrderRecycler.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.setInterface(new RecyclerViewAdapter.OrderInterFace() {
                @Override
                public void itemClicked(OldPojo.Result or) {
                    Intent intent = new Intent(OldOrders.this, ViewProduct.class);
                    intent.putExtra("key",or.getProductId());
                    intent.putExtra("image", ApiUrl.LIVE_IMAGE_URL+"/ThumbnailImage/"+or.getProductId()+"/"+
                            or.getProductImage());
                    intent.putExtra("limage",ApiUrl.LIVE_IMAGE_URL+"/LargeImage/"+or.getProductId()+"/"+
                            or.getProductImage());
                    startActivity(intent);
                }
                @Override
                public void ratingChanged(float rating, String description, String revId, String productid, boolean newRatings) {
                     newRating = newRatings;
                     reviewId = revId;
                     productId =  productid;
                     postratingBar.setRating(rating);
                     descriptionReview.setText(description);
                     otpDialog.show();
                }
            });
        }
    }
}

