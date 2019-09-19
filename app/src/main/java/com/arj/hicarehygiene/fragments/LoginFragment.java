package com.arj.hicarehygiene.fragments;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.activities.HomeActivity;
import com.arj.hicarehygiene.databinding.FragmentLoginBinding;
import com.arj.hicarehygiene.handler.OtpReceivedInterface;
import com.arj.hicarehygiene.handler.UserLoginClickHandler;
import com.arj.hicarehygiene.network.model.BasicResponse;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.VerifyOtpRequest;
import com.arj.hicarehygiene.utils.AppSignatureHelper;
import com.arj.hicarehygiene.utils.SMSListener;
import com.arj.hicarehygiene.utils.SharedPreferencesUtility;
import com.arj.hicarehygiene.utils.notifications.OneSIgnalHelper;
import com.arj.hicarehygiene.viewmodel.UserLoginViewModel;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements UserLoginClickHandler, GoogleApiClient.ConnectionCallbacks,
        OtpReceivedInterface, GoogleApiClient.OnConnectionFailedListener {
    FragmentLoginBinding mfragmentLoginBinding;
    AlertDialog alertDialog;
    private static final int OTP_REQUEST = 1000;
    private static final int LOGIN_REQUST = 2000;
    String id = "";
    String code = "";
    private static String OTP = "";
    SMSListener mSmsBroadcastReceiver;
    GoogleApiClient mGoogleApiClient;


    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mfragmentLoginBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        AppSignatureHelper appSignatureHelper = new AppSignatureHelper(getActivity());
        appSignatureHelper.getAppSignatures();

        mSmsBroadcastReceiver = new SMSListener();
        //set google api client for hint request
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

        mSmsBroadcastReceiver.setOnOtpListeners(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
        getActivity().registerReceiver(mSmsBroadcastReceiver, intentFilter);
        getActivity().setTitle("");
//        getHintPhoneNumber();
        startSMSListener();


        mfragmentLoginBinding.setModel(new UserLoginViewModel());
        mfragmentLoginBinding.setHandler(this);

        mfragmentLoginBinding.txtResend.setVisibility(View.GONE);
        String text = "<font color=#000000>Did't get the OTP?</font> <font color=#217d55> Resend OTP</font>";
        mfragmentLoginBinding.txtResend.setText(Html.fromHtml(text));

        mfragmentLoginBinding.buttonSignIn.setText("LOGIN");


        if (!OTP.equals("")) {
            mfragmentLoginBinding.edtOtp.setText(OTP);
        }

        if (mfragmentLoginBinding.buttonSignIn.getText().toString().equals("LOGIN")) {
            mfragmentLoginBinding.lnrOtp.setVisibility(View.GONE);

        } else {
            mfragmentLoginBinding.lnrOtp.setVisibility(View.VISIBLE);

        }


        return mfragmentLoginBinding.getRoot();
    }


    public void startSMSListener() {
        SmsRetrieverClient mClient = SmsRetriever.getClient(getActivity());
        Task<Void> mTask = mClient.startSmsRetriever();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Log.e("TAG_OTP", "SMS Retriever starts");
            }
        });
        mTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG_OTP", "Error");
            }
        });
    }

    private void getOtp() {
        final UserLoginViewModel userLoginViewModel = mfragmentLoginBinding.getModel();
        NetworkCallController controller = new NetworkCallController(LoginFragment.this);

        controller.setListner(new NetworkResponseListner<LoginResponse>() {

            @Override
            public void onResponse(int requestCode, LoginResponse response) {

                mfragmentLoginBinding.txtResend.setVisibility(View.VISIBLE);
                // delete all previous record
                getRealm().beginTransaction();
                getRealm().deleteAll();
                getRealm().commitTransaction();

                // add new record
                getRealm().beginTransaction();
                getRealm().copyToRealmOrUpdate(response);
                getRealm().commitTransaction();
                if (isVisible()) {
                    if (response.getIsPhoneNumberConfirmed().equals("False")) {
                        Toast.makeText(getActivity(), response.getOTP(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), response.getOTP() + "", Toast.LENGTH_SHORT).show();
                        if (response.getOTP() != null) {
                            mfragmentLoginBinding.lnrOtp.setVisibility(View.VISIBLE);
                            mfragmentLoginBinding.buttonSignIn.setText("VERIFY");
                            id = response.getUserID();
                            code = response.getOTP();
                            mfragmentLoginBinding.getModel().setId(id);
                            mfragmentLoginBinding.getModel().setOtp(code);
                        }
                    }
                }

            }

            @Override
            public void onFailure(int requestCode) {

            }
        });
        controller.login(OTP_REQUEST, userLoginViewModel.getUsername(),
                userLoginViewModel.getOtp());
    }

    @Override
    public void onLoginClicked(View view) {

        if (mfragmentLoginBinding.editTextUsername.getText().toString().length() < 10) {
            mfragmentLoginBinding.editTextUsername.setError("Invalid mobile no.");
        } else if (mfragmentLoginBinding.editTextUsername.getText().toString().length() == 0) {
            mfragmentLoginBinding.editTextUsername.setError("Please enter mobile no.");
        } else {
            if (mfragmentLoginBinding.buttonSignIn.getText().toString().equals("LOGIN")) {
                getOtp();
            } else {
                if (mfragmentLoginBinding.edtOtp.getText().toString().length() == 0 || mfragmentLoginBinding.edtOtp.getText().toString().length() < 6) {
                    Toast.makeText(getActivity(), "Invalid OTP.", Toast.LENGTH_SHORT).show();
                } else {
                    verifyOTP();
                }

            }

        }


    }

    private void verifyOTP() {

        final UserLoginViewModel userLoginViewModel = mfragmentLoginBinding.getModel();
        NetworkCallController controller = new NetworkCallController(this);
        OneSIgnalHelper oneSIgnalHelper = new OneSIgnalHelper(getActivity());
        String mStrPlayerId = oneSIgnalHelper.getmStrUserID();
        VerifyOtpRequest request = new VerifyOtpRequest();
        request.setCode(userLoginViewModel.getOtp());
        request.setUserId(userLoginViewModel.getId());
        request.setPlayerId(mStrPlayerId);
        controller.setListner(new NetworkResponseListner() {
            @Override
            public void onResponse(int requestCode, Object data) {
                BasicResponse response = (BasicResponse) data;
                if (response.getSuccess()) {
                    SharedPreferencesUtility.savePrefBoolean(getActivity(),
                            SharedPreferencesUtility.IS_USER_LOGIN, true);
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int requestCode) {

            }
        });
        controller.verify(LOGIN_REQUST, request);
    }

    @Override
    public void onForgotPasswordClicked(View view) {
        showAlert();
    }

    @Override
    public void onRegisterClicked(View view) {
        replaceFragment(RegistrationFragment.newInstance(), "LoginFrament-RegistrationFragment");
    }

    @Override
    public void onResendOtpClicked(View view) {
        final UserLoginViewModel userLoginViewModel = mfragmentLoginBinding.getModel();
        NetworkCallController controller = new NetworkCallController(this);
        controller.setListner(new NetworkResponseListner<LoginResponse>() {

            @Override
            public void onResponse(int requestCode, LoginResponse response) {
                if (isVisible()) {
                    if (response.getOTP() != null) {
                        id = response.getUserID();
                        code = response.getOTP();
                        mfragmentLoginBinding.getModel().setId(id);
                        mfragmentLoginBinding.getModel().setOtp(code);
                        startSMSListener();
                    }
                }
            }

            @Override
            public void onFailure(int requestCode) {

            }
        });
        controller.resendOtp(LOGIN_REQUST, userLoginViewModel.getId());
    }

    private void showAlert() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.customdialog, null);
        dialogBuilder.setView(dialogView);
//        alertDialog.getWindow().setWindowAnimations(R.style.DialogTheme);
        final TextView btn_submit = (TextView) dialogView.findViewById(R.id.btn_submit);
        final TextView btn_cancel = (TextView) dialogView.findViewById(R.id.btn_cancel);
        final EditText edt_mobile = (EditText) dialogView.findViewById(R.id.edittextmobile);

        if (mfragmentLoginBinding.editTextUsername.getText().toString().length() >= 0) {
            edt_mobile.setText(mfragmentLoginBinding.editTextUsername.getText().toString());
        } else {
            edt_mobile.setText("");

        }

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }

        });

        dialogBuilder.setCancelable(false);
        alertDialog = dialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialog.getWindow().setLayout(400, 400); //Controlling width and height.
        alertDialog.show();

    }

    @Override
    public void onOtpReceived(String otp) {
        Log.e("Otp Received", otp);
        mfragmentLoginBinding.edtOtp.setText(otp);
        if (mfragmentLoginBinding.edtOtp.length() == 6) {
            verifyOTP();
        }
    }

    @Override
    public void onOtpTimeout() {
        Log.v("SMSListener", "TimeOutException");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
