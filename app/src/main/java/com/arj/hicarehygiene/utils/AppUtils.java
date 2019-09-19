package com.arj.hicarehygiene.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.arj.hicarehygiene.R;

import org.joda.time.DateTime;


public class AppUtils {


    public class LocationConstants {
        public static final int SUCCESS_RESULT = 0;

        public static final int FAILURE_RESULT = 1;

        public static final String PACKAGE_NAME = "com.sample.sishin.maplocation";

        public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";

        public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";

        public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";

        public static final String LOCATION_DATA_AREA = PACKAGE_NAME + ".LOCATION_DATA_AREA";
        public static final String LOCATION_DATA_CITY = PACKAGE_NAME + ".LOCATION_DATA_CITY";
        public static final String LOCATION_DATA_STREET = PACKAGE_NAME + ".LOCATION_DATA_STREET";


    }


    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    public static String getDays(int day) {
        String days = "";
        switch (day) {
            case 1:
                days = "MON";
                break;

            case 2:
                days = "TUE";
                break;

            case 3:
                days = "WED";
                break;

            case 4:
                days = "THU";
                break;

            case 5:
                days = "FRI";
                break;

            case 6:
                days = "SAT";
                break;

            case 7:
                days = "SUN";
                break;

            default:
                days = "NA";
                break;
        }

        return days;
    }



    public static String getMonths(int month) {
        String months = "";
        switch (month) {
            case 1:
                months = "January";
                break;

            case 2:
                months = "February";
                break;

            case 3:
                months = "March";
                break;

            case 4:
                months = "April";
                break;

            case 5:
                months = "May";
                break;

            case 6:
                months = "June";
                break;

            case 7:
                months = "July";
                break;

            case 8:
                months= "August";
                break;

            case 9:
                months = "September";
                break;

            case 10:
                months = "October";
                break;

            case 11:
                months = "November";
                break;

            case 12:
                months = "December";
                break;

            default:
                months = "NA";
                break;
        }

        return months;
    }


    public static Integer getImageByCode(String type) {
        int image = 0;
        switch (type) {
            case "CMS":

                image = R.drawable.ic_cockroach;

                break;

            case "CMSGENEX":

                image = R.drawable.ic_cockroach;

                break;

            case "BBMS":

                image = R.drawable.ic_bed_bug;

                break;

            case "BMS":

                image = R.drawable.ic_dove;

                break;

            case "CARCMS":

                image = R.drawable.ic_mosquito;

                break;

            case "FMS":

                image = R.drawable.ic_fly;

                break;

            case "LMS":

                image = R.drawable.ic_lizard;

                break;

            case "MMS":

                image = R.drawable.ic_mosquito;

                break;

            case "RMS":

                image = R.drawable.ic_rat;

                break;

            case "SRS":

                image = R.drawable.ic_snake;

                break;

            case "Stericare":

                image = R.drawable.avatar;

                break;

            case "TMSPOST":

                image = R.drawable.ic_termite;

                break;

            case "TMSPRE":

                image = R.drawable.ic_termite;

                break;

            case "WBMS":

                image = R.drawable.ic_beetle;

                break;

            case "Termites for Post":

                image = R.drawable.ic_termite;

                break;

            case "Bed Bugs":

                image = R.drawable.ic_bed_bug;

                break;

            case "HCS":

                image = R.drawable.avatar;

                break;

            case "Insp":

                image = R.drawable.avatar;

                break;

            default:

                image = R.mipmap.logo;

                break;

        }
        return image;
    }

    public static String GetSMSServiceType(String type) {
        String serviceType = "";

        if (type.equals("CMS")) {
            serviceType = "Cockroach Control";
        } else if (type.equals("CMSGENEX")) {
            serviceType = "Cockroach Control";
        } else if (type.equals("BBMS")) {
            serviceType = "Bed Bugs Control";
        } else if (type.equals("BMS")) {
            serviceType = "Bird Netting";
        } else if (type.equals("CARCMS")) {
            serviceType = "Car Cockroach Control";
        } else if (type.equals("FMS")) {
            serviceType = "Fly Control";
        } else if (type.equals("LMS")) {
            serviceType = "Lizard Control";
        } else if (type.equals("MMS")) {
            serviceType = "Mosquito Control";
        } else if (type.equals("RMS")) {
            serviceType = "Rodent Control";
        } else if (type.equals("SRS")) {
            serviceType = "Snake Control";
        } else if (type.equals("Stericare")) {
            serviceType = "Stericare";
        } else if (type.equals("TMSPOST")) {
            serviceType = "Termite Control";
        } else if (type.equals("TMSPRE")) {
            serviceType = "Termite Control";
        } else if (type.equals("WBMS")) {
            serviceType = "Wood Borer Control";
        } else if (type.equals("Termites for Post")) {
            serviceType = "Termite Control";
        } else if (type.equals("Bed Bugs")) {
            serviceType = "Bed Bugs Control";
        } else if (type.equals("HCS")) {
            serviceType = "House Cleaning";
        } else if (type.equals("Insp")) {
            serviceType = "Inspection";
        } else {
            serviceType = type;
        }
//        switch (type)
//        {

//            case "CMS":
//
//                serviceType = "Cockroach Control";
//
//                break;
//
//            case "CMSGENEX":
//
//                serviceType = "Cockroach Control";
//
//                break;
//
//            case "BBMS":
//
//                serviceType = "Bed Bugs Control";
//
//                break;

//            case "BMS":
//
//                serviceType = "Bird Netting";
//
//                break;

//            case "CARCMS":
//
//                serviceType = "Car Cockroach Control";
//
//                break;

//            case "FMS":
//
//                serviceType = "Fly Control";
//
//                break;

//            case "LMS":
//
//                serviceType = "Lizard Control";
//
//                break;

//            case "MMS":
//
//                serviceType = "Mosquito Control";
//
//                break;

//            case "RMS":
//
//                serviceType = "Rodent Control";
//
//                break;

//            case "SRS":
//
//                serviceType = "Snake Control";
//
//                break;
//
//            case "Stericare":
//
//                serviceType = "Stericare";
//
//                break;

//            case "TMSPOST":
//
//                serviceType = "Termite Control";
//
//                break;

//            case "TMSPRE":
//
//                serviceType = "Termite Control";
//
//                break;
//
//            case "WBMS":
//
//                serviceType = "Wood Borer Control";
//
//                break;

//            case "Termites for Post":
//
//                serviceType = "Termite Control";
//
//                break;

//            case "Bed Bugs":
//
//                serviceType = "Bed Bugs Control";
//
//                break;

//            case "HCS":
//
//                serviceType = "House Cleaning";
//
//                break;

//            case "Insp":
//
//                serviceType = "Inspection";
//
//                break;
//
//            default:
//
//                serviceType = type;
//
//                break;

//        }

        return serviceType;

    }


}
