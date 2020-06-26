package mv.hospital.profile;

import android.content.Context;
import android.util.Log;

import mv.hospital.Networks.NetworkingUtils;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter {

    Context context;
    ProfileView profilePresenter;
    public ProfilePresenter (Context context,ProfileView profilePresenter){
        this.profilePresenter = profilePresenter;
        this.context = context;
    }


    void update(JsonObject jsonObject, String id){
        NetworkingUtils.getUserApiInstance().update(id,jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ProPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProPojo otpPojo) {
                if (otpPojo.getStatus().equals("true")){
                    profilePresenter.showToast("Updated successfully");
                    profilePresenter.success();

                }else {
                    profilePresenter.showToast("Try again.");
                }

               // profilePresenter.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
            //    profilePresenter.hideProgress();
                Log.i("TAG","Error log"+e.toString()+jsonObject);
            }

            @Override
            public void onComplete() {
            //    profilePresenter.hideProgress();
            }
        });

    }

    void showCustomer(JsonObject jsonObject){
        profilePresenter.showProgress();
        NetworkingUtils.getUserApiInstance().viewCustomer(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CusPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CusPojo cusPojo) {

                if (cusPojo.getStatus().equals("true")){
                   profilePresenter.shoeCustome(cusPojo.getResult());
                }else {
                    profilePresenter.showToast("Try again");
                }
                profilePresenter.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                profilePresenter.hideProgress();
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
