package mv.hospital.Appointment;

import mv.hospital.ViewModel;

public interface AppointmentModel extends ViewModel {
    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void showToast(String message);

    void showPatientId(String id,String regId);

    void createdPatientid(String id);

    void showRegNumber(String id);
}
