package mv.hospital.cart.OldOrders;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import mv.hospital.Networks.ApiUrl;
import com.hospital.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {

        OldPojo.Result[] results;

          OrderInterFace orderInterFace;

        public RecyclerViewAdapter(OldPojo.Result[] results) {
            this.results = results;
        }

        @NonNull
        @Override
        public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.old_order_design,
                    parent, false);
            return new viewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull viewHolder holder, int position) {
            OldPojo.Result or = results[position];
            holder.oldProductName .setText(or.getProductName());
            holder.oldsizeSpinner.setText("Size : "+ or.getProductSize());

            holder.order_rating.setNumStars(5);
            if (or.getRating() != null) {
                holder.order_rating.setRating(Float.parseFloat(or.getRating()));
            } else {
                holder.order_rating.setRating(Float.parseFloat("0.0"));
            }
            holder.order_rating.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
                @Override
                public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                    if (or.getRating() != null) {
                        orderInterFace.ratingChanged(rating,or.getDescription(),or.getReviewId(),"",
                                false);
                    } else {
                        orderInterFace.ratingChanged(rating,"",
                              "",or.getProductId(),
                                true);

                    }

                }
            });

            if(or.getColor().equals("")){
                holder.oldcolorSpinner.setVisibility(View.GONE);
            }else {
                 holder.oldcolorSpinner.setText("Color : " +or.getColor());
            }
            holder.oldtotalAmount.setText(or.getAmount());
            holder.oldtotalCount.setText("Quantity : "+or.getQuantity());
            if (or.getProductImage() != null){
                Glide.with(holder.itemView.getContext()).load(
                        ApiUrl.LIVE_IMAGE_URL+"/ThumbnailImage/"+or.getProductId()+"/"+
                                or.getProductImage()).into(holder.oldImage);

            } else{
                holder.oldImage.setImageResource(R.drawable.error);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        orderInterFace.itemClicked(results[position]);
                }
            });
        }
        @Override
        public int getItemCount() {
            return results.length;
        }

    public void setInterface(OrderInterFace orderInterFace) {
            this.orderInterFace = orderInterFace;
    }

    public interface OrderInterFace {

        void itemClicked(OldPojo.Result or);
        void ratingChanged(float rating, String description,String reviewId,String productid,boolean newRating);
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
        @BindView(R.id.order_rating)
        MaterialRatingBar order_rating;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    }



