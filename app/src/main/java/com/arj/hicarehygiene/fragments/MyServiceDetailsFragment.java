package com.arj.hicarehygiene.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.adapter.ComplaintHistoryAdapter;
import com.arj.hicarehygiene.adapter.MyServiceViewDetailsAdapter;
import com.arj.hicarehygiene.databinding.FragmentMyServiceDetailsBinding;
import com.arj.hicarehygiene.network.model.userservices.ServiceTasks;
import com.arj.hicarehygiene.network.model.userservices.Service_Details;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyServiceDetailsFragment extends BaseFragment {
    FragmentMyServiceDetailsBinding mFragmentMyServiceDetailsBinding;
    private static final String ARG_TASK = "ARG_TASK";
    private List<ServiceTasks> tasksList = null;
    RecyclerView.LayoutManager layoutManager;
    private MyServiceViewDetailsAdapter mAdapter;

    public MyServiceDetailsFragment() {
        // Required empty public constructor
    }

    public static MyServiceDetailsFragment newInstance(List<ServiceTasks> service_details) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_TASK, (ArrayList<? extends Parcelable>) service_details);
        MyServiceDetailsFragment fragment = new MyServiceDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            tasksList = getArguments().getParcelableArrayList(ARG_TASK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentMyServiceDetailsBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_my_service_details, container, false);
        getActivity().setTitle("My Service Details");
        return mFragmentMyServiceDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentMyServiceDetailsBinding.recycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentMyServiceDetailsBinding.recycleView.setLayoutManager(layoutManager);
        mAdapter = new MyServiceViewDetailsAdapter(tasksList);
        mFragmentMyServiceDetailsBinding.recycleView.setAdapter(mAdapter);
    }


}
