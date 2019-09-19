package com.arj.hicarehygiene.network;

import android.os.Build;

import org.json.JSONObject;

import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.BaseFragment;
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
import com.arj.hicarehygiene.viewmodel.SignUpUserViewModel;
import com.arj.hicarehygiene.viewmodel.UserLoginViewModel;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCallController {

    private final BaseFragment mContext;
    private NetworkResponseListner mListner;

    public NetworkCallController(BaseFragment context) {
        this.mContext = context;
    }

    public NetworkCallController() {
        this.mContext = null;
    }

    public void setListner(NetworkResponseListner listner) {
        this.mListner = listner;
    }

    public void login(final int requestCode, final String username, final String password) {

        mContext.showProgressDialog();

        BaseApplication.getRetrofitAPI(false)
                .login("password", username, password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        mContext.dismissProgressDialog();

                        if (response != null) {
                            if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body());
                            } else if (response.errorBody() != null) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    mContext.showServerError("Oops!There is no account existed with provided details.");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            mContext.showServerError();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }


    public void refreshToken(final int requestCode, final String refreshToken) {
        BaseApplication.getRetrofitAPI(false)
                .refreshToken("refresh_token", refreshToken)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response != null) {
                            if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body());
                            } else {
                                mListner.onFailure(requestCode);
                            }
                        } else {
                            mListner.onFailure(requestCode);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        mListner.onFailure(requestCode);
                    }
                });
    }

    public void createUser(final int requestCode, SignUpUserViewModel model) {
        mContext.showProgressDialog();

        CreateUserRequest request = new CreateUserRequest();
        request.setFirstName(model.getFirstName().trim());
        request.setLastName(model.getLastName().trim());
        request.setEmail(model.getEmail().trim());
        request.setPhoneNumber(model.getPhoneNumber().trim());

        BaseApplication.getRetrofitAPI(false)
                .createNewUser(request)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call,
                                           Response<BasicResponse> response) {
                        mContext.dismissProgressDialog();

                        if (response != null) {
                            if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body());
                            } else if (response.errorBody() != null) {
                                try {
                                    mContext.showServerError("Error");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            mContext.showServerError();
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }

//    public void verifyOtp(final int requestCode, UserLoginViewModel model) {
//        mContext.showProgressDialog();
////        VerifyOtpRequest request = new VerifyOtpRequest();
//        VerifyOtpRequest request = new VerifyOtpRequest();
//        String id = model.getId();
//        String code = model.getOtp();
//        request.setCode(model.getOtp());
//        request.setUserId(model.getId());
//
//        BaseApplication.getRetrofitAPI(false).verifyOtp(request).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                mContext.dismissProgressDialog();
//
//                if (response != null) {
//                    if (response.body() != null) {
//                        mListner.onResponse(requestCode, response.body());
//                    } else if (response.errorBody() != null) {
//                        try {
//                            JSONObject jObjError = new JSONObject(response.errorBody().string());
//                            mContext.showServerError(jObjError.getString("Message"));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                } else {
//                    mContext.showServerError();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                mContext.dismissProgressDialog();
//                mContext.showServerError("Please try again !!!");
//            }
//        });
//    }

    public void verify(final int requestCode, VerifyOtpRequest request) {
        mContext.showProgressDialog();
        BaseApplication.getRetrofitAPI(false)
                .verify(request)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body());
                            } else if (response.errorBody() != null) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    mContext.showServerError(jObjError.getString("Message"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            mContext.showServerError();
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }


    public void resendOtp(final int requestCode, final String userid) {

        mContext.showProgressDialog();

        BaseApplication.getRetrofitAPI(false)
                .resendOtp(userid)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        mContext.dismissProgressDialog();

                        if (response != null) {
                            if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body());
                            } else if (response.errorBody() != null) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    mContext.showServerError("Oops!There is no account existed with provided details.");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            mContext.showServerError();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }

    public String getRefreshToken() {
        String refreshToken = null;
        try {
            LoginResponse loginResponse =
                    Realm.getDefaultInstance().where(LoginResponse.class).findAll().get(0);
            refreshToken = loginResponse.getRefreshToken();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return refreshToken;
    }


    public void getOrderList(final int requestCode, final GetOrderRequest request) {
        mContext.showProgressDialog();

        BaseApplication.getRetrofitAPI(true)
                .getOrderList(request)
                .enqueue(new Callback<GetOrderResponse>() {
                    @Override
                    public void onResponse(Call<GetOrderResponse> call,
                                           Response<GetOrderResponse> response) {
                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.code() == 401) { // Unauthorised Access
                                NetworkCallController controller = new NetworkCallController();
                                controller.setListner(new NetworkResponseListner<LoginResponse>() {
                                    @Override
                                    public void onResponse(int reqCode, LoginResponse response) {
                                        // delete all previous record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().deleteAll();
                                        Realm.getDefaultInstance().commitTransaction();

                                        // add new record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().copyToRealmOrUpdate(response);
                                        Realm.getDefaultInstance().commitTransaction();
                                        getOrderList(requestCode, request);
                                    }

                                    @Override
                                    public void onFailure(int requestCode) {

                                    }
                                });
                                controller.refreshToken(100, getRefreshToken());
                            } else if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body().getData());
                            } else if (response.errorBody() != null) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    mContext.showServerError(jObjError.getString("Message"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            mContext.showServerError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetOrderResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }

    public void getAllActiveOrders(final int requestCode, final GetOrderRequest request) {
        mContext.showProgressDialog();

        BaseApplication.getRetrofitAPI(true)
                .getAllActiveOrders(request)
                .enqueue(new Callback<GetOrderResponse>() {
                    @Override
                    public void onResponse(Call<GetOrderResponse> call,
                                           Response<GetOrderResponse> response) {
                        if (response != null) {
                            mContext.dismissProgressDialog();
                            if (response.code() == 401) { // Unauthorised Access
                                NetworkCallController controller = new NetworkCallController();
                                controller.setListner(new NetworkResponseListner<LoginResponse>() {
                                    @Override
                                    public void onResponse(int reqCode, LoginResponse response) {
                                        // delete all previous record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().deleteAll();
                                        Realm.getDefaultInstance().commitTransaction();

                                        // add new record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().copyToRealmOrUpdate(response);
                                        Realm.getDefaultInstance().commitTransaction();
                                        getOrderList(requestCode, request);
                                    }

                                    @Override
                                    public void onFailure(int requestCode) {

                                    }
                                });
                                controller.refreshToken(100, getRefreshToken());
                            } else if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body().getData());
                            } else if (response.errorBody() != null) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    mContext.showServerError(jObjError.getString("Message"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            mContext.showServerError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetOrderResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }


    public void getComplaintList(final int requestCode, final ComplaintHistoryRequest request) {
//        mContext.showProgressDialog();
        BaseApplication.getRetrofitAPI(true)
                .getComplaintList(request)
                .enqueue(new Callback<ComplaintHistoryResponse>() {
                    @Override
                    public void onResponse(Call<ComplaintHistoryResponse> call,
                                           Response<ComplaintHistoryResponse> response) {
//                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.code() == 401) { // Unauthorised Access
                                NetworkCallController controller = new NetworkCallController();
                                controller.setListner(new NetworkResponseListner<LoginResponse>() {
                                    @Override
                                    public void onResponse(int reqCode, LoginResponse response) {
                                        // delete all previous record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().deleteAll();
                                        Realm.getDefaultInstance().commitTransaction();

                                        // add new record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().copyToRealmOrUpdate(response);
                                        Realm.getDefaultInstance().commitTransaction();
                                        getComplaintList(requestCode, request);
                                    }

                                    @Override
                                    public void onFailure(int requestCode) {

                                    }
                                });
                                controller.refreshToken(100, getRefreshToken());
                            } else if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body().getData());
                            } else {
                                if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
//                            mContext.dismissProgressDialog();
                            mContext.showServerError("Something went wrong!");
                        }
                    }

                    @Override
                    public void onFailure(Call<ComplaintHistoryResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }


    public void getTodayService(final int requestCode, final String mobile) {

//        mContext.showProgressDialog();

        BaseApplication.getRetrofitAPI(false)
                .todayService(mobile)
                .enqueue(new Callback<TodayResponse>() {
                    @Override
                    public void onResponse(Call<TodayResponse> call, Response<TodayResponse> response) {
//                        mContext.dismissProgressDialog();

                        if (response != null) {

                            if (response.code() == 401) { // Unauthorised Access
                                NetworkCallController controller = new NetworkCallController();
                                controller.setListner(new NetworkResponseListner<LoginResponse>() {
                                    @Override
                                    public void onResponse(int reqCode, LoginResponse response) {
                                        // delete all previous record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().deleteAll();
                                        Realm.getDefaultInstance().commitTransaction();

                                        // add new record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().copyToRealmOrUpdate(response);
                                        Realm.getDefaultInstance().commitTransaction();
                                        getTodayService(requestCode, mobile);
                                    }

                                    @Override
                                    public void onFailure(int requestCode) {

                                    }
                                });
                                controller.refreshToken(100, getRefreshToken());
                            } else if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body());
                            } else {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
//                                    mContext.showServerError(jObjError.getString("Message"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            mContext.showServerError();
                        }
                    }

                    @Override
                    public void onFailure(Call<TodayResponse> call, Throwable t) {
//                        mContext.dismissProgressDialog();
//                        mContext.showServerError("Please try again !!!");
                    }
                });
    }


    public void postReferrals(final int requestCode, AddReferralRequest request) {
        mContext.showProgressDialog();
        BaseApplication.getRetrofitAPI(true)
                .postReferrals(request)
                .enqueue(new Callback<AddReferralResponse>() {
                    @Override
                    public void onResponse(Call<AddReferralResponse> call, Response<AddReferralResponse> response) {
                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body());
                            } else if (response.errorBody() != null) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    mContext.showServerError(jObjError.getString("ErrorMessage"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            mContext.showServerError();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddReferralResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Something went wrong, please try again !!!");
                    }
                });
    }

    public void getReferrals(final int requestCode, final GetReferralRequest request) {
        mContext.showProgressDialog();
        BaseApplication.getRetrofitAPI(true)
                .getReferrals(request)
                .enqueue(new Callback<GetReferralResponse>() {
                    @Override
                    public void onResponse(Call<GetReferralResponse> call,
                                           Response<GetReferralResponse> response) {
                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.code() == 401) { // Unauthorised Access
                                NetworkCallController controller = new NetworkCallController();
                                controller.setListner(new NetworkResponseListner<LoginResponse>() {
                                    @Override
                                    public void onResponse(int reqCode, LoginResponse response) {
                                        // delete all previous record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().deleteAll();
                                        Realm.getDefaultInstance().commitTransaction();

                                        // add new record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().copyToRealmOrUpdate(response);
                                        Realm.getDefaultInstance().commitTransaction();
                                        getReferrals(requestCode, request);
                                    }

                                    @Override
                                    public void onFailure(int requestCode) {

                                    }
                                });
                                controller.refreshToken(100, getRefreshToken());
                            } else if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body().getData());

                            } else if (response.errorBody() != null) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    mContext.showServerError(jObjError.getString("ErrorMessage"));

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            mContext.showServerError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetReferralResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Something went wrong, please try again !!!");
                    }
                });
    }

    public void getVoucherCode(final int requestCode, final VoucherRequest request) {
        mContext.showProgressDialog();

        BaseApplication.getRetrofitAPI(true)
                .getVoucherCode(request)
                .enqueue(new Callback<VoucherResponseMain>() {
                    @Override
                    public void onResponse(Call<VoucherResponseMain> call,
                                           Response<VoucherResponseMain> response) {
                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.code() == 401) { // Unauthorised Access
                                NetworkCallController controller = new NetworkCallController();
                                controller.setListner(new NetworkResponseListner<LoginResponse>() {
                                    @Override
                                    public void onResponse(int reqCode, LoginResponse response) {
                                        // delete all previous record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().deleteAll();
                                        Realm.getDefaultInstance().commitTransaction();

                                        // add new record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().copyToRealmOrUpdate(response);
                                        Realm.getDefaultInstance().commitTransaction();
                                        getVoucherCode(requestCode, request);
                                    }

                                    @Override
                                    public void onFailure(int requestCode) {

                                    }
                                });
                                controller.refreshToken(100, getRefreshToken());
                            } else if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body().getData());
                            } else {
                                if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            mContext.showServerError("error");
                        }
                    }

                    @Override
                    public void onFailure(Call<VoucherResponseMain> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }


    public void getComplaintTypes(final int requestCode) {
        mContext.showProgressDialog();

        BaseApplication.getRetrofitAPI(true)
                .getComplaintTypes()
                .enqueue(new Callback<ComplaintTypeResponse>() {
                    @Override
                    public void onResponse(Call<ComplaintTypeResponse> call,
                                           Response<ComplaintTypeResponse> response) {
                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.code() == 401) { // Unauthorised Access
                                NetworkCallController controller = new NetworkCallController();
                                controller.setListner(new NetworkResponseListner<LoginResponse>() {
                                    @Override
                                    public void onResponse(int reqCode, LoginResponse response) {
                                        // delete all previous record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().deleteAll();
                                        Realm.getDefaultInstance().commitTransaction();

                                        // add new record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().copyToRealmOrUpdate(response);
                                        Realm.getDefaultInstance().commitTransaction();
                                        getComplaintTypes(requestCode);
                                    }

                                    @Override
                                    public void onFailure(int requestCode) {

                                    }
                                });
                                controller.refreshToken(100, getRefreshToken());
                            } else if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body().getData());
                            } else {
                                if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            mContext.showServerError("error");
                        }
                    }

                    @Override
                    public void onFailure(Call<ComplaintTypeResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }


    public void getCreateComplaints(final int requestCode, final CreateComplaintRequest request) {
        mContext.showProgressDialog();

        BaseApplication.getRetrofitAPI(true)
                .getCreateComplaints(request)
                .enqueue(new Callback<CreateComplaintResponse>() {
                    @Override
                    public void onResponse(Call<CreateComplaintResponse> call,
                                           Response<CreateComplaintResponse> response) {
                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.code() == 401) { // Unauthorised Access
                                NetworkCallController controller = new NetworkCallController();
                                controller.setListner(new NetworkResponseListner<LoginResponse>() {
                                    @Override
                                    public void onResponse(int reqCode, LoginResponse response) {
                                        // delete all previous record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().deleteAll();
                                        Realm.getDefaultInstance().commitTransaction();

                                        // add new record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().copyToRealmOrUpdate(response);
                                        Realm.getDefaultInstance().commitTransaction();
                                        getCreateComplaints(requestCode, request);
                                    }

                                    @Override
                                    public void onFailure(int requestCode) {
                                    }
                                });
                                controller.refreshToken(100, getRefreshToken());
                            } else if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body());
                            } else {
                                if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            mContext.showServerError("error");
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateComplaintResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }


    public void getComplaintByCaseId(final int requestCode, final ComplaintCaseIdRequest request) {
        mContext.showProgressDialog();

        BaseApplication.getRetrofitAPI(true)
                .getComplaintsByCaseNumber(request)
                .enqueue(new Callback<ComplaintHistoryResponse>() {
                    @Override
                    public void onResponse(Call<ComplaintHistoryResponse> call,
                                           Response<ComplaintHistoryResponse> response) {
                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.code() == 401) { // Unauthorised Access
                                NetworkCallController controller = new NetworkCallController();
                                controller.setListner(new NetworkResponseListner<LoginResponse>() {
                                    @Override
                                    public void onResponse(int reqCode, LoginResponse response) {
                                        // delete all previous record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().deleteAll();
                                        Realm.getDefaultInstance().commitTransaction();

                                        // add new record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().copyToRealmOrUpdate(response);
                                        Realm.getDefaultInstance().commitTransaction();
                                        getComplaintByCaseId(requestCode, request);
                                    }

                                    @Override
                                    public void onFailure(int requestCode) {
                                    }
                                });
                                controller.refreshToken(100, getRefreshToken());
                            } else if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body().getData());
                            } else {
                                if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            mContext.showServerError("error");
                        }
                    }

                    @Override
                    public void onFailure(Call<ComplaintHistoryResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }

    public void getAllServices(final int requestCode, final MyServiceRequest request) {
//       mContext.showProgressDialog();
        BaseApplication.getRetrofitAPI(true)
                .getAllServiceRequest(request)
                .enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call,
                                           Response<ServiceResponse> response) {
//                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.code() == 401) { // Unauthorised Access
                                NetworkCallController controller = new NetworkCallController();
                                controller.setListner(new NetworkResponseListner<LoginResponse>() {
                                    @Override
                                    public void onResponse(int reqCode, LoginResponse response) {
                                        // delete all previous record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().deleteAll();
                                        Realm.getDefaultInstance().commitTransaction();

                                        // add new record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().copyToRealmOrUpdate(response);
                                        Realm.getDefaultInstance().commitTransaction();
                                        getAllServices(requestCode, request);
                                    }

                                    @Override
                                    public void onFailure(int requestCode) {
                                    }
                                });
                                controller.refreshToken(100, getRefreshToken());
                            } else if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body().getData());
                            } else {
                                if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
//                            mContext.showServerError("error");
                        }
                    }

                    @Override
                    public void onFailure(Call<ServiceResponse> call, Throwable t) {
//                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }


    public void getServiceList(final int requestCode, final ServiceRequest request) {
        mContext.showProgressDialog();
        BaseApplication.getRetrofitAPI(true)
                .getServiceList(request)
                .enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call,
                                           Response<ServiceResponse> response) {
                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.code() == 401) { // Unauthorised Access
                                NetworkCallController controller = new NetworkCallController();
                                controller.setListner(new NetworkResponseListner<LoginResponse>() {
                                    @Override
                                    public void onResponse(int reqCode, LoginResponse response) {
                                        // delete all previous record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().deleteAll();
                                        Realm.getDefaultInstance().commitTransaction();

                                        // add new record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().copyToRealmOrUpdate(response);
                                        Realm.getDefaultInstance().commitTransaction();
                                        getServiceList(requestCode, request);
                                    }

                                    @Override
                                    public void onFailure(int requestCode) {

                                    }
                                });
                                controller.refreshToken(100, getRefreshToken());
                            } else if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body().getData());

                            } else {
                                if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ServiceResponse> call, Throwable t) {
                        mContext.showServerError("Please try again !!!");
                        mContext.dismissProgressDialog();

                    }
                });
    }


    public void getAvailableSlots(final int requestCode, String taskId, String sDate, String eDate) {
        mContext.showProgressDialog();
        BaseApplication.getSlots()
                .getAvailableSlot(taskId, sDate, eDate)
                .enqueue(new Callback<SlotResponse>() {
                    @Override
                    public void onResponse(Call<SlotResponse> call, Response<SlotResponse> response) {
                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body().getTimeSlot());
                            } else if (response.errorBody() != null) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SlotResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");

                    }
                });
    }


    public void getOffers(final int requestCode) {
//        mContext.showProgressDialog();
        BaseApplication.getRetrofitAPI(true)
                .getOffers()
                .enqueue(new Callback<OfferResponse>() {
                    @Override
                    public void onResponse(Call<OfferResponse> call, Response<OfferResponse> response) {
//                        mContext.dismissProgressDialog();
                        if (response != null) {
                            if (response.code() == 401) { // Unauthorised Access
                                NetworkCallController controller = new NetworkCallController();
                                controller.setListner(new NetworkResponseListner<LoginResponse>() {
                                    @Override
                                    public void onResponse(int reqCode, LoginResponse response) {
                                        // delete all previous record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().deleteAll();
                                        Realm.getDefaultInstance().commitTransaction();

                                        // add new record
                                        Realm.getDefaultInstance().beginTransaction();
                                        Realm.getDefaultInstance().copyToRealmOrUpdate(response);
                                        Realm.getDefaultInstance().commitTransaction();
                                        getOffers(requestCode);
                                    }

                                    @Override
                                    public void onFailure(int requestCode) {

                                    }
                                });
                                controller.refreshToken(100, getRefreshToken());
                            } else if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body().getData());
                            } else if (response.errorBody() != null) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<OfferResponse> call, Throwable t) {
//                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");

                    }
                });
    }

}
