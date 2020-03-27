package com.example.hospital.cart.orders;

import android.content.Context;
import android.util.Log;

import com.example.hospital.Networks.NetworkingUtils;
import com.example.hospital.profile.Shared;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderPresenter {

    Context context;
    OrderView orderView;
    public OrderPresenter(Context context,OrderView orderView){
        this.context = context;
        this.orderView = orderView;
    }

    void getProViews(){
        orderView.showProgress();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Id", Shared.id(context));
        jsonObject.addProperty("refernce","UserId");
        jsonObject.addProperty("table","OrderMaster");

      //  Log.i("TAG","Orders details"+ jsonObject);
        NetworkingUtils.getUserApiInstance().getAllorders (jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OrderPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OrderPojo orderPojo) {
                if (orderPojo.getStatus().equals("true")){
                    orderView.getDetails(orderPojo.getResult());
                }else {
                    orderView.showToast("Try again");
                }
                orderView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                orderView.hideProgress();
               // Log.i("TAG","Orders details"+ e.toString());
            }

            @Override
            public void onComplete() {
                orderView.hideProgress();
            }
        });
    }
}
