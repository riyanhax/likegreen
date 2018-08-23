package com.pywl.likegreen.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.pywl.likegreen.R;


public class SystemSettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
   private  View mMyToPromote,mSystemPersonalData;
   private PopupWindow popupWindow;
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
    }
    private void initData() {

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

                break;
        }
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
               /* WindowManager.LayoutParams lp = getParent().getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                getParent().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getParent().getWindow().setAttributes(lp);*/
            }
        });
        //popupWindow.showAsDropDown(mMyToPromote, -70, 5);
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

}
