package com.pywl.likegreen.activity.mine;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pywl.likegreen.R;

import de.hdodenhof.circleimageview.CircleImageView;

//个人资料页
public class PersonalDataActivity extends AppCompatActivity implements View.OnClickListener {
    private View mSystemPersonalHead,mSystemPersonalName,mSystemPersonPhone,mRlSagnatrue,mRlShoppingAddress;
    private CircleImageView mIvPersonalHead;
    private TextView mTvPersonalName,mTvPersonalPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        initView();
        initData();
    }

    private void initView() {
        //头像栏
        mSystemPersonalHead = findViewById(R.id.system_personal_head);
        mSystemPersonalHead.setOnClickListener(this);
        //头像图片
        mIvPersonalHead = (CircleImageView)findViewById(R.id.iv_personal_head);
        //昵称栏
        mSystemPersonalName = findViewById(R.id.system_personal_data_nechen);
        mSystemPersonalHead.setOnClickListener(this);
        //昵称
        mTvPersonalName=(TextView)findViewById(R.id.tv_personal_name);
        //手机号栏
         mSystemPersonPhone = findViewById(R.id.system_personal_phone);
         mSystemPersonPhone.setOnClickListener(this);
         //手机号
        mTvPersonalPhone=(TextView)findViewById(R.id.tv_personal_phone);
        //个性化签名
        mRlSagnatrue = findViewById(R.id.rl_sagnatrue);
        mRlShoppingAddress = findViewById(R.id.rl_shopping_address);
    }

    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.system_personal_head:
                break;

        }
    }
}
