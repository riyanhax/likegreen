package com.xbdl.xinushop.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.xbdl.xinushop.bean.MyConstants;


/**
 * 文件名：SharedPreferencesUtils 描 述：SharedPreferences工具类 作 者：穆琨 时 间：2016-02-28 版 权：
 */
public class SharedPreferencesUtil {

    private static SharedPreferences mSharedPreferences;

    private static SharedPreferences getSharedPreferences(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(MyConstants.DATA,
                    0);
        }
        return mSharedPreferences;

    }

    /**
     * @param context 上下文
     * @param key     键
     * @param value   默认值
     */
    public static void putInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * @param context 上下文
     * @param key     键
     * @param value   默认值
     * @return 根据key获得的值
     */
    public static int getInt(Context context, String key, int value) {
        return getSharedPreferences(context).getInt(key, value);
    }

    /**
     * @param context 上下文
     * @param key     键
     * @param value   默认值
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * @param context 上下文
     * @param key     键
     * @param value   默认值
     * @return 根据key获得的值
     */
    public static String getString(Context context, String key, String value) {
        return getSharedPreferences(context).getString(key, value);
    }

    /**
     * @param context 上下文
     * @param key     键
     * @param value   默认值
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * @param context 上下文
     * @param key     键
     * @param value   默认值
     * @return 根据key获得的值
     */
    public static boolean getBoolean(Context context, String key, boolean value) {
        return getSharedPreferences(context).getBoolean(key, value);
    }

    /**
     * @param context 上下文
     * @param key     键
     * @param value   默认值
     */
    public static void putLong(Context context, String key, Long value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * @param context 上下文
     * @param key     键
     * @param value   默认值
     * @return 根据key获得的值
     */
    public static Long getLong(Context context, String key, Long value) {
        return getSharedPreferences(context).getLong(key, value);
    }

    /**
     * @param context 上下文
     * @param key     键
     */
    public static void remove(Context context, String key) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(key);
        editor.commit();
    }

}
