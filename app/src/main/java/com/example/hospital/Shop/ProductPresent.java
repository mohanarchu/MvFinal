package com.example.hospital.Shop;

import com.example.hospital.ViewModel;

public interface ProductPresent extends ViewModel {
    @Override
    void hideProgress();


    void showDetails(ProductPojo.Result[] productPojo);

    void showProgress();

    void showNext(ProductPojo.Result[] productPojo);

}
