package com.example.hospital.Order;

import android.content.Context;
import android.util.Log;

import com.example.hospital.Networks.NetworkingUtils;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderPresenter {

    Context context;
    OrderView orderView;
    public OrderPresenter(Context context,OrderView orderView){

        this.orderView = orderView;
        this.context  = context;
    }

    public void makeOrder(JsonObject jsonObject){

        orderView.showProgress();

        NetworkingUtils.getUserApiInstance().order(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OrderPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OrderPojo orderPojo) {

                if (orderPojo.getStatus().equals("true")){
                    orderView.hideProgress();
                    orderView.sucess(orderPojo.getResult()[0].getID());
                    orderView.hideProgress();
                }else {
                    orderView.showToast("Try again");
                }
                orderView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                orderView.hideProgress();
                Log.i("TAG", "ProductArray" + e.toString());
            }

            @Override
            public void onComplete() {
                orderView.hideProgress();

            }
        });
    }


    public void postProducts(JsonObject jsonObject){


        NetworkingUtils.getUserApiInstance().order(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OrderPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OrderPojo orderPojo) {

                if (orderPojo.getStatus().equals("true")){
                    orderView.hideProgress();
                  orderView.placed();
                    orderView.showToast("Order placed successfully");
                }else {
                    orderView.showToast("Try again");
                }
                orderView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                orderView.hideProgress();
            }

            @Override
            public void onComplete() {
                orderView.hideProgress();

            }
        });

    }


}
