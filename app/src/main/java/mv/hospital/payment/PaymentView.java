package mv.hospital.payment;

import mv.hospital.ViewModel;

public interface PaymentView extends ViewModel {

    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void showToast(String message);

    void success(String key);
}
