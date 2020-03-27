package com.example.hospital.Appointment;

import com.example.hospital.ViewModel;

public interface AppointmentModel extends ViewModel {
    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void showToast(String message);

    void createdAppointmentId(String id);
}
