package com.pywl.likegreen.chat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by theWind on 2018/8/14.
 */

public abstract class JGBaseFragment extends Fragment {
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handlerMsg(msg);
        }
    };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //JMessageClient.registerEventReceiver(this);
        JMessageClient.login("fjjpydc", "84915190qw", new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {

            }
        });

    }
    public abstract void handlerMsg(Message msg);
    @Override
    public void onDestroy() {
        JMessageClient.unRegisterEventReceiver(this);
        super.onDestroy();
    }
}
