package com.example.hospital.profile;

import com.example.hospital.ViewModel;

public interface ProfileView extends ViewModel {
    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void showToast(String message);


    void success();

    void failure();

    void shoeCustome(CusPojo.Result[] cusPojo);



}
