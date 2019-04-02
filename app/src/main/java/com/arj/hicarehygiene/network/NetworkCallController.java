package com.arj.hicarehygiene.network;

import org.json.JSONObject;

import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.BaseFragment;
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
import com.arj.hicarehygiene.viewmodel.SignUpUserViewModel;
import com.arj.hicarehygiene.viewmodel.UserLoginViewModel;
import io.realm.Realm;
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
                .enqueue(new Callback<CreateUserResonse>() {
                    @Override
                    public void onResponse(Call<CreateUserResonse> call,
                                           Response<CreateUserResonse> response) {
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
                    public void onFailure(Call<CreateUserResonse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }

    public void verifyOtp(final int requestCode, UserLoginViewModel model) {
        mContext.showProgressDialog();
//        VerifyOtpRequest request = new VerifyOtpRequest();
        VerifyOtpRequest request = new VerifyOtpRequest();
        String id = model.getId();
        String code = model.getOtp();
        request.setCode(model.getOtp());
        request.setUserId(model.getId());

        BaseApplication.getRetrofitAPI(false).verifyOtp(request).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
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
            public void onFailure(Call<String> call, Throwable t) {
                mContext.dismissProgressDialog();
                mContext.showServerError("Please try again !!!");
            }
        });
    }

    public void verify(final int requestCode, final String code, final String userid) {
        mContext.showProgressDialog();
        BaseApplication.getRetrofitAPI(false)
                .verify(code, userid)
                .enqueue(new Callback<VerifyOtpRequest>() {
                    @Override
                    public void onResponse(Call<VerifyOtpRequest> call, Response<VerifyOtpRequest> response) {
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
                    public void onFailure(Call<VerifyOtpRequest> call, Throwable t) {
                        mListner.onFailure(requestCode);
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

        BaseApplication.getRetrofitAPI(true)
                .getOrderList(request)
                .enqueue(new Callback<GetOrderResponse>() {
                    @Override
                    public void onResponse(Call<GetOrderResponse> call,
                                           Response<GetOrderResponse> response) {
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
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }


    public void getComplaintList(final int requestCode, final ComplaintHistoryRequest request) {

        BaseApplication.getRetrofitAPI(true)
                .getComplaintList(request)
                .enqueue(new Callback<ComplaintHistoryResponse>() {
                    @Override
                    public void onResponse(Call<ComplaintHistoryResponse> call,
                                           Response<ComplaintHistoryResponse> response) {
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
                            mContext.showServerError("error");
                        }
                    }

                    @Override
                    public void onFailure(Call<ComplaintHistoryResponse> call, Throwable t) {
                        mContext.showServerError("Please try again !!!");
                    }
                });
    }

    public void getServiceList(final int requestCode, final ServiceRequest request) {

        BaseApplication.getRetrofitAPI(true)
                .getServiceList(request)
                .enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call,
                                           Response<ServiceResponse> response) {
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
//                                        mContext.showServerError(jObjError.getString("Message"));
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
//                        mContext.showServerError("Please try again !!!");
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


}
