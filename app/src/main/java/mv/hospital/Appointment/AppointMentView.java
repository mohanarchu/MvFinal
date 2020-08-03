package mv.hospital.Appointment;

import android.content.Context;
import android.util.Log;

import mv.hospital.Appointment.model.AppointNumberPojo;
import mv.hospital.Appointment.model.UserValPojo;
import mv.hospital.Networks.NetworkingUtils;
import mv.hospital.Order.OrderPojo;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mv.hospital.profile.ProPojo;

public class AppointMentView {


    Context context;
    AppointmentModel appointmentModel;
    public AppointMentView(Context context,AppointmentModel appointmentModel){
        this.context= context;
        this.appointmentModel = appointmentModel;
    }

    public void getUserValidation(String email) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email",email);
        NetworkingUtils.getUserApiInstance().userValidation(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserValPojo>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(UserValPojo commPojo) {
                if (commPojo.getStatus().equals("true")){
                    if(commPojo.getResult().length != 0)
                        appointmentModel.showPatientId(commPojo.getResult()[0].getPatientId(),commPojo.getResult()[0].getRegistrationId());
                    else appointmentModel.showPatientId("-1","");
                }

                appointmentModel.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                appointmentModel.hideProgress();
            }
            @Override
            public void onComplete() {
                appointmentModel.hideProgress();
            }
        });

    }
    public void getAppointmentNumber() {

        NetworkingUtils.getUserApiInstance().getAppointmentNumbers().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AppointNumberPojo>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(AppointNumberPojo commPojo) {
                if (commPojo.getStatus().equals("true")){
                    appointmentModel.showRegNumber(commPojo.getResult()[0].getRegistrationId());
                } else {
                    appointmentModel.showToast("Try again");
                }

                appointmentModel.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                appointmentModel.hideProgress();
            }
            @Override
            public void onComplete() {
                appointmentModel.hideProgress();
            }
        });
    }

    public void makeOrder(JsonObject jsonObject){

        appointmentModel.showProgress();

        NetworkingUtils.getUserApiInstance().order(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OrderPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OrderPojo orderPojo) {

                if (orderPojo.getStatus().equals("true")) {
                    appointmentModel.hideProgress();
                    appointmentModel.createdPatientid(orderPojo.getResult()[0].getID());
                    appointmentModel.hideProgress();
                }else {
                    appointmentModel.showToast("Try again");
                }
                appointmentModel.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                appointmentModel.hideProgress();

            }

            @Override
            public void onComplete() {
                appointmentModel.hideProgress();

            }
        });
    }
    public void makeUpdate(String patientId, JsonObject jsonObject){

        NetworkingUtils.getUserApiInstance().update(patientId,jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ProPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProPojo orderPojo) {

                if (orderPojo.getStatus().equals("true")){

                    appointmentModel.createdPatientid(patientId);

                }else {
                    appointmentModel.showToast("Try again");
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {


            }
        });
    }
}
