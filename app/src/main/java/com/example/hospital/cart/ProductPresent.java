package com.example.hospital.cart;

import com.example.hospital.ViewModel;
import com.example.hospital.cart.pojo.ProductCommomPojo;

public interface ProductPresent extends ViewModel {


    @Override
    void showProgress();

    @Override
    void hideProgress();

    void showDetails(DetailsPojo.Result[] detailsPojo);

    void showColor(ColorPojo.Result[] colorPojos);

    void showSize(SizePojo.Result[] sizePojos);

    void showImages(ImegesPojo.Result[] sizePojos);

    void showProductCommom(ProductCommomPojo sizePojos);
}


