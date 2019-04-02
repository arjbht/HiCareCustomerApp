package com.arj.hicarehygiene.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;

import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.activities.HomeActivity;
import com.arj.hicarehygiene.adapter.Home_Cleaning_Adapter;
import com.arj.hicarehygiene.adapter.Home_Pest_Adapter;
import com.arj.hicarehygiene.databinding.FragmentHomeBinding;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.model.todayservice.TodayResponse;

import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment
        implements NetworkResponseListner {
    FragmentHomeBinding mfragmentHomeBinding;
    Home_Cleaning_Adapter mCleanigAdapter;
    Home_Pest_Adapter mPestAdapter;
    ArrayList<Integer> banner_array = new ArrayList<>();
    private static final int TODAY_REQUEST = 1000;

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
//        setHasOptionsMenu(true);
        getActivity().setTitle("Hicare Pest Control");
        CoordinatorLayout coordinate = getActivity().findViewById(R.id.coordinate);
        CoordinatorLayout coordinate_normal = getActivity().findViewById(R.id.coordinate1);
//        FrameLayout frame_normal = getActivity().findViewById(R.id.container1);
//        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation1);
        coordinate.setVisibility(View.VISIBLE);
        coordinate_normal.setVisibility(View.GONE);

//        getActivity().onPrepareOptionsMenu(true);

        banner_array.add(R.drawable.bannerpest);
        banner_array.add(R.drawable.bannerpestcntr);
        getBanner();
        setHasOptionsMenu(true);

        return mfragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mfragmentHomeBinding.lnrToday.setVisibility(View.GONE);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mfragmentHomeBinding.recycle1.setLayoutManager(layoutManager);
        mfragmentHomeBinding.recycle1.setHasFixedSize(true);
        mCleanigAdapter = new Home_Cleaning_Adapter();
        mfragmentHomeBinding.recycle1.setAdapter(mCleanigAdapter);

        LinearLayoutManager layoutManager2 =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mfragmentHomeBinding.recycle2.setLayoutManager(layoutManager2);
        mfragmentHomeBinding.recycle2.setHasFixedSize(true);
        mPestAdapter = new Home_Pest_Adapter();
        mfragmentHomeBinding.recycle2.setAdapter(mPestAdapter);

        getTodaysService();
    }

    private void getTodaysService() {

        NetworkCallController controller = new NetworkCallController();
        controller.setListner(new NetworkResponseListner<List<TodayResponse>>() {
            @Override
            public void onResponse(int requestCode, List<TodayResponse> items) {

                if (items != null) {
                    mfragmentHomeBinding.lnrToday.setVisibility(View.VISIBLE);
                    String Order_No = items.get(0).getData().get(0).getOrder_No();
                    String Service_Plan = items.get(0).getData().get(0).getService_Plan();
                    String Service_Step = items.get(0).getData().get(0).getService_Step();
                    String Slot_time = items.get(0).getData().get(0).getTodaysDetails().get(0).getSlot_Time();

                    mfragmentHomeBinding.txtOrderno.setText(Order_No);
                    mfragmentHomeBinding.txtServicename.setText(Service_Plan);
                    mfragmentHomeBinding.txtSlot.setText(Slot_time);
                    mfragmentHomeBinding.serviceStep.setText(Service_Step);
                }
            }

            @Override
            public void onFailure(int requestCode) {

            }
        });
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> LoginRealmModels =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                String Mobile = LoginRealmModels.get(0).getPhoneNumber();
                controller.getTodayService(TODAY_REQUEST, Mobile);
            }
        }

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem cart = menu.findItem(R.id.cart);
        MenuItem search = menu.findItem(R.id.search);
        MenuItem filter = menu.findItem(R.id.filter);

        cart.setVisible(true);
        search.setVisible(true);
        filter.setVisible(false);
    }
    //    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_home, menu);
//        getActivity().invalidateOptionsMenu();
//        mfragmentHomeBinding.getRoot().invalidate();
//        mfragmentHomeBinding.getRoot().requestLayout();
//        super.onCreateOptionsMenu(menu, inflater);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.cart:
//                Log.i("item id ", item.getItemId() + "");
//                Intent intent = new Intent(getActivity(), CartActivity.class);
//                getActivity().startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                return false;
//
//            case R.id.wallet:
//
//
//                return false;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private void getBanner() {
        for (int i = 0; i < banner_array.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description("")
                    .image(banner_array.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.bundle(new Bundle());
            mfragmentHomeBinding.slider.addSlider(textSliderView);

        }
        mfragmentHomeBinding.slider.setPresetTransformer(SliderLayout.Transformer.Tablet);
        mfragmentHomeBinding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mfragmentHomeBinding.slider.setCustomAnimation(new DescriptionAnimation());
        mfragmentHomeBinding.slider.setDuration(4000);
    }


    @Override
    public void onResponse(int requestCode, Object response) {

    }

    @Override
    public void onFailure(int requestCode) {

    }
}
