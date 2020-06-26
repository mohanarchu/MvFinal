package mv.hospital.Register;

import android.content.Context;

import mv.hospital.Networks.NetworkingUtils;
import mv.hospital.profile.Shared;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OtpValidate {

    Context context;
    OtpValView otpValView;

    public OtpValidate(Context context,OtpValView otpValView){
        this.context =context;
        this.otpValView =otpValView;
    }

    void getLogin(JsonObject jsonObject){

        NetworkingUtils.getUserApiInstance().otpValidate(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OtpPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OtpPojo otpPojo) {
                if (otpPojo.getStatus().equals("true")){
                    if (otpPojo.getAuthStatus().equals("true")){
                        otpValView.success();
                        Shared.putDetailss(otpPojo.getResult(),context);
                    }else {
                        otpValView.showToast("Enter valid otp");
                    }

                }else {
                    otpValView.showToast("Try again");
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
