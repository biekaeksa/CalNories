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
        String bulan = "";


        if(month.equals("1")){
            bulan = "Januari";
        }else if(month.equals("2")){
            bulan = "Februari";
        }else if(month.equals("3")){
            bulan = "Maret";
        }else if(month.equals("4")){
            bulan = "April";
        }else if(month.equals("5")){
            bulan = "Mei";
        }else if(month.equals("6")){
            bulan = "Juni";
        }else if(month.equals("7")){
            bulan = "Juli";
        }else if(month.equals("8")){
            bulan = "Agustus";
        }else if(month.equals("9")){
            bulan = "September";
        }else if(month.equals("10")){
            bulan = "Oktober";
        }else if(month.equals("11")){
            bulan = "November";
        }else if(month.equals("12")){
            bulan = "Desember";
        }


        return day + " " + bulan + " " + year;
    }
}
