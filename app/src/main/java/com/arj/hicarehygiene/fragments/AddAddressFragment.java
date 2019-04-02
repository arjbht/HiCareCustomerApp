package com.arj.hicarehygiene.fragments;


import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Address;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.FragmentAddAddressBinding;
import com.arj.hicarehygiene.service.LocationManager;
import com.arj.hicarehygiene.service.listner.LocationManagerListner;

import com.arj.hicarehygiene.utils.Notifier;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAddressFragment extends BaseFragment implements GoogleMap.OnMarkerClickListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, OnMapReadyCallback, LocationManagerListner {
    FragmentAddAddressBinding mfragmentAddAddressBinding;
    SupportMapFragment supportMapFragment;
    private GoogleMap mMap;
    double source_address_lat, source_address_long;
    int ConstZoomValue = 15;
    String source_address;
    String lat = "";
    String lng = "";
    private Location mLocation;
    private LocationManagerListner mListner;

    public AddAddressFragment() {
        // Required empty public constructor
    }

    public static AddAddressFragment newInstance() {
        Bundle args = new Bundle();
        AddAddressFragment fragment = new AddAddressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mfragmentAddAddressBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_add_address, container, false);
//        GPSTracker gpsTracker = new GPSTracker(getActivity());
        // Inflate the layout for this fragment
        getActivity().setTitle("Add Address");
        supportMapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        LocationManager.Builder builder = new LocationManager.Builder(getActivity());
        builder.setLocationListner(this);
        builder.build();
        return mfragmentAddAddressBinding.getRoot();
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

    @Override
    public boolean onMarkerClick(Marker marker) {

        return true;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        if (source_address_lat != 0.0 && source_address_long != 0.0) {
            if (isNetworkConnected()) {

                setAddress(source_address_lat, source_address_long);
                setupDefaultScreen();
            } else {
                Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {


                if (getmLocation() != null) {
                    Location location = getmLocation();
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                    MarkerOptions mo = new MarkerOptions()
                            .position(latLng)
                            .flat(true)
                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.ic_placeholder, "")));
                    Marker mkr = mMap.addMarker(mo);
                    mkr.showInfoWindow();


                } else {
//                    Notifier.make(this, "Location not Found", Notifier.LENGTH_INDEFINITE)
//                            .setAction("Retry", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    onMapLoaded();
//                                }
//                            })
//                            .show();
                    showLocationPopUp();
                    setLocationListner(mListner);
                }
            }
        });

        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
//                try {
//                    LatLng latlong = googleMap.getProjection().getVisibleRegion().latLngBounds.getCenter();
//                    if (latlong.latitude != 0) {
//                        source_address_lat = latlong.latitude;
//                        source_address_long = latlong.longitude;
//                        setAddress(source_address_lat, source_address_long);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                if (getmLocation() != null) {
                    Location location = getmLocation();
                    source_address_lat = location.getLatitude();
                    source_address_long = location.getLongitude();
                    setAddress(source_address_lat, source_address_long);
                } else {
                    showLocationPopUp();
                    setLocationListner(mListner);
                }
            }
        });
    }

    private String setAddress(double lat, double long_temp) {

        String addressstr = "Select location";

        try {
            Geocoder geocoder;
            List<Address> addresses;
            Address address = null;
            geocoder = new Geocoder(getActivity(), Locale.getDefault());

            addresses = geocoder.getFromLocation(lat, long_temp, 1);
            address = addresses.get(0);
            try {

                if (address.getMaxAddressLineIndex() == 0) {
                    addressstr = address.getAddressLine(0);
                    source_address = addressstr;


                } else if (address.getMaxAddressLineIndex() == 1) {
                    String localAddress = address.getAddressLine(1);
                    String place2 = localAddress + " , " + address.getLocality() + "," + address.getCountryName();
                } else if (address.getMaxAddressLineIndex() == 2) {

                    String localAddress = address.getAddressLine(1);
                    String MiddleAddress = address.getAddressLine(2);
                    addressstr = localAddress + " , " + MiddleAddress;

                } else {
                    String localAddress = address.getAddressLine(1);
                    String partsaa[] = localAddress.split(",");
                    String partd = partsaa[1];
                    String MiddleAddress = address.getAddressLine(2);
                    String parts[] = MiddleAddress.split(",");
                    String part = parts[0];
                    addressstr = partd + " , " + part;
                }
                mfragmentAddAddressBinding.edtLocation.setText(addressstr);

            } catch (Exception e) {
                e.printStackTrace();

            }

        } catch (Exception e) {
            e.printStackTrace();
//            new GetClass().execute();
        }

        return addressstr;
    }


    private void setupDefaultScreen() {

        if (getmLocation() != null) {
            Location location = getmLocation();
            source_address_lat = location.getLatitude();
            source_address_long = location.getLongitude();
            LatLng latLng = new LatLng(source_address_lat, source_address_long);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, ConstZoomValue), 1000, null);
            mMap.getUiSettings().setRotateGesturesEnabled(false);
        } else {

            Notifier.make(this, "Location not Found", Notifier.LENGTH_INDEFINITE)
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setupDefaultScreen();
                        }
                    })
                    .show();
        }
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            return false;
        } else
            return true;
    }

    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId, String tittle) {

        View customMarkerView = ((LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_driver, null);
        ImageView markerImageView = customMarkerView.findViewById(R.id.profile_imageaa);
        TextView txtTittle = customMarkerView.findViewById(R.id.txtTittle);
        txtTittle.setText(tittle);
        markerImageView.setImageResource(resId);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

    @Override
    public void locationFetched(Location mLocation, Location oldLocation, String time, String locationProvider) {
        this.mLocation = mLocation;
        if (mListner != null) {
            mListner.locationFetched(mLocation, oldLocation, time, locationProvider);
        }
    }

    public Location getmLocation() {
        return mLocation;
    }

    public void showLocationPopUp() {

        LocationManager.Builder builder = new LocationManager.Builder(getActivity());
        builder.setLocationListner(this);
        builder.build();
    }

    public void setLocationListner(LocationManagerListner locationListner) {
        this.mListner = locationListner;
    }

}
