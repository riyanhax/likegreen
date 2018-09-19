package com.pywl.likegreen.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pywl.likegreen.R;
import com.pywl.likegreen.base.BaseActivity;
import com.pywl.likegreen.bean.MyConstants;
import com.pywl.likegreen.utils.SharedPreferencesUtil;


public class SystemSettingsActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext;
    private  View mMyToPromote,mSystemPersonalData;
    private PopupWindow popupWindow;
    private  CheckBox wifi,mobileandWifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_mysettings);
        mContext=this;
        initView();
        initData();
    }



    private void initView() {
        mSystemPersonalData=findViewById(R.id.system_personal_Data);
        mSystemPersonalData.setOnClickListener(this);
        //我要推广
        mMyToPromote = findViewById(R.id.my_to_promote);
        mMyToPromote.setOnClickListener(this);
        //网络状态
        wifi = (CheckBox)findViewById(R.id.cb_wifi_network);
        mobileandWifi = (CheckBox)findViewById(R.id.cb_mobile_network);

        findViewById(R.id.rl_change_phone).setOnClickListener(this);
        findViewById(R.id.rl_push_msg).setOnClickListener(this);
        findViewById(R.id.rl_user_protocol).setOnClickListener(this);
    }
    private void initData() {
        //wifi下观看视频
        wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    wifi.setChecked(true);
                    mobileandWifi.setChecked(false);
                    SharedPreferencesUtil.putBoolean(SystemSettingsActivity.this, MyConstants.WIFI_AND_MOBILE,true);
                }else {
                    wifi.setChecked(false);
                    mobileandWifi.setChecked(true);
                    SharedPreferencesUtil.putBoolean(SystemSettingsActivity.this, MyConstants.WIFI_AND_MOBILE,false);
                }
            }
        });
        mobileandWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    wifi.setChecked(false);
                    mobileandWifi.setChecked(true);
                }else {
                    wifi.setChecked(true);
                    mobileandWifi.setChecked(false);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_to_promote:
                showPopuWindow();
                break;
            case R.id.system_personal_Data:
                Intent intentPersonalData = new Intent(this, PersonalDataActivity.class);
                startActivity(intentPersonalData);
                break;
            case R.id.tv_to_promote_cancel:
                popupWindow.dismiss();
                break;
            case R.id.to_to_promte_consulting://立即咨询
                call("18038637318");
                popupWindow.dismiss();
                break;
            case R.id.rl_change_phone://更改手机号
                Intent intentChangePhoneNumActivity = new Intent(this, ChangePhoneNumActivity.class);
                startActivity(intentChangePhoneNumActivity);
                break;
            case R.id.rl_push_msg://消息推送
                Intent intentSetPushMsgActivity = new Intent(this, SetPushMsgActivity.class);
                startActivity(intentSetPushMsgActivity);
                break;
            case R.id.rl_user_protocol://版权说明 用户协议
                Intent intentUserProtocolActivity = new Intent(this, UserProtocolActivity.class);
                startActivity(intentUserProtocolActivity);
                break;
            case R.id.rl_about_us://关于我们
                Intent intentAboutUsActivity = new Intent(this, AboutUsActivity.class);
                startActivity(intentAboutUsActivity);
                break;

        }
    }
    //调到拨号界面
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void showPopuWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_to_promote, null);
        TextView popToPromotCancel = contentView.findViewById(R.id.tv_to_promote_cancel);
        popToPromotCancel.setOnClickListener(this);
        TextView popToPromotConsulting = contentView.findViewById(R.id.to_to_promte_consulting);
        popToPromotConsulting.setOnClickListener(this);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        //设置半透明
        WindowManager.LayoutParams lp = this.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getWindow().setAttributes(lp);
        //恢复正常
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = SystemSettingsActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                SystemSettingsActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                SystemSettingsActivity.this.getWindow().setAttributes(lp);
            }
        });
        //popupWindow.showAsDropDown(mMyToPromote, -70, 5);
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

}
