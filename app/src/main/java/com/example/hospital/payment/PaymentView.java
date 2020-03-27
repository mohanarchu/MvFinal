package com.example.hospital.payment;

import com.example.hospital.ViewModel;

public interface PaymentView extends ViewModel {

    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void showToast(String message);

    void success(String key);
}
