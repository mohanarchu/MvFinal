package mv.hospital.Order;

import android.content.Context;

import mv.hospital.Appointment.model.MailPojo;
import mv.hospital.Networks.NetworkingUtils;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mv.hospital.profile.ProPojo;

public class OrderPresenter {

    Context context;
    OrderView orderView;
    public OrderPresenter(Context context,OrderView orderView){

        this.orderView = orderView;
        this.context  = context;
    }

    public void makeOrder(JsonObject jsonObject){

        orderView.showProgress();

        NetworkingUtils.getUserApiInstance().order(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OrderPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OrderPojo orderPojo) {

                if (orderPojo.getStatus().equals("true")){
                    orderView.hideProgress();
                    orderView.sucess(orderPojo.getResult()[0].getID());
                    orderView.hideProgress();
                }else {
                    orderView.showToast("Try again");
                }
                orderView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                orderView.hideProgress();

            }

            @Override
            public void onComplete() {
                orderView.hideProgress();

            }
        });
    }


    public void postProducts(JsonObject jsonObject){


        NetworkingUtils.getUserApiInstance().order(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OrderPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OrderPojo orderPojo) {

                if (orderPojo.getStatus().equals("true")){
                    orderView.hideProgress();
                    orderView.placed();
                  //  orderView.showToast("Order placed successfully");
                }else {
                    orderView.showToast("Try again");
                }
                orderView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                orderView.hideProgress();
            }

            @Override
            public void onComplete() {
                orderView.hideProgress();

            }
        });

    }
    public void updateOrder(JsonObject jsonObject,String orderId){



        NetworkingUtils.getUserApiInstance().update(orderId,jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ProPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProPojo orderPojo) {

                if (orderPojo.getStatus().equals("true")){
                    orderView.showToast("Completed");

                } else {
                    orderView.showToast("Try again");
                }
                orderView.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                orderView.hideProgress();
            }

            @Override
            public void onComplete() {
                orderView.hideProgress();

            }
        });

    }
    public void sendOrderMail(JsonObject jsonObject){



        NetworkingUtils.getUserApiInstance().sendOrderMail(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MailPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MailPojo orderPojo) {
                if (orderPojo.getStatus().equals("true")){
                    orderView.placed();
                }else {
                    orderView.showToast("Try again");
                }
                orderView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                orderView.hideProgress();
            }

            @Override
            public void onComplete() {
                orderView.hideProgress();

            }
        });

    }

}
