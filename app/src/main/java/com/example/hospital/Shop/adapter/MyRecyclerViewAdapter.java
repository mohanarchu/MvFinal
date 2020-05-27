package com.example.hospital.Shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hospital.Networks.ApiUrl;
import com.example.hospital.R;
import com.example.hospital.Shop.ProductPojo;
import com.example.hospital.cart.ViewProduct;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.viewHolder> {



    List<ProductPojo.Result> productPojo;
    Context context;


    public MyRecyclerViewAdapter(List<ProductPojo.Result> productPojo, Context context) {
        this.productPojo = productPojo;
        this.context = context;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_designs, parent, false);
        return new viewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int i) {
        ProductPojo.Result po = productPojo.get(i);
        //   holder.  product.setImageResource(po.getProductName());
        holder.productName.setText(po.getProductName());
        holder.productPrice.setText(po.getPrice());
        if (po.getRating() != null)
        holder.totalReview.setRating(Float.parseFloat(po.getRating()));
        else
            holder.totalReview.setRating(Float.parseFloat("0.0"));
        holder.totalReview.setIsIndicator(true);
        holder.totalReviewCount.setText(po.getTotalReview());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.error)
                .error(R.mipmap.ic_launcher);
        if (po.getThumbnailImage() != null) {
            Glide.with(context).load(
                      ApiUrl.LIVE_IMAGE_URL+"/ThumbnailImage/" +
                            po.getProductId() + "/" +
                            po.getThumbnailImage()).into(holder.priductImage);
        } else {
            holder.priductImage.setImageResource(R.drawable.error);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewProduct.class);
                intent.putExtra("key", po.getProductId());
                intent.putExtra("image",ApiUrl.LIVE_IMAGE_URL+"/ThumbnailImage/"+ po.getProductId() + "/" +
                        po.getThumbnailImage());
                intent.putExtra("limage", ApiUrl.LIVE_IMAGE_URL+"/LargeImage/" + po.getProductId() + "/" +
                        po.getLargeimage());
                context.startActivity(intent);


            }
        });
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return productPojo == null ? 0 : productPojo.size();
    }


    public void add(ProductPojo.Result response) {
        productPojo.add(response);
        notifyItemInserted(productPojo.size() - 1);
    }

    public void addAll(List<ProductPojo.Result> postItems) {
        for (ProductPojo.Result response : postItems) {
            add(response);
        }
    }


    private void remove(ProductPojo.Result postItems) {
        int position = productPojo.indexOf(postItems);
        if (position > -1) {
            productPojo.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addLoading() {

        add(new ProductPojo.Result());
    }

    public void removeLoading() {

        int position = productPojo.size() - 1;
        ProductPojo.Result item = getItem(position);
        if (item != null) {
            productPojo.remove(position);
            notifyItemRemoved(position);
        }

    }

    ProductPojo.Result getItem(int position) {
        return productPojo.get(position);
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.priductImage)
        ImageView priductImage;
        @BindView(R.id.productName)
        TextView productName;
        @BindView(R.id.productPrice)
        TextView productPrice;
        @BindView(R.id.total_review)
        MaterialRatingBar totalReview;
        @BindView(R.id.total_review_count)
        TextView totalReviewCount;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

    }

}
