package com.arj.hicarehygiene.network;

import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.model.OrdersModel.GetOrderRequest;
import com.arj.hicarehygiene.network.model.OrdersModel.GetOrderResponse;
import com.arj.hicarehygiene.network.model.VerifyOtpRequest;
import com.arj.hicarehygiene.network.model.access.CreateUserRequest;
import com.arj.hicarehygiene.network.model.access.CreateUserResonse;
import com.arj.hicarehygiene.network.model.complaint.ComplaintHistoryRequest;
import com.arj.hicarehygiene.network.model.complaint.ComplaintHistoryResponse;
import com.arj.hicarehygiene.network.model.todayservice.TodayResponse;
import com.arj.hicarehygiene.network.model.userservices.ServiceRequest;
import com.arj.hicarehygiene.network.model.userservices.ServiceResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IRetrofit {
    String BASE_URL = "http://apps.hicare.in/apps/api/";
//    http://apps.hicare.in/apps/api/login

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("grant_type") String grantType,
                              @Field("UserName") String username, @Field("Password") String password);

    @POST("users/Registration")
    Call<CreateUserResonse> createNewUser(
            @Body CreateUserRequest request);

    @POST("users/VerifyOTP")
    Call<String> verifyOtp(@Body VerifyOtpRequest request);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> refreshToken(@Field("grant_type") String grantType,
                                     @Field("refresh_token") String refreshToken);

    @FormUrlEncoded
    @POST("users/VerifyOTP")
    Call<VerifyOtpRequest> verify(@Field("Code") String code,
                                  @Field("UserId") String userid);


    @FormUrlEncoded
    @POST("users/ResendOTP")
    Call<LoginResponse> resendOtp(@Field("UserId") String userid);

    @POST("orders/GetOrders")
    Call<GetOrderResponse> getOrderList(
            @Body GetOrderRequest request);

    @POST("complaint/GetComplaint")
    Call<ComplaintHistoryResponse> getComplaintList(
            @Body ComplaintHistoryRequest request);

    @POST("servicerequest/GetServiceRequest")
    Call<ServiceResponse> getServiceList(
            @Body ServiceRequest request);

    @FormUrlEncoded
    @POST("servicerequest/GetTodaysService")
    Call<TodayResponse> todayService(@Field("MobileNo") String userid);

}
