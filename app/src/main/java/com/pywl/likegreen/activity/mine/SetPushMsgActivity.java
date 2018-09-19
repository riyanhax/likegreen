package com.pywl.likegreen.activity.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.pywl.likegreen.R;
import com.pywl.likegreen.bean.MyConstants;
import com.pywl.likegreen.utils.SharedPreferencesUtil;

/*
* 消息推送
* */
public class SetPushMsgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_push_msg);
        CheckBox msgpush = (CheckBox)findViewById(R.id.cb_msg_push);
        msgpush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SharedPreferencesUtil.putBoolean(SetPushMsgActivity.this,MyConstants.ISPUSH_MSG,true);
                }else {
                    SharedPreferencesUtil.putBoolean(SetPushMsgActivity.this,MyConstants.ISPUSH_MSG,false);
                }
            }
        });
        boolean aBoolean = SharedPreferencesUtil.getBoolean(this, MyConstants.ISPUSH_MSG, true);
        Log.v("nihaoma",aBoolean+"11111111");
        msgpush.setChecked(aBoolean);
    }
}
