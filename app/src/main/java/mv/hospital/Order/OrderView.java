package mv.hospital.Order;

import mv.hospital.ViewModel;

public interface OrderView extends ViewModel {
    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void showToast(String message);

    void sucess(String id);


    void placed();
}
