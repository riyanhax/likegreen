package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.JsonRootBean;
import com.xbdl.xinushop.constant.UrlConstant2;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ImageUtils;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

//个人资料页
public class PersonalDataActivity extends BaseActivity implements View.OnClickListener {

    private ArrayList<ImageItem> headicon;
    private View mSystemPersonalHead, mSystemPersonalName, mSystemPersonPhone, mRlSagnatrue, mRlShoppingAddress;
    private CircleImageView mIvPersonalHead;
    private TextView mTvPersonalName, mTvPersonalPhone;
    private TextView tv_signature;//个性签名


    @Override
    protected Activity getActivity() {
        return PersonalDataActivity.this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        ImagePicker.getInstance().setMultiMode(false);
        initView();
        initData();
    }

    private void initView() {
        //头像栏
        mSystemPersonalHead = findViewById(R.id.system_personal_head);
        mSystemPersonalHead.setOnClickListener(this);
        //头像图片
        mIvPersonalHead = (CircleImageView) findViewById(R.id.iv_personal_head);
        //昵称栏
        mSystemPersonalName = findViewById(R.id.system_personal_data_nechen);
        mSystemPersonalName.setOnClickListener(this);
        //昵称
        mTvPersonalName = (TextView) findViewById(R.id.tv_personal_name);
        //手机号栏
        mSystemPersonPhone = findViewById(R.id.system_personal_phone);
        mSystemPersonPhone.setOnClickListener(this);
        //手机号
        mTvPersonalPhone = (TextView) findViewById(R.id.tv_personal_phone);
        //个性化签名
        findViewById(R.id.rl_sagnatrue).setOnClickListener(this);
        tv_signature = findViewById(R.id.tv_signature);
        mRlShoppingAddress = findViewById(R.id.rl_shopping_address);
        mRlShoppingAddress.setOnClickListener(this);
        findViewById(R.id.rl_idcard_check).setOnClickListener(this);//身份证验证
        findViewById(R.id.rl_business_check).setOnClickListener(this);//营业执照验证
        findViewById(R.id.iv_return).setOnClickListener(this);//返回

    }

    private void initData() {
        //设置手机号
        mTvPersonalPhone.setText(MyApplication.user.getUserPhone());
        //个性签名
        tv_signature.setText(MyApplication.user.getSignature());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.system_personal_head:
                Intent intentPerview = new Intent(this, ImageGridActivity.class);
                intentPerview.putExtra(ImageGridActivity.EXTRAS_IMAGES, headicon);
                startActivityForResult(intentPerview, 100);
                break;
            case R.id.system_personal_phone://修改手机号码
                Intent intentChangePhoneNumActivity = new Intent(this, ChangePhoneNumActivity.class);
                startActivity(intentChangePhoneNumActivity);
                break;
            case R.id.rl_sagnatrue://个性签名
                Intent intentChangeUserMsgActivity = new Intent(this, AutographActivity.class);
                intentChangeUserMsgActivity.putExtra("PersonalDataActivity", 0);
                startActivityForResult(intentChangeUserMsgActivity, 200);
                break;
            case R.id.rl_shopping_address://地址
                Intent intentShoppingAddressActivity = new Intent(this, ShoppingAddressActivity.class);
                startActivity(intentShoppingAddressActivity);
                break;
            case R.id.rl_idcard_check://身份证验证
                Intent intentIDCardCheckActivity = new Intent(this, IDCardCheckActivity.class);
                startActivity(intentIDCardCheckActivity);
                break;
            case R.id.rl_business_check://营业执照验证
                Intent intentBusinessLicenseActivity = new Intent(this, BusinessLicenseActivity.class);
                startActivity(intentBusinessLicenseActivity);
                break;
            case R.id.system_personal_data_nechen://设置名字
                Intent intentChangeUserMsgActivity2 = new Intent(this, AutographActivity.class);
                intentChangeUserMsgActivity2.putExtra("PersonalDataActivity", 1);
                startActivityForResult(intentChangeUserMsgActivity2, 200);
                break;
            case R.id.iv_return://返回
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                headicon = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                String bitmapToString = ImageUtils.bitmapToString(headicon.get(0).path);
                sendMsg("headPortrait", bitmapToString);
                Glide.with(this).load(headicon.get(0).path).into(mIvPersonalHead);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_OK) {
            if (data != null && requestCode == 200) {
                String signature = data.getStringExtra("signature");
                if (signature != null) {
                    Log.v("nihaoma", signature);

                    sendMsg("signature", signature);
                    tv_signature.setText(signature);
                }
                String nichen = data.getStringExtra("nichen");
                if (nichen != null) {
                    Log.v("nihaoma", nichen);
                    mTvPersonalName.setText(nichen);
                    sendMsg("userName", nichen);
                }
            }
        }

    }


    private void sendMsg(String key, String val) {
        String loginToken = MyApplication.user.getLoginToken();
        Log.v("nihaoma", loginToken + key + val + UrlConstant2.updataUser);
        HttpUtils2.updataUser(loginToken, key, val, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma", "4444444");
                Gson gson = new Gson();
                JsonRootBean jsonRootBean = gson.fromJson(response.body(), JsonRootBean.class);
                Log.v("nihaoma", jsonRootBean.toString());
                dismissLoading();
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                Log.v("nihaoma", "1111111");
                showLoading();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.v("nihaoma", "22222222" + response.body());

                dismissLoading();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Log.v("nihaoma", "3333333");
                dismissLoading();
            }
        });

    }
}