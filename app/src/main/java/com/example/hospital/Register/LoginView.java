package com.example.hospital.Register;

import com.example.hospital.ViewModel;
import com.google.gson.JsonObject;

public interface LoginView extends ViewModel {
    @Override
    void showProgress();
    @Override
    void hideProgress();
    @Override
    void showToast(String message);
    void doRegister(JsonObject jsonObject);
    void doLogin(String otp);
}
