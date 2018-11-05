package com.xbdl.xinushop.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/**
 * 作者： Administrator
 * 时间： 2018/10/16 10:24
 * 描述： 主要用于判断APP是否在前台运行
 */
public class ForegroundUtil implements Application.ActivityLifecycleCallbacks {

    //单例
    private static ForegroundUtil instance = new ForegroundUtil();

    //用于判断是否程序在前台
    private boolean foreground = false, paused = true;
    //handler用于处理切换activity时的短暂时期可能出现的判断错误
    private Handler handler = new Handler();
    private Runnable check;

    public static void init(Application app){
        app.registerActivityLifecycleCallbacks(instance);
    }

    public static ForegroundUtil get(){
        return instance;
    }

    private ForegroundUtil(){}

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {
        paused = true;
        if (check != null)
            handler.removeCallbacks(check);
        handler.postDelayed(check = new Runnable(){
            @Override
            public void run() {
                if (foreground && paused) {
                    foreground = false;
                    Log.v("nihaoma","后台运行啦");
                } else {
                    Log.v("nihaoma","任然在前台运行");
                }
            }
        }, 500);

    }

    @Override
    public void onActivityResumed(Activity activity) {
        paused = false;
        foreground = true;
        if (check != null)
            handler.removeCallbacks(check);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    /**
     * 是否在前台运行
     * @return true 前台运行
     */
    public boolean isForeground(){
        return foreground;
    }
}
