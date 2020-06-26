package mv.hospital.cart.orders;

import mv.hospital.ViewModel;

public interface OrderView extends ViewModel {
    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void showToast(String message);

    void getDetails(OrderPojo.Result[] orderPojo);
}
