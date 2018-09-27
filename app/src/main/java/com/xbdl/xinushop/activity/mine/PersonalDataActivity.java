package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lzy.imagepicker.ui.ImageGridActivity;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.base.BasePresenter;

import de.hdodenhof.circleimageview.CircleImageView;

//个人资料页
public class PersonalDataActivity extends BaseActivity implements View.OnClickListener {
    private View mSystemPersonalHead,mSystemPersonalName,mSystemPersonPhone,mRlSagnatrue,mRlShoppingAddress;
    private CircleImageView mIvPersonalHead;
    private TextView mTvPersonalName,mTvPersonalPhone;

    @Override
    protected Activity getActivity() {
        return PersonalDataActivity.this;
    }

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
        findViewById(R.id.rl_sagnatrue).setOnClickListener(this);
        mRlShoppingAddress = findViewById(R.id.rl_shopping_address);
        mRlShoppingAddress.setOnClickListener(this);
        findViewById(R.id.rl_idcard_check).setOnClickListener(this);//身份证验证
        findViewById(R.id.rl_business_check).setOnClickListener(this);//营业执照验证
    }

    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.system_personal_head:
                break;
            case R.id.system_personal_phone://修改手机号码
                Intent intentChangePhoneNumActivity = new Intent(this, com.xbdl.xinushop.activity.mine.ChangePhoneNumActivity.class);
                startActivity(intentChangePhoneNumActivity);
                break;
            case R.id.rl_sagnatrue://个性签名
                Intent intentChangeUserMsgActivity = new Intent(this, com.xbdl.xinushop.activity.mine.AutographActivity.class);
                startActivity(intentChangeUserMsgActivity);
            case R.id.rl_shopping_address://个性签名
                Intent intentShoppingAddressActivity = new Intent(this, com.xbdl.xinushop.activity.mine.ShoppingAddressActivity.class);
                startActivity(intentShoppingAddressActivity);
                break;
            case R.id.rl_idcard_check://身份证验证
                Intent intentIDCardCheckActivity = new Intent(this, com.xbdl.xinushop.activity.mine.IDCardCheckActivity.class);
                startActivity(intentIDCardCheckActivity);
                break;
            case R.id.rl_business_check://营业执照验证
                Intent intentBusinessLicenseActivity = new Intent(this, com.xbdl.xinushop.activity.mine.BusinessLicenseActivity.class);
                startActivity(intentBusinessLicenseActivity);
                break;

        }
    }


}
