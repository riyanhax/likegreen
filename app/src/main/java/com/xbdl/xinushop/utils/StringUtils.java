package com.xbdl.xinushop.utils;

import android.text.TextUtils;



/**
 * 字符工具
 * Created by YSL on 2017/4/11.
 */

public class StringUtils {

    /**
     * 获取状态
     * @param code
     *  A("待审核",0),
        B("未通过",1),
        C("筹款中",2),
        D("已完成",3),
        E("已下架",4);
        F("永久下架")
     */
    public static String getProjectStatus(String code){
        String status = "待审核";
        if ("A".equals(code)){
            status = "待审核";
        }else if ("B".equals(code)){
            status = "未通过";
        }else if ("C".equals(code)){
            status = "筹款中";
        }else if ("D".equals(code)){
            status = "已完成";
        }else if ("E".equals(code)){
            status = "已下架";
        }else if ("F".equals(code)){
            status = "永久下架";
        }
        return status;
    }

    /**
     * Object》int
     * @param object
     * @return
     */
    public static int ObjectToInt(Object object){
        int backInt = 0;
        String str = String.valueOf(object);
        if (TextUtils.isEmpty(str)){
            return backInt;
        }
        if (str.endsWith(".0")){
            str = str.substring(0,str.length()-2);
        }
        backInt = Integer.valueOf(str);
        return backInt;
    }

    /**
     * Object》long
     * @param object
     * @return
     */
    public static Long ObjectToLong(Object object){
        Long backInt = Long.valueOf(0);
        String str = String.valueOf(object);
        if (TextUtils.isEmpty(str)){
            return backInt;
        }
        if (str.endsWith(".0")){
            str = str.substring(0,str.length()-2);
        }
        backInt = Long.valueOf(str);
        return backInt;
    }

    /**
     * Json Filter
     */
/*    public static ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if (v == null)
                return "";
            return v;
        }
    };*/

    /**
     * 判断是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if (TextUtils.isEmpty(str) || "null".equals(str)){
            return true;
        }
        return false;
    }

}
