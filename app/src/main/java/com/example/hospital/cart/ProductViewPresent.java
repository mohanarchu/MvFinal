package com.example.hospital.cart;

import android.content.Context;
import android.util.Log;

import com.example.hospital.Networks.NetworkingUtils;
import com.example.hospital.Shop.ProductPojo;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductViewPresent {

    Context context;
    ProductPresent productPresent;

    public ProductViewPresent(Context context,ProductPresent productPresent){
        this.productPresent = productPresent;
        this.context = context;
    }

    public void getDetails(String id,String refrence, String table ){

        productPresent.showProgress();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Id",id);
        jsonObject.addProperty("refernce",refrence);
        jsonObject.addProperty("table",table);


        NetworkingUtils.getUserApiInstance().getProductDetails(jsonObject).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DetailsPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(DetailsPojo productPojo) {

                productPresent.showDetails(productPojo.getResult());

            }

            @Override
            public void onError(Throwable e) {
               // productPresent.hideProgress();
                // Log.i("TAg","ProductDetails"+e.toString());
            }

            @Override
            public void onComplete() {

            }
        });




    }
   void getColor(String id){
       JsonObject jsonObjects = new JsonObject();
       jsonObjects.addProperty("Id",id);
       jsonObjects.addProperty("refernce","ProductId");
       jsonObjects.addProperty("table","ProductColors");
    //   Log.i("TAg","ProductDetails"+jsonObjects);
       NetworkingUtils.getUserApiInstance().getColor(jsonObjects).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ColorPojo>() {
           @Override
           public void onSubscribe(Disposable d) {

           }

           @Override
           public void onNext(ColorPojo colorPojo) {
               Log.i("TAg","ProductDetails"+colorPojo.getStatus());
               productPresent.showColor(colorPojo.getResult());
           }

           @Override
           public void onError(Throwable e) {
             //  productPresent.hideProgress();
               Log.i("TAg","ProductDetails"+e.toString());
           }

           @Override
           public void onComplete() {

           }
       });
   }

   void getSize(String id){
       JsonObject jsonObject1 = new JsonObject();
       jsonObject1.addProperty("Id",id);
       jsonObject1.addProperty("refernce","ProductId");
       jsonObject1.addProperty("table","ProductSizeDetails");
       NetworkingUtils.getUserApiInstance().getSize(jsonObject1).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SizePojo>() {
           @Override
           public void onSubscribe(Disposable d) {

           }

           @Override
           public void onNext(SizePojo colorPojo) {

               productPresent.showSize(colorPojo.getResult());
               productPresent.hideProgress();
           }

           @Override
           public void onError(Throwable e) {
               productPresent.hideProgress();
           }

           @Override
           public void onComplete() {

           }
       });

   }


    void getImages(String id){
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("Id",id);
        jsonObject1.addProperty("refernce","ProductId");
        jsonObject1.addProperty("table","ProductImages");
        NetworkingUtils.getUserApiInstance().getImages(jsonObject1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ImegesPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ImegesPojo colorPojo) {

                productPresent.showImages(colorPojo.getResult());
                productPresent.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                productPresent.hideProgress();
            }

            @Override
            public void onComplete() {

            }
        });

    }



}