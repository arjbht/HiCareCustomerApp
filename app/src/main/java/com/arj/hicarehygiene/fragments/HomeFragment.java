package com.arj.hicarehygiene.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arj.hicarehygiene.activities.ComplaintActivity;
import com.arj.hicarehygiene.activities.DashboardActivity;
import com.arj.hicarehygiene.activities.MyServiceDetailActivity;
import com.arj.hicarehygiene.handler.UserHomeClickHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.FragmentHomeBinding;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements UserHomeClickHandler {
    FragmentHomeBinding mfragmentHomeBinding;
    ArrayList<Integer> banner_array = new ArrayList<>();
    private BlockingDeque<String> queue;
    ConnectionFactory factory;
    private static final String QUEUE_NAME = "ARJUN";

    Thread subscribeThread;
    Thread publishThread;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mfragmentHomeBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        getActivity().setTitle("Hicare Pest Control");
        queue = new LinkedBlockingDeque<String>();
        factory = new ConnectionFactory();
        setupConnectionFactory();
        try {
            queue.putLast("123");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        subscribe(incomingMessageHandler);
//        publishToAMQP();

        publishHandler();


        mfragmentHomeBinding.setHandler(this);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        banner_array.add(R.drawable.bannerpest);
        banner_array.add(R.drawable.bannerpestcntr);
        setHasOptionsMenu(true);

        return mfragmentHomeBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        publishHandler();
    }

    private void publishHandler() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                publishToAMQP();
            }
        }, 1000);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RealmResults<LoginResponse> LoginRealmModels =
                BaseApplication.getRealm().where(LoginResponse.class).findAll();
        if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
            mfragmentHomeBinding.txtName.setText(LoginRealmModels.get(0).getFirstName() + " " + LoginRealmModels.get(0).getLastName());
            mfragmentHomeBinding.txtNumber.setText(LoginRealmModels.get(0).getPhoneNumber());
        }
    }

    private void setupConnectionFactory() {
        factory.setAutomaticRecoveryEnabled(false);
        factory.setHost("52.74.65.15");
        factory.setUsername("hicare");
        factory.setPassword("hicare");
        factory.setPort(5672);
    }

//    public void publishToAMQP() {
//        publishThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Connection connection = factory.newConnection();
//                        Channel ch = connection.createChannel();
//
//                        ch.exchangeDeclare("Arjun Bhatt", "amq.fanout");
//                        ch.confirmSelect();
//
//                        while (true) {
//                            String message = queue.takeFirst();
//                            try {
//                                ch.basicPublish("HiCare", "", null, message.getBytes());
//                                Log.d("queue", "[s] " + message);
//                                ch.waitForConfirmsOrDie();
//                            } catch (Exception e) {
//                                Log.d("queue", "[f] " + message);
//                                queue.putFirst(message);
//                                throw e;
//                            }
//                        }
//                    } catch (InterruptedException e) {
//                        break;
//                    } catch (Exception e) {
//                        Log.d("", "Connection broken: " + e.getClass().getName());
//                        try {
//                            Thread.sleep(5000); //sleep and then try again
//                        } catch (InterruptedException e1) {
//                            break;
//                        }
//                    }
//                }
//            }
//        });
//        publishThread.start();
//    }

    public void publishToAMQP()
    {
        publishThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Connection connection = factory.newConnection();
                        Channel ch = connection.createChannel();
                        ch.confirmSelect();
                        ch.queueDeclare(QUEUE_NAME,false,false,false,null);

                        while (true) {
                            String message = queue.takeFirst();
                            try{
                                ch.basicPublish("", QUEUE_NAME, null, message.getBytes());
                                Log.d("queue", "[sp] " + message);
                                ch.waitForConfirmsOrDie();
//                                connection.close();
                            } catch (Exception e){
                                Log.d("queue","[f] " + message);
                                queue.putFirst(message);
                                throw e;
                            }
                        }
                    } catch (InterruptedException e) {
                        break;
                    } catch (Exception e) {
                        Log.d("queue", "Connection broken: " + e.getClass().getName());
                        try {
                            Thread.sleep(5000); //sleep and then try again
                        } catch (InterruptedException e1) {
                            break;
                        }
                    }
                }
            }
        });
        publishThread.start();
    }


//    private void getTodaysService() {
//
//        NetworkCallController controller = new NetworkCallController();
//        controller.setListner(new NetworkResponseListner<List<TodayResponse>>() {
//            @Override
//            public void onResponse(int requestCode, List<TodayResponse> items) {
//
//                if (items != null) {
//                    mfragmentHomeBinding.lnrToday.setVisibility(View.VISIBLE);
//                    String Order_No = items.get(0).getData().get(0).getOrder_No();
//                    String Service_Plan = items.get(0).getData().get(0).getService_Plan();
//                    String Service_Step = items.get(0).getData().get(0).getService_Step();
//                    String Slot_time = items.get(0).getData().get(0).getTodaysDetails().get(0).getSlot_Time();
//
//                    mfragmentHomeBinding.txtOrderno.setText(Order_No);
//                    mfragmentHomeBinding.txtServicename.setText(Service_Plan);
//                    mfragmentHomeBinding.txtSlot.setText(Slot_time);
//                    mfragmentHomeBinding.serviceStep.setText(Service_Step);
//                }
//            }
//
//            @Override
//            public void onFailure(int requestCode) {
//
//            }
//        });
//        if ((HomeActivity) getActivity() != null) {
//            RealmResults<LoginResponse> LoginRealmModels =
//                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
//            if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
//                String Mobile = LoginRealmModels.get(0).getPhoneNumber();
//                controller.getTodayService(TODAY_REQUEST, Mobile);
//            }
//        }
//
//    }

    @Override
    public void onTrackServiceClicked(View view) {
        startActivity(new Intent(getActivity(), DashboardActivity.class).putExtra(DashboardActivity.ARG_DASHBOARD, "track"));
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void onMyServiceClicked(View view) {
        startActivity(new Intent(getActivity(), MyServiceDetailActivity.class).putExtra(DashboardActivity.ARG_DASHBOARD, "service"));
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    @Override
    public void onAddReferralClicked(View view) {
        startActivity(new Intent(getActivity(), DashboardActivity.class).putExtra(DashboardActivity.ARG_DASHBOARD, "referral"));
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void onComplaintClicked(View view) {
        startActivity(new Intent(getActivity(), ComplaintActivity.class));
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void onVoucherClicked(View view) {
        startActivity(new Intent(getActivity(), DashboardActivity.class).putExtra(DashboardActivity.ARG_DASHBOARD, "voucher"));
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
