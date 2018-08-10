package com.pywl.likegreen;

import android.app.Application;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by theWind on 2018/8/10.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);
    }
}
