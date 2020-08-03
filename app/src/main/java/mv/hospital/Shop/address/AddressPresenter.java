package mv.hospital.Shop.address;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mv.hospital.Networks.NetworkingUtils;
import mv.hospital.Order.OrderPojo;
import mv.hospital.Shop.ProductPojo;
import mv.hospital.ViewModel;
import mv.hospital.profile.ProPojo;

public class AddressPresenter {
    Context context;
    AddressView addressView;
    public AddressPresenter(Context context,AddressView addressView){
        this.context = context;
        this.addressView = addressView;
    }
    public void makeOrder(JsonObject jsonObject) {


        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("table","Address");
        jsonObject1.addProperty("multipleInsert",false);
        jsonObject1.add("data",jsonObject);

        NetworkingUtils.getUserApiInstance().order(jsonObject1).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OrderPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OrderPojo orderPojo) {

                if (orderPojo.getStatus().equals("true")) {
                    addressView.hideProgress();
                }  else {
                    addressView.showDetails("Failed");
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

    public void getStates() {
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("table","State");
        NetworkingUtils.getUserApiInstance().getStates(jsonObject2).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<StatePojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StatePojo orderPojo) {

                if (orderPojo.getStatus().equals("true")) {
                    addressView.showStates(orderPojo.getResult());
                } else {
                    addressView.showDetails("Failed");
                }

            }

            @Override
            public void onError(Throwable e) {
                addressView.showDetails("Failed");
            }

            @Override
            public void onComplete() {
                addressView.showDetails("Failed");

            }
        });
    }

    public void getList(String id) {
        addressView.showProgress();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table","Address");
        jsonObject.addProperty("Id",id);
        jsonObject.addProperty("refernce","customerid");

        NetworkingUtils.getUserApiInstance().getAddress(jsonObject).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AddressPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(AddressPojo commPojo) {
                if (commPojo.getStatus().equals("true")){
                    addressView.showResult(commPojo.getResult());
                }
                addressView.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                Log.i("TAG","Details+"+e.toString());
                addressView.hideProgress();

            }

            @Override
            public void onComplete() {
                addressView.hideProgress();
            }
        });

    }
    public interface AddressView extends ViewModel {
        @Override
        void hideProgress();

        void showDetails(String message);

        void showProgress();

        void showResult(AddressPojo.Result[] results);

        void showStates(StatePojo.Result[] results);
    }
}
