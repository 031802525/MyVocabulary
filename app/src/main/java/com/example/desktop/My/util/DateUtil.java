package com.example.desktop.My.util;

import java.util.Calendar;

public class DateUtil {

//    获取当前月的天数
    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

//    获取当前年月
    public static String getCurrentYearAndMonth(){
        Calendar a = Calendar.getInstance();
        int year = a.get(Calendar.YEAR);
        int month = a.get(Calendar.MONTH) + 1;
        return year+"年"+month+"月";
    }

//    获取当前月日为星期几
    public static int getFirstDayOfMonth(){
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DAY_OF_MONTH,1);
        int i = a.get(Calendar.DAY_OF_WEEK);
        return i;
    }

//    获取当前日
    public static int getCurrentday(){
        Calendar a = Calendar.getInstance();
        int day = a.get(Calendar.DAY_OF_MONTH);
        return day;
    }
//      获取当前月
    public static int getCurrentMonth(){
        Calendar a = Calendar.getInstance();
        int month = a.get(Calendar.MONTH);
        return month+1;
    }
}
