package mv.hospital.cart.OldOrders;

import mv.hospital.ViewModel;

public interface OldOrdersView extends ViewModel {
    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void showToast(String message);

    void showDetails(OldPojo.Result[] results);
}
