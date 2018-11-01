package com.xbdl.xinushop.utils;

import java.util.Calendar;
import java.util.TimeZone;

public class WeekTimeUtil {

    private static String mYear;
    private static String mMonth;
    private static String mDay;
    private static String mWay;
   /*
   * 1年 2月 3日 4星期
   * */
    public static String StringData(int data){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if("1".equals(mWay)){
            mWay ="天";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("7".equals(mWay)){
            mWay ="六";
        }
        switch (data){
            case 1:
                return mYear;
            case 2:
                return mMonth;
            case 3:
                return mDay;
            case 4:
                return mWay;
             default:
                 return "nian";
        }

    }

}
