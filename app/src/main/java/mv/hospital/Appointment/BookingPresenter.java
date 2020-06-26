package mv.hospital.Appointment;

import android.content.Context;

import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mv.hospital.Appointment.model.AppointNumberPojo;
import mv.hospital.Appointment.model.BranchModel;
import mv.hospital.Appointment.model.MailPojo;
import mv.hospital.Appointment.model.UserValPojo;
import mv.hospital.Networks.NetworkingUtils;
import mv.hospital.ViewModel;

public class BookingPresenter {

    Context context;
    BookingView bookingView;
    public BookingPresenter(Context context,BookingView bookingView) {
        this.bookingView = bookingView;
        this.context = context;
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
                    bookingView.showAppNumbers(commPojo.getResult()[0].getRegistrationId(),
                            commPojo.getResult1()[0].getAppointmentid());
                } else {
                    bookingView.showToast("Try again");
                }
                bookingView.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                bookingView.hideProgress();
            }
            @Override
            public void onComplete() {
                bookingView.hideProgress();
            }
        });
    }
    public void sendMail(JsonObject jsonObject) {

        NetworkingUtils.getUserApiInstance().sendMail(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MailPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(MailPojo commPojo) {
                if (commPojo.getStatus().equals("true")){
                    bookingView.mailSuccess();
                } else {
                    bookingView.showToast("Try again");
                }

                bookingView.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                bookingView.hideProgress();
            }
            @Override
            public void onComplete() {
                bookingView.hideProgress();
            }
        });
    }

    public void getBranches() {

        NetworkingUtils.getUserApiInstance().getBranches().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BranchModel>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(BranchModel commPojo) {

                if (commPojo.getStatus().equals("true")){
                     bookingView.showBranches(commPojo.getResult());
                } else {
                    bookingView.showToast("Try again");
                }

                bookingView.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                bookingView.hideProgress();
            }
            @Override
            public void onComplete() {
                bookingView.hideProgress();
            }
        });
    }


    public interface BookingView extends ViewModel {
        @Override
        void showProgress();

        @Override
        void hideProgress();

        @Override
        void showToast(String message);

        void showBranches(BranchModel.Result[] branches);

        void showAppNumbers(String RegistrationId, String Appointmentid);

        void mailSuccess();

    }
}
