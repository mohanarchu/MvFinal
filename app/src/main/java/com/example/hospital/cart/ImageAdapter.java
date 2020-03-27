package com.example.hospital.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hospital.R;
import com.example.hospital.Shop.Sopping;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {


    Context context;
    ImegesPojo.Result[] sizePojos;
    public ImageAdapter(Context context,ImegesPojo.Result[] sizePojos){
        this.context = context;
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

        Glide.with(context).load(
                "http://shop.mvdiabetes.com/Upload/Products/LargeImage/"+sizePojos[position]
                        .getProductId()+"/"+
                        sizePojos[position].getThumbimage()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return sizePojos.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
        }
    }
}
