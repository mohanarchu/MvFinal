package mv.hospital.Shop.reviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesgood.views.JustifiedTextView;
import com.hospital.R;
import mv.hospital.Utils.CommonUtils;
import mv.hospital.cart.pojo.ProductCommomPojo;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class ReviiewAdapter extends RecyclerView.Adapter<ReviiewAdapter.ReviewHolder> {


    ProductCommomPojo.ProductReview[] productReviews;


    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.review_design, parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        holder.initHolder(productReviews[position]);
    }

    public void setReview(ProductCommomPojo.ProductReview[] productReviews) {
        this.productReviews = productReviews;
    }

    @Override
    public int getItemCount() {
        return productReviews != null ? productReviews.length : 0;
    }

    class ReviewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.review_person_name)
        TextView reviewPersonName;
        @BindView(R.id.review_rating)
        MaterialRatingBar reviewRating;
        @BindView(R.id.reviewd_date)
        TextView reviewdDate;
        @BindView(R.id.review_description)
        JustifiedTextView reviewDescription;
        @BindView(R.id.reply_description)
        JustifiedTextView replyDescription;
        @BindView(R.id.reply_date)
        TextView replyDate;
        @BindView(R.id.review_reply_layout)
        LinearLayout replyLayout;
        ReviewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
              reviewRating.setClickable(false);
              reviewRating.setOnDragListener(null);
              reviewRating.setFocusable(false);
              reviewRating.setIsIndicator(true);

        }

        void initHolder(ProductCommomPojo.ProductReview review) {
            reviewRating.setOnRatingChangeListener((ratingBar, rating) ->
                    reviewRating.setRating(Float.parseFloat(review.getRating())));
            if (review.getUserName() == null || review.getUserName().equals("")) {
                reviewPersonName.setText("User not found");
            } else {
                reviewPersonName.setText(review.getUserName());
            }
            if (review.getReply() != null) {
                replyLayout.setVisibility(View.VISIBLE);
                replyDescription.setText(review.getReply());
                replyDate.setText(CommonUtils.getdate(review.getReplyDate()));
            } else {
                replyLayout.setVisibility(View.GONE);
            }
            reviewRating.setRating(Float.parseFloat(review.getRating()));
            reviewdDate.setText(CommonUtils.getdate(review.getReviewDate()));
            reviewDescription.setText(review.getDescription());
        }
    }

}
