package com.xbdl.xinushop.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xbdl.xinushop.R;
/*
* 应用设备
* */
public class EquipmentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return).setOnClickListener(this);
        findViewById(R.id.rl_plantmachine).setOnClickListener(this);
        findViewById(R.id.rl_plantlight).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_return:
                finish();
                break;
            case R.id.rl_plantmachine:
            case R.id.rl_plantlight:
          /*      ComponentName componentName = new ComponentName("com.tencent.mm","com.tencent.mm.MainActivity");
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("FIRST_APP_KEY", "你好 ，MainActivity");
                intent.putExtras(bundle);
                intent.setComponent(componentName);
                startActivity(intent);*/

           /*    try {
                    PackageManager packageManager = getPackageManager();
                    Intent intent=new Intent();
                    intent = packageManager.getLaunchIntentForPackage("com.example.administrator.myflower");
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Intent viewIntent = new
                            Intent("android.intent.action.VIEW", Uri.parse("http://weixin.qq.com/"));
                    startActivity(viewIntent);
                }*/

                break;
        }
    }
}
