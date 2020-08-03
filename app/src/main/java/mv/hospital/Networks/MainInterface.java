package mv.hospital.Networks;



import mv.hospital.Appointment.model.AppointNumberPojo;
import mv.hospital.Appointment.model.BranchModel;
import mv.hospital.Appointment.model.MailPojo;
import mv.hospital.Appointment.model.RoomPojo;
import mv.hospital.Appointment.model.UserValPojo;
import mv.hospital.Order.OrderPojo;
import mv.hospital.Register.LoginPojo;
import mv.hospital.Register.OtpPojo;
import mv.hospital.Register.RegisterPojo;
import mv.hospital.Shop.ProductPojo;
import mv.hospital.Shop.address.AddressPojo;
import mv.hospital.Shop.address.StatePojo;
import mv.hospital.Shop.reviews.ReviewPojo;
import mv.hospital.cart.ColorPojo;
import mv.hospital.cart.DetailsPojo;
import mv.hospital.cart.ImegesPojo;
import mv.hospital.cart.OldOrders.OldPojo;
import mv.hospital.cart.SizePojo;
import mv.hospital.cart.pojo.ProductCommomPojo;
import mv.hospital.profile.CusPojo;
import mv.hospital.profile.ProPojo;
import com.google.gson.JsonObject;


import retrofit2.http.Body;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

     @POST("/api/AllOrders")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<mv.hospital.cart.orders.OrderPojo> getAllorders(@Body JsonObject statePost);

     @POST("/api/Myorders")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<OldPojo> myOrders(@Body JsonObject statePost);

     @POST("/api/List")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<ReviewPojo> getList(@Body JsonObject statePost);


     @POST("/api/validateUser")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<UserValPojo> userValidation(@Body JsonObject statePost);

     @GET("/api/getAppointmentNumber")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<AppointNumberPojo> getAppointmentNumbers();

     @GET("/api/branches")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<BranchModel> getBranches();

     @POST("/api/sendMail")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<MailPojo> sendMail(@Body JsonObject jsonObject);

     @POST("/api/Product")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<AddressPojo> getAddress(@Body JsonObject statePost);

     @POST("/api/commonList")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<StatePojo> getStates(@Body JsonObject statePost);

     @POST("/api/getRooms")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<RoomPojo> getRooms(@Body JsonObject jsonObject);

     @POST("/api/sendOrder")
     @Headers({"Content-Type: application/json;charset=UTF-8"})
     Observable<MailPojo> sendOrderMail(@Body JsonObject jsonObject);

}



