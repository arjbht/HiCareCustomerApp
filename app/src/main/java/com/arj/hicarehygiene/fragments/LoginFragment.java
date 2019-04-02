package com.arj.hicarehygiene.fragments;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import com.arj.hicarehygiene.handler.UserLoginClickHandler;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.VerifyOtpRequest;
import com.arj.hicarehygiene.utils.SharedPreferencesUtility;
import com.arj.hicarehygiene.viewmodel.UserLoginViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements UserLoginClickHandler {
    FragmentLoginBinding mfragmentLoginBinding;
    AlertDialog alertDialog;
    private static final int OTP_REQUEST = 1000;
    private static final int LOGIN_REQUST = 2000;
    String id = "";
    String code = "";
    private static String OTP = "";

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
        Broadcast receiver = new Broadcast();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(receiver,
                new IntentFilter(Broadcast.PROCESS_RESPONSE));

        mfragmentLoginBinding.setModel(new UserLoginViewModel());

        mfragmentLoginBinding.setHandler(this);

        String text = "<font color=#000000>Did't get the OTP?</font> <font color=#2bb77a> Resend OTP</font>";
        mfragmentLoginBinding.txtResend.setText(Html.fromHtml(text));

        mfragmentLoginBinding.buttonSignIn.setText("SEND OTP");


        if (!OTP.equals("")) {
            mfragmentLoginBinding.edtOtp.setText(OTP);
        }

//        new CountDownTimer(20000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                mfragmentLoginBinding.timerValue.setText(new SimpleDateFormat("mm:ss").format(new Date(millisUntilFinished)));
//            }
//
//            public void onFinish() {
//                mfragmentLoginBinding.txtSend.setVisibility(View.VISIBLE);
//                mfragmentLoginBinding.txtSend.setText("RESEND");
//            }
//        }.start();

//        new CountDownTimer(20000, 1000) {//CountDownTimer(edittext1.getText()+edittext2.getText()) also parse it to long
//
//            @TargetApi(Build.VERSION_CODES.N)
//            public void onTick(long millisUntilFinished) {
//                mfragmentLoginBinding.timerValue.setText(toIntExact(millisUntilFinished / 1000));
//                //here you can have your logic to set text to edittext
//            }
//
//            public void onFinish() {
//                mfragmentLoginBinding.txtSend.setVisibility(View.VISIBLE);
//                mfragmentLoginBinding.txtSend.setText("RESEND");
//            }
//        }
//                .start();
//        mfragmentLoginBinding.editTextUsername.addTextChangedListener(new TextWatcher() {
//            private Timer timer = new Timer();
//            private final long DELAY = 2000;
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                timer.cancel();
//                timer = new Timer();
//                timer.schedule(
//                        new TimerTask() {
//                            @Override
//                            public void run() {
//                                if (!mfragmentLoginBinding.editTextUsername.getText().equals("") && mfragmentLoginBinding.editTextUsername.getText().toString().length() == 10)
//                                    getOtp();
//                            }
//                        },
//                        DELAY
//                );
//
//
//            }
//        });
        if (mfragmentLoginBinding.buttonSignIn.getText().toString().equals("SEND OTP")) {
            mfragmentLoginBinding.lnrOtp.setVisibility(View.GONE);

        } else {
            mfragmentLoginBinding.lnrOtp.setVisibility(View.VISIBLE);

        }


        return mfragmentLoginBinding.getRoot();
    }

    private void getOtp() {
        final UserLoginViewModel userLoginViewModel = mfragmentLoginBinding.getModel();
        NetworkCallController controller = new NetworkCallController(LoginFragment.this);

        controller.setListner(new NetworkResponseListner<LoginResponse>() {

            @Override
            public void onResponse(int requestCode, LoginResponse response) {
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

        if (mfragmentLoginBinding.buttonSignIn.getText().toString().equals("SEND OTP")) {
            getOtp();
            mfragmentLoginBinding.buttonSignIn.setText("LOGIN");
            mfragmentLoginBinding.lnrOtp.setVisibility(View.VISIBLE);

        } else if (mfragmentLoginBinding.buttonSignIn.getText().toString().equals("LOGIN")) {

            final UserLoginViewModel userLoginViewModel = mfragmentLoginBinding.getModel();
            NetworkCallController controller = new NetworkCallController(this);
            controller.setListner(new NetworkResponseListner<VerifyOtpRequest>() {

                @Override
                public void onResponse(int requestCode, VerifyOtpRequest response) {

                    if (isVisible()) {
                        SharedPreferencesUtility.savePrefBoolean(getActivity(),
                                SharedPreferencesUtility.IS_USER_LOGIN, true);
                        startActivity(new Intent(getActivity(), HomeActivity.class));
                        getActivity().finish();
                    }
                }

                @Override
                public void onFailure(int requestCode) {

                }
            });
            controller.verify(LOGIN_REQUST, userLoginViewModel.getId(), userLoginViewModel.getOtp());
        }


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

    public static class Broadcast extends BroadcastReceiver {
        public static final String PROCESS_RESPONSE = "android.provider.Telephony.SMS_RECEIVED";

        public void onReceive(Context context, Intent intent) {

            final Bundle bundle = intent.getExtras();

            try {

                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    for (int i = 0; i < pdusObj.length; i++) {

                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                        String senderNum = phoneNumber;
                        String message = currentMessage.getDisplayMessageBody();

                        String[] parts = message.split(" ");
                        String lastWord = parts[parts.length - 1];

                        String otp = lastWord.substring(0, 6);

                        OTP = otp;

                        Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);


                    }
                }
            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" + e);

            }
        }


    }

}
