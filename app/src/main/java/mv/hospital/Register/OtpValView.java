package mv.hospital.Register;

import mv.hospital.ViewModel;

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
