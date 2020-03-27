package com.example.hospital.cart.OldOrders;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hospital.AddressDetails;
import com.example.hospital.R;
import com.example.hospital.Shop.Sopping;
import com.example.hospital.cart.ViewProduct;
import com.example.hospital.cart.orders.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OldOrders extends AppCompatActivity implements OldOrdersView {

    @BindView(R.id.oldOrderRecycler)
    RecyclerView oldOrderRecycler;
    @BindView(R.id.reorderItems)
    LinearLayout reorderItems;
    ProgressDialog progressDialog;

    OldOrderPresenter oldOrderPresenter;
    OldPojo.Result[] result;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_orders);
        ButterKnife.bind(this);
        oldOrderPresenter = new OldOrderPresenter(getApplicationContext(), this);
        Intent intent = getIntent();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        oldOrderRecycler.setLayoutManager(layoutManager);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
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
    }

    @Override
    public void showToast(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDetails(OldPojo.Result[] results) {
        List<OldPojo.Result> list = new ArrayList<>(Arrays.asList(results));
//        Set<OldPojo.Result> set = new HashSet<>(list);
//        list.clear();
//        list.addAll(set);
        @SuppressLint({"NewApi", "LocalSuppress"}) List<OldPojo.Result> listWithoutDuplicates =
                list.stream().distinct().collect(Collectors.toList());

        Log.i("TAG","Old order results"+ listWithoutDuplicates);
        result =results;
        if (results.length != 0){
            oldOrderRecycler.setAdapter(new RecyclerViewAdapter(results));
        }
    }
    public static <T> ArrayList<OldPojo.Result> removeDuplicates( List<OldPojo.Result> list)
    {

        // Create a new ArrayList
        ArrayList<OldPojo.Result> newList = new ArrayList<OldPojo.Result>();

        // Traverse through the first list
        for (OldPojo.Result element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }
    public class RecyclerViewAdapter extends RecyclerView.Adapter<viewHolder> {

        OldPojo.Result[] results;


        public RecyclerViewAdapter(OldPojo.Result[] results) {
            this.results = results;
        }

        @NonNull
        @Override
        public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.old_order_design, parent, false);
            return new viewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull viewHolder holder, int position) {
            OldPojo.Result or = results[position];
            holder.oldProductName .setText(or.getProductName());
            holder.oldsizeSpinner.setText(or.getProductSize());
            holder.oldProductCode.setText(or.getProductCode());
            if(or.getColor().equals("")){
                holder.oldcolorSpinner.setVisibility(View.GONE);
            }else {
                holder.oldcolorSpinner.setText(or.getColor());
            }
            holder.oldtotalAmount.setText(or.getAmount());
            holder.oldtotalCount.setText("Quantity : "+or.getQuantity());
            if (or.getThumbimage() != null){
                Glide.with(getApplicationContext()).load(
                        "http://shop.mvdiabetes.com/Upload/Products/ThumbnailImage/"+or.getProductId()[0]+"/"+
                                or.getThumbimage()).into(holder.oldImage);


            }else{
                holder.oldImage.setImageResource(R.drawable.error);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(OldOrders.this, ViewProduct.class);
                    intent.putExtra("key",or.getProductId()[0]);
                    intent.putExtra("image","http://shop.mvdiabetes.com/Upload/Products/ThumbnailImage/"+or.getProductId()[0]+"/"+
                            or.getThumbimage());
                    intent.putExtra("limage","http://shop.mvdiabetes.com/Upload/Products/LargeImage/"+or.getProductId()[0]+"/"+
                            or.getLargeimage());
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return results.length;
        }
    }

    class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.old_image)
        ImageView oldImage;
        @BindView(R.id.oldProductName)
        TextView oldProductName;

        @BindView(R.id.oldProductCode)
        TextView oldProductCode;
        @BindView(R.id.oldsizeSpinner)
        TextView oldsizeSpinner;
        @BindView(R.id.oldcolorSpinner)
        TextView oldcolorSpinner;
        @BindView(R.id.oldtotalCount)
        TextView oldtotalCount;
        @BindView(R.id.oldtotalAmount)
        TextView oldtotalAmount;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

