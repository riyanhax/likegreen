package com.xbdl.xinushop.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;
import com.xbdl.xinushop.MainActivity;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.utils.AESUtils;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mPhoneNumber, etPwd;
    private  CircleImageView headIcon;
    private CheckBox showPwd;
    private String loginphone,loginpwd;

    @Override
    protected Activity getActivity() {
        return LoginActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }



    private void initView() {
        headIcon = (CircleImageView)findViewById(R.id.login_head_icon);
        mPhoneNumber =(EditText) findViewById(R.id.et_phone_number);
        etPwd =(EditText) findViewById(R.id.et_pwd);
        findViewById(R.id.tv_login).setOnClickListener(this);//登录
        findViewById(R.id.tv_forget_pwd).setOnClickListener(this);//忘记密码
        findViewById(R.id.login_weixin).setOnClickListener(this);//微信
        findViewById(R.id.login_qq).setOnClickListener(this);//QQ
        findViewById(R.id.login_weibo).setOnClickListener(this);//微博

        showPwd= findViewById(R.id.cb_show_pwd);
        showPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);// 输入为密码且可见
                    etPwd.setSelection(etPwd.getText().length());
                }else {
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);// 设置文本类密码（默认不可见），这两个属性必须同时设置
                    etPwd.setSelection(etPwd.getText().length());
                }
            }
        });

    }
    private int forget=0;
    private int register=1;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login://登录
                login();

                break;
            case R.id.tv_forget_pwd://忘记密码
                Intent intentRegisterActivity1 = new Intent(this, RegisterActivity.class);
                intentRegisterActivity1.putExtra("LoginActivity",forget);
                startActivity(intentRegisterActivity1);
                break;
            case R.id.tv_tv_register://注册
                Intent intentRegisterActivity = new Intent(this, RegisterActivity.class);
                intentRegisterActivity.putExtra("LoginActivity",register);
                startActivity(intentRegisterActivity);
                break;
            case R.id.login_weixin:
                UMShareAPI.get(this).getPlatformInfo(this,SHARE_MEDIA.WEIXIN,authListener);
                break;
            case R.id.login_qq:
                UMShareAPI.get(this).getPlatformInfo(this,SHARE_MEDIA.QQ,authListener);
                break;
            case R.id.login_weibo:
                UMShareAPI.get(this).getPlatformInfo(this,SHARE_MEDIA.SINA,authListener);
                break;
        }
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            showLoading();
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            dismissLoading();
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Set<Map.Entry<String, String>> set = data.entrySet();
            for (Map.Entry<String,String> me: set){
                String key = me.getKey();
                String value = me.getValue();
                Log.v("nihaoma",key+"11111"+value);
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            dismissLoading();
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            dismissLoading();
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    private void login(){
        if (mPhoneNumber.length()!=11){
            Toast.makeText(this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etPwd.getText().toString())){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        loginphone = mPhoneNumber.getText().toString();
        loginpwd = etPwd.getText().toString();
        if (mPhoneNumber.length()==11&& !TextUtils.isEmpty(etPwd.toString())){
            HttpUtils.login(loginphone, loginpwd,new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    Log.v("nihaoma","3333333333");
                    String body = response.body();
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        int code = jsonObject.getInt("code");
                        String object = jsonObject.getString("object");
                        if (code==100){
                            Gson gson = new Gson();
                            PersonBean personBean = gson.fromJson(object, PersonBean.class);
                            MyApplication application = (MyApplication)getApplication();
                            application.setUer(personBean);
                            Log.v("nihaoma",personBean.toString());
                            JSONObject jsonObject1 = new JSONObject(object);
                            String user = jsonObject1.getString("user");
                            SharedPreferencesUtil.putString(LoginActivity.this,MyConstants.User,user);
                            //设置是否登录了
                            SharedPreferencesUtil.putBoolean(LoginActivity.this,MyConstants.ISLOGIN,true);
                            //加密
                           // String phoneEncode = AESUtils.encryptString(loginphone, MyConstants.Key);
                            //String pwdEncode = AESUtils.encryptString(loginpwd, MyConstants.Key);

                            SharedPreferencesUtil.putString(LoginActivity.this,MyConstants.PHONE,loginphone);
                            SharedPreferencesUtil.putString(LoginActivity.this,MyConstants.PASSWORD,loginpwd);
                            //SharedPreferencesUtil.putString(LoginActivity.this,MyConstants.PHONE,phoneEncode);
                           // SharedPreferencesUtil.putString(LoginActivity.this,MyConstants.PASSWORD,pwdEncode);
                            dismissLoading();
                            Intent intentMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intentMainActivity);
                            finish();
                        }else {
                            String msg = jsonObject.getString("msg");
                            Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onStart(Request<String, ? extends Request> request) {
                    super.onStart(request);

                    Log.v("nihaoma","4444444444");
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    dismissLoading();
                    Log.v("nihaoma",response+"55555555555");
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    dismissLoading();
                }
            });
        }
    }
    private void initData() {
        String phoneShare = SharedPreferencesUtil.getString(this, MyConstants.PHONE, "1");
        String pwdShare = SharedPreferencesUtil.getString(this, MyConstants.PASSWORD, "1");
        Log.v("nihaoma",phoneShare+"11111"+pwdShare);
        if (!TextUtils.isEmpty(phoneShare)&&!phoneShare.equals("1")){
            Log.v("nihaoma",phoneShare+"11111"+pwdShare);
            //String decodePhone = AESUtils.decryptString(phoneShare, MyConstants.Key);
            mPhoneNumber.setText(phoneShare);
        }
        if (!TextUtils.isEmpty(pwdShare)&&!pwdShare.equals("1")){
            Log.v("nihaoma",phoneShare+"11111"+pwdShare);
          //  String decodepwd = AESUtils.decryptString(pwdShare, MyConstants.Key);
            etPwd.setText(pwdShare);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag("login");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        OkGo.getInstance().cancelTag(this);
        MainActivity.instance.finish();
        finish();
        OkGo.getInstance().cancelTag("login");
    }
}
