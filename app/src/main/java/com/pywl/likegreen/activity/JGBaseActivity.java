package com.pywl.likegreen.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by theWind on 2018/8/14.
 */

public class JGBaseActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        JMessageClient.registerEventReceiver(this);
    }
}
