package com.xxx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    //日期转换为年月日字符串
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }
    //年月日字符串转换为日期

    public static Date stringToDate(String dateString)  {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
