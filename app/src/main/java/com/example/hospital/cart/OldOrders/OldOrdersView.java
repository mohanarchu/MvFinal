package com.example.hospital.cart.OldOrders;

import com.example.hospital.ViewModel;

public interface OldOrdersView extends ViewModel {
    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void showToast(String message);

    void showDetails(OldPojo.Result[] results);
}
