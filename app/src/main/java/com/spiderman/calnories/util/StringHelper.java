package com.spiderman.calnories.util;

/**
 * Created by Biekaeksa on 4/15/2017.
 */

public class StringHelper {
    public static String convertTimeToString(int hourInt, int minuteInt){
        String hour = String.valueOf(hourInt);
        String minute = String.valueOf(minuteInt);

        if (hourInt < 10) {
            hour = "0" + hour;
        }

        if (minuteInt < 10) {
            minute = "0" + minute;
        }

        return hour+":"+minute;
    }

    public static String convertCalendarToString(int yearInt, int monthOfYear, int dayOfMonth) {
        String year = String.valueOf(yearInt);
        String month = String.valueOf(monthOfYear + 1);
        String day = String.valueOf(dayOfMonth);

        if (monthOfYear < 10) {
            month = "0" + month;
        }

        if (dayOfMonth < 10) {
            day = "0" + day;
        }



        return day + "/" + month + "/" + year;
    }
}
