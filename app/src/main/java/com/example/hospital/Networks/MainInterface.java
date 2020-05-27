package com.example.hospital.Networks;



import android.provider.MediaStore;

import com.example.hospital.Order.OrderPojo;
import com.example.hospital.Register.LoginPojo;
import com.example.hospital.Register.OtpPojo;
import com.example.hospital.Register.RegisterPojo;
import com.example.hospital.Shop.ProductPojo;
import com.example.hospital.Shop.reviews.ReviewPojo;
import com.example.hospital.cart.ColorPojo;
import com.example.hospital.cart.DetailsPojo;
import com.example.hospital.cart.ImegesPojo;
import com.example.hospital.cart.OldOrders.OldPojo;
import com.example.hospital.cart.SizePojo;
import com.example.hospital.cart.pojo.ProductCommomPojo;
import com.example.hospital.payment.PaymentPojo;
import com.example.hospital.profile.CusPojo;
import com.example.hospital.profile.ProPojo;
import com.google.gson.JsonObject;



import retrofit2.http.Body;
import retrofit2.http.GET;

import io.reactivex.Observable;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;

public interface MainInterface {

     @POST("/api")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<ProductPojo> getLogin(@Body JsonObject statePost);

     @POST("/api/Product")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<DetailsPojo> getProductDetails(@Body JsonObject statePost);

     @POST("/api/getProductDetails")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<ProductCommomPojo> getProduct(@Body JsonObject statePost);

     @POST("/api/UpdateReview")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<OrderPojo> updateReview(@Body JsonObject statePost);


     @POST("/api/Product")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<ColorPojo> getColor(@Body JsonObject statePost);

     @POST("/api/Product")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<SizePojo> getSize(@Body JsonObject statePost);

     @POST("/api/Product")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<ImegesPojo> getImages(@Body JsonObject statePost);

     @POST("/api")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<SizePojo> addProducts(@Body JsonObject statePost);

     @POST("/api/login")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<LoginPojo> loginValidate(@Body JsonObject statePost);

     @POST("/api/User")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<RegisterPojo> doRegiser(@Body JsonObject statePost);

     @POST("/api/otp-validate")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<OtpPojo> otpValidate(@Body JsonObject statePost);

     @PUT("/api/{phone_number}")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<ProPojo> update(@Path("phone_number") String id, @Body JsonObject statePost);

     @POST("/api/Product")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<CusPojo> viewCustomer(@Body JsonObject statePost);

     @POST("/api/Order")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<OrderPojo> order(@Body JsonObject statePost);

     @POST("/api/Product")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<com.example.hospital.cart.orders.OrderPojo> getAllorders(@Body JsonObject statePost);

     @POST("/api/Myorders")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<OldPojo> myOrders(@Body JsonObject statePost);

     @POST("/api/List")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<ReviewPojo> getList(@Body JsonObject statePost);


//
//     @POST("/api/pay")
//     @Headers({"Content-Type: application/json;charset=UTF-8"})
//     Observable<PaymentPojo> makePayment(@Body PayUmoneySdkInitializer.PaymentParam mPaymentParams);


}



