package mv.hospital.payment;

import android.content.Context;

public class PaymentModel {


    Context context;
    PaymentView paymentView;
    public PaymentModel(Context context,PaymentView paymentView){
        this.context= context;
        this.paymentView = paymentView;
    }

//    void  makeHashRequest(PayUmoneySdkInitializer.PaymentParam mPaymentParams) {
//        NetworkingUtils.getUserApiInstance().makePayment(mPaymentParams).subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PaymentPojo>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//            @Override
//            public void onNext(PaymentPojo commPojo) {
//                Log.i("TAG","Payment result+"+commPojo.getResult());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i("TAG","Payment result+"+e.toString());
//
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//    }

}
