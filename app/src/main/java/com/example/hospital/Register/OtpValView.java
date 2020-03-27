package com.example.hospital.Register;

import com.example.hospital.ViewModel;

public interface OtpValView extends ViewModel {

    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void showToast(String message);

    void success();

    void faliure();
}
