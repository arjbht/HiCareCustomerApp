package com.arj.hicarehygiene.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.arj.hicarehygiene.R;

/**
 * Created by yogi on 10/05/17.
 */

public class TimeUtil {

    public static boolean isDayTime(Integer sunriseTime, Integer sunsetTime) {
        final Calendar c = Calendar.getInstance();
        long currentEpicTime = c.getTimeInMillis() / 1000;
        if (currentEpicTime > sunriseTime && currentEpicTime < sunsetTime) {
            return true;
        } else {
            return false;
        }
    }

    public static int getColor(boolean daylight) {
        if (daylight) {
            return R.color.colorAccent;
        } else {
            return R.color.colorPrimary;
        }
    }

    public static String convertEpochToDateTime(long epochTime, String format) {
        final Calendar c = Calendar.getInstance();
        TimeZone timezon = c.getTimeZone();
        String displayName = timezon.getDisplayName();

        Date epochDate = new Date(epochTime * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(epochDate);
    }

    public static String getCurrentDateTime(String format) {
        final Calendar c = Calendar.getInstance();
        Date currentDateTime = new Date(c.getTimeInMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(currentDateTime);
    }

    public static String reFormatDate(String dateIn, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateIn);
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String reFormatDateTime(String dateIn, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = simpleDateFormat.parse(dateIn);
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static Boolean isOnDate(String scheduled_date, String format) throws ParseException {
        String T = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();

        Date todayDate = Calendar.getInstance().getTime();
        String todayString = sdf.format(todayDate);//current date
        try {
            c.setTime(sdf.parse(todayString));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, 7);  // number of days to add
        T = sdf.format(c.getTime());
        if (T.compareTo(scheduled_date) <= 0) {
            return true;
        } else {
            return false;
        }

    }
}
