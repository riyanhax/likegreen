package com.xbdl.xinushop.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;

import com.xbdl.xinushop.BroadcastReceiver.NetWorkStateReceiver;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.view.LoadingDialog;


/**
 * Created by theWind on 2018/8/3.
 */

public class BaseActivity extends AppCompatActivity {
   protected LoadingDialog loadingDialog;
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
    /**
     * 显示加载对话框

     */
    protected void showLoading(){
        if (loadingDialog==null){
            loadingDialog = new LoadingDialog(this);
            loadingDialog.show();
        }
    }
    protected void dismissLoading(){
        loadingDialog.dismiss();
    }
}
