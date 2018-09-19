package com.pywl.likegreen.base;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pywl.likegreen.BroadcastReceiver.NetWorkStateReceiver;
import com.pywl.likegreen.R;

/**
 * Created by theWind on 2018/8/3.
 */

public class BaseActivity extends AppCompatActivity {

    public NetWorkStateReceiver netWorkStateReceiver;


    //在onResume()方法注册
    @Override
    protected void onResume() {
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);

        super.onResume();
    }

    //onPause()方法注销
    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);

        super.onPause();
    }
}
