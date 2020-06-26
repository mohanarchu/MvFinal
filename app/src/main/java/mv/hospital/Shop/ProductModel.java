package mv.hospital.Shop;

import android.content.Context;
import android.util.Log;

import mv.hospital.Networks.NetworkingUtils;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductModel {

     Context context ;
    ProductPresent productPresent;
     public ProductModel   (Context context,ProductPresent productPresent){
            this.context = context;
            this.productPresent  = productPresent;
      }


      public void getList(String sort, String skipCount, String limitCount, boolean first, String catId) {

          productPresent.showProgress();
          JsonObject jsonObject = new JsonObject();
          jsonObject.addProperty("table","Products");
          jsonObject.addProperty("sortProperty",sort);
          jsonObject.addProperty("sortType","ASC");
          jsonObject.addProperty("skipCount",skipCount);
          jsonObject.addProperty("limitCount",limitCount);
          jsonObject.addProperty("CategoryId",catId);
          NetworkingUtils.getUserApiInstance().getLogin(jsonObject).subscribeOn(Schedulers.io()).
                  observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ProductPojo>() {
              @Override
              public void onSubscribe(Disposable d) {
              }
              @Override
              public void onNext(ProductPojo commPojo) {
                  if (commPojo.getStatus().equals("true")){
                      if (first){
                          productPresent.showDetails(commPojo.getResult());
                      }else {
                          productPresent.showNext(commPojo.getResult());
                      }

                  }
                  productPresent.hideProgress();

              }

              @Override
              public void onError(Throwable e) {
                  Log.i("TAG","Details+"+e.toString());
                  productPresent.hideProgress();

              }

              @Override
              public void onComplete() {
                    productPresent.hideProgress();
              }
          });

      }
}
