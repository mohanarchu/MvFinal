package com.example.hospital.Shop.reviews;

import android.content.Context;

import com.example.hospital.Networks.NetworkingUtils;
import com.example.hospital.ViewModel;
import com.example.hospital.cart.pojo.ProductCommomPojo;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ReviewPresenter {
    ReviewView reviewView;
    Context context;

    public ReviewPresenter(Context context,ReviewView reviewView) {
        this.reviewView =reviewView;
        this.context = context;
    }

    public void getList( String skipCount, String limitCount, boolean first,String id) {

        reviewView.showProgress();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",id);
        jsonObject.addProperty("table","ProductsReview");
        jsonObject.addProperty("sortProperty","ReviewDate");
        jsonObject.addProperty("sortType","DESC");
        jsonObject.addProperty("skipCount",skipCount);
        jsonObject.addProperty("limitCount",limitCount);
        jsonObject.addProperty("reference","ProductId");
        NetworkingUtils.getUserApiInstance().getList(jsonObject).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ReviewPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ReviewPojo productPojo) {

                if (productPojo.getStatus().equals("true")) {
                    reviewView.showReviews(productPojo.getResult());
                    reviewView.hideProgress();
                } else  {
                    reviewView.showToast("try again");
                }

            }

            @Override
            public void onError(Throwable e) {
                // productPresent.hideProgress();
                // Log.i("TAg","ProductDetails"+e.toString());
                reviewView.hideProgress();
            }

            @Override
            public void onComplete() {

            }
        });
    }



    public interface ReviewView extends ViewModel {
        @Override
        void showProgress();

        @Override
        void hideProgress();

        @Override
        void showToast(String message);


        void showReviews(ProductCommomPojo.ProductReview[] review);
    }

}
