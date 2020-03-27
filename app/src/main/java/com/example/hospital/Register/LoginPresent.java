package com.example.hospital.Register;

import android.content.Context;
import android.util.Log;

import com.example.hospital.Networks.NetworkingUtils;
import com.google.gson.JsonObject;
import com.squareup.moshi.Json;

import java.util.Arrays;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresent {

    LoginView loginView;
    Context context;
    public LoginPresent(Context context,LoginView loginView){
        this.context = context;
        this.loginView = loginView;
    }
    void getLogin(JsonObject jsonObject){
        loginView.showProgress();
        NetworkingUtils.getUserApiInstance().loginValidate(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LoginPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginPojo loginPojo) {

                if (loginPojo.getStatus().equals("true")){

                    if (loginPojo.getResult() == null || loginPojo.getResult().length == 0  ){
                     //   loginView.showToast("Try again");
                        loginView.doRegister(jsonObject);
                    } else {
                       // loginView.showToast("Try");
                        loginView.doLogin(loginPojo.getOtp());
                    }
                }else {
                    loginView.showToast("Try again");
                }
                loginView.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                Log.i("TAG","JsonObjects"+e.toString()+jsonObject);
            }

            @Override
            public void onComplete() {
                loginView.hideProgress();
            }
        });
    }

    void doRegister(JsonObject jsonObject){
        NetworkingUtils.getUserApiInstance().doRegiser(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RegisterPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegisterPojo registerPojo) {
                Log.i("TAG","JsonObjectss"+ registerPojo.getOtp());
                if (registerPojo.getStatus().equals("true")){

                    loginView.doLogin(registerPojo.getOtp());
                }else {
                    loginView.showToast("Try again");
                }
                loginView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                loginView.hideProgress();
            }

            @Override
            public void onComplete() {
                loginView.hideProgress();
            }
        });
    }
}
