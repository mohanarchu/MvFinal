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
import com.example.hospital.R;
import com.example.hospital.Shop.ProductPojo;
import com.example.hospital.cart.ViewProduct;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.viewHolder> {


        List<ProductPojo.Result> productPojo;
        Context context;
        public MyRecyclerViewAdapter(List<ProductPojo.Result> productPojo,Context context){
            this.productPojo = productPojo;
            this.context = context;
        }

        // inflates the cell layout from xml when needed
        @Override
        @NonNull
        public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context). inflate(R.layout.item_designs, parent, false);
            return new viewHolder(view);
        }

        // binds the data to the TextView in each cell
        @Override
        public void onBindViewHolder(@NonNull viewHolder holder,final int i) {
            ProductPojo.Result po = productPojo.get(i);

         //   holder.  product.setImageResource(po.getProductName());
            holder. name.setText(po.getProductName());
            holder. price.setText(po.getPrice());

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.error)
                    .error(R.mipmap.ic_launcher);

            if (po.getThumbnailImage() != null){
                Glide.with(context).load(
                        "http://shop.mvdiabetes.com/Upload/Products/ThumbnailImage/"+
                                po.getProductId()+"/"+
                                po.getThumbnailImage()).into(holder.product);


            } else {
               holder.product.setImageResource(R.drawable.error);
            }

            holder.itemView .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ViewProduct.class);
                    intent.putExtra("key",po.getProductId());
                    intent.putExtra("image","http://shop.mvdiabetes.com/Upload/Products/ThumbnailImage/"+po.getProductId()+"/"+
                            po.getThumbnailImage());
                    intent.putExtra("limage","http://shop.mvdiabetes.com/Upload/Products/LargeImage/"+po.getProductId()+"/"+
                            po.getLargeimage());
                    context. startActivity(intent);


                }
            });
        }

        // total number of cells
        @Override
        public int getItemCount() {
            return productPojo == null ? 0 : productPojo.size();
        }


        public void add(ProductPojo.Result response) {
             productPojo.add(response) ;
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

    public class  viewHolder extends RecyclerView.ViewHolder{

        ImageView product;
        TextView name,price;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            product = itemView.findViewById(R.id.priductImage);
            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
        }

    }

}
