package com.arj.hicarehygiene.network;

import com.arj.hicarehygiene.network.model.BasicResponse;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.model.OrdersModel.GetOrderRequest;
import com.arj.hicarehygiene.network.model.OrdersModel.GetOrderResponse;
import com.arj.hicarehygiene.network.model.ReferralModel.AddReferralRequest;
import com.arj.hicarehygiene.network.model.ReferralModel.AddReferralResponse;
import com.arj.hicarehygiene.network.model.ReferralModel.GetReferralRequest;
import com.arj.hicarehygiene.network.model.ReferralModel.GetReferralResponse;
import com.arj.hicarehygiene.network.model.VerifyOtpRequest;
import com.arj.hicarehygiene.network.model.access.CreateUserRequest;
import com.arj.hicarehygiene.network.model.access.CreateUserResonse;
import com.arj.hicarehygiene.network.model.complaint.ComplaintCaseIdRequest;
import com.arj.hicarehygiene.network.model.complaint.ComplaintHistoryRequest;
import com.arj.hicarehygiene.network.model.complaint.ComplaintHistoryResponse;
import com.arj.hicarehygiene.network.model.complaint.ComplaintTypeResponse;
import com.arj.hicarehygiene.network.model.complaint.CreateComplaintRequest;
import com.arj.hicarehygiene.network.model.complaint.CreateComplaintResponse;
import com.arj.hicarehygiene.network.model.offer.OfferResponse;
import com.arj.hicarehygiene.network.model.slots.SlotResponse;
import com.arj.hicarehygiene.network.model.todayservice.TodayResponse;
import com.arj.hicarehygiene.network.model.userservices.MyServiceRequest;
import com.arj.hicarehygiene.network.model.userservices.ServiceRequest;
import com.arj.hicarehygiene.network.model.userservices.ServiceResponse;
import com.arj.hicarehygiene.network.model.voucher.VoucherRequest;
import com.arj.hicarehygiene.network.model.voucher.VoucherResponseMain;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofit {
    String BASE_URL = "http://apps.hicare.in/apps/api/";
    String SLOTS_URL = "http://run.hicare.in/slot/api/";

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("grant_type") String grantType,
                              @Field("UserName") String username, @Field("Password") String password);

    @POST("users/Registration")
    Call<BasicResponse> createNewUser(
            @Body CreateUserRequest request);

//    @POST("users/VerifyOTP")
//    Call<String> verifyOtp(@Body VerifyOtpRequest request);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> refreshToken(@Field("grant_type") String grantType,
                                     @Field("refresh_token") String refreshToken);

    @POST("users/VerifyOTP")
    Call<BasicResponse> verify(@Body VerifyOtpRequest request);

    @FormUrlEncoded
    @POST("users/ResendOTP")
    Call<LoginResponse> resendOtp(@Field("UserId") String userid);

    /*[Get Orders]*/

    @POST("orders/GetOrders")
    Call<GetOrderResponse> getOrderList(
            @Body GetOrderRequest request);

    /*[Get All Active Orders]*/

    @POST("orders/GetActiveOrders")
    Call<GetOrderResponse> getAllActiveOrders(
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

    @POST("referral/CreateReferral")
    Call<AddReferralResponse> postReferrals(@Body AddReferralRequest request);


    /*[Referral Details]*/

    @POST("referral/GetReferrals")
    Call<GetReferralResponse> getReferrals(@Body GetReferralRequest request);

    /*[Voucher Code]*/

    @POST("users/CustomerReferral")
    Call<VoucherResponseMain> getVoucherCode(@Body VoucherRequest request);

    /*[Complaint Types]*/

    @POST("complaint/GetComplaintType")
    Call<ComplaintTypeResponse> getComplaintTypes();

    /*[Create Complaints]*/

    @POST("complaint/CreateComplaint")
    Call<CreateComplaintResponse> getCreateComplaints(@Body CreateComplaintRequest request);

    /*[ComplaintByCaseNo]*/

    @POST("complaint/GetComplaintByCaseNo")
    Call<ComplaintHistoryResponse> getComplaintsByCaseNumber(@Body ComplaintCaseIdRequest request);


    /*[GetAllService]*/

    @POST("servicerequest/GetAllServiceRequest")
    Call<ServiceResponse> getAllServiceRequest(@Body MyServiceRequest request);

    /*Service Booking*/

    @GET("Slot/GetAvailableSlot")
    Call<SlotResponse> getAvailableSlot(@Query("taskId") String taskId, @Query("slotStartDate") String slotStartDate,
                                        @Query("slotEndDate") String slotEndDate);

    @GET("offers/GetOffers")
    Call<OfferResponse> getOffers();

}
