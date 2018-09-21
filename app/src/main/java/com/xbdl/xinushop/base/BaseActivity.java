package com.xbdl.xinushop.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;

import com.xbdl.xinushop.BroadcastReceiver.NetWorkStateReceiver;


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
