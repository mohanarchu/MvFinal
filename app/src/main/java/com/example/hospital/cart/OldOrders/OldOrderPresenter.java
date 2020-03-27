package com.example.hospital.cart.OldOrders;

import android.content.Context;

import com.example.hospital.Networks.NetworkingUtils;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OldOrderPresenter {


    Context context;
    OldOrdersView oldOrdersView;
    public OldOrderPresenter(Context context,OldOrdersView oldOrdersView){
        this.context = context;
        this.oldOrdersView= oldOrdersView;
    }

    void getDetails(String id   ){
        oldOrdersView.showProgress();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Id",id);
        NetworkingUtils.getUserApiInstance().myOrders(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OldPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OldPojo oldPojo) {


                if (oldPojo.getStatus().equals("true")){
                    oldOrdersView.showDetails(oldPojo.getResult());
                }else {
                    oldOrdersView.showToast("Try again");
                }
                oldOrdersView.hideProgress();

            }

            @Override
            public void onError(Throwable e) {
                oldOrdersView.hideProgress();
            }

            @Override
            public void onComplete() {
                oldOrdersView.hideProgress();
            }
        });
    }

}
