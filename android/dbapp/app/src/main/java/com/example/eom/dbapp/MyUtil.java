package com.example.eom.dbapp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sangwon0001 on 2017-06-07.
 */

public class MyUtil {
    public static String getStringFromDate(Date date){
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sdFormat.format(date);
    }
    public static Date getDateFromString(String dateString){
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            return sdFormat.parse(dateString);
        }catch (Exception e){
            e.printStackTrace();
            return new Date(System.currentTimeMillis());
        }
    }
}
