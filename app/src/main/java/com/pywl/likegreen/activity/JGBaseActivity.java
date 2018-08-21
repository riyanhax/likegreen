package com.pywl.likegreen.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by theWind on 2018/8/14.
 */

public abstract class JGBaseActivity extends Activity {
    Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handlermsg(msg);
        }
    };
   private Thread thread;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //JMessageClient.registerEventReceiver(this);
         thread = new Thread(new Runnable() {
            @Override
            public void run() {
                threadRun();
            }
        });
    }

    protected abstract void threadRun();

    abstract void handlermsg(Message msg);

}
