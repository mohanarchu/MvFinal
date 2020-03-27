package com.example.hospital.Appointment;

import android.content.Context;
import android.util.Log;

import com.example.hospital.Networks.NetworkingUtils;
import com.example.hospital.Order.OrderPojo;
import com.example.hospital.Shop.ProductPojo;
import com.example.hospital.profile.Shared;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AppointMentView {


    Context context;
    AppointmentModel appointmentModel;
    public AppointMentView(Context context,AppointmentModel appointmentModel){
        this.context= context;
        this.appointmentModel = appointmentModel;
    }


    public void createAPpointment(JsonObject jsonObject){
        jsonObject.add("data", jsonObject);
        jsonObject.addProperty("table", "Registration");
        jsonObject.addProperty("multipleInsert", false);
        NetworkingUtils.getUserApiInstance().order(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OrderPojo>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(OrderPojo commPojo) {
                if (commPojo.getStatus().equals("true")){
                    appointmentModel.hideProgress();
                }


            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG","Details+"+e.toString());
                appointmentModel.hideProgress();

            }

            @Override
            public void onComplete() {

                appointmentModel.hideProgress();
            }
        });
    }
}
