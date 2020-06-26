package mv.hospital.cart.OldOrders;

import android.content.Context;

import mv.hospital.Networks.NetworkingUtils;
import mv.hospital.Order.OrderPojo;
import mv.hospital.profile.Shared;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OldOrderPresenter {


    Context context;
    OldOrdersView oldOrdersView;
    public OldOrderPresenter(Context context,OldOrdersView oldOrdersView){
        this.context = context;
        this.oldOrdersView= oldOrdersView;
    }

    void getDetails(String id   ){
        oldOrdersView.showProgress();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("orderId",id);
        jsonObject.addProperty("id", Shared.id(context));
        NetworkingUtils.getUserApiInstance().myOrders(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OldPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OldPojo oldPojo) {


                if (oldPojo.getStatus().equals("true")){
                    oldOrdersView.showDetails(oldPojo.getResult());
                }else {
                    oldOrdersView.showToast("Try again");
                }
                oldOrdersView.hideProgress();

            }

            @Override
            public void onError(Throwable e) {
                oldOrdersView.hideProgress();
            }

            @Override
            public void onComplete() {
                oldOrdersView.hideProgress();
            }
        });
    }

    void  updateReview(JsonObject jsonObject) {
        oldOrdersView.showProgress();

        NetworkingUtils.getUserApiInstance().updateReview(jsonObject).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OrderPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OrderPojo orderPojo) {

                if (orderPojo.getStatus().equals("true")) {
                    oldOrdersView.showToast("Review updated");
                    oldOrdersView.hideProgress();
                } else  {
                    oldOrdersView.showToast("Review update failed");
                }
            }

            @Override
            public void onError(Throwable e) {
                oldOrdersView.hideProgress();
            }

            @Override
            public void onComplete() {

            }
        });
    }
    void  createReview(JsonObject jsonObject) {

        oldOrdersView.showProgress();
        NetworkingUtils.getUserApiInstance().order(jsonObject).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OrderPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OrderPojo orderPojo) {

                if (orderPojo.getStatus().equals("true")) {
                    oldOrdersView.showToast("Review updated");
                    oldOrdersView.hideProgress();
                } else  {
                    oldOrdersView.showToast("Review post failed");
                }
            }

            @Override
            public void onError(Throwable e) {
                oldOrdersView.hideProgress();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
