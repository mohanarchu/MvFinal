package com.example.hospital.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecsAdapter extends RecyclerView.Adapter<SpecsAdapter.SpecsHolder> {



    ArrayList<SpecsArray> specsArrays;
    @NonNull
    @Override
    public SpecsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SpecsHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.specs_viw, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SpecsHolder holder, int position) {
            holder.specsImage.setImageDrawable(specsArrays.get(position).getDrawable());
            holder.specsText.setText(specsArrays.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    void setArray(ArrayList<SpecsArray> specsArrays) {
        this.specsArrays = specsArrays;
        notifyDataSetChanged();
    }

    class SpecsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.specsImage)
        ImageView specsImage;
        @BindView(R.id.specsText)
        TextView specsText;

        public SpecsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
