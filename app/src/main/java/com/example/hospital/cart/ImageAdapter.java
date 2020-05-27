package com.example.hospital.cart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hospital.Networks.ApiUrl;
import com.example.hospital.R;
import com.example.hospital.Shop.Sopping;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {


    Context context;
    List<String> sizePojos;
    String productId;
    public ImageAdapter(Context context, List<String> sizePojos , String productId){
        this.context = context;
        this.productId = productId;
        this.sizePojos = sizePojos;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context). inflate(R.layout.image_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).asBitmap() .load(
                ApiUrl.LIVE_IMAGE_URL+"/LargeImage/"+productId +"/"+
                        sizePojos.get(position)) .into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        return sizePojos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
        }
    }
}
