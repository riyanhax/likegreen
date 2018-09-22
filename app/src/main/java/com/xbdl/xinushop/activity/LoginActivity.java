package com.xbdl.xinushop.activity;

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
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MainActivity;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mPhoneNumber, etPwd;
    private  CircleImageView headIcon;
    private CheckBox showPwd;
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
        findViewById(R.id.tv_tv_register).setOnClickListener(this);//注册
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login://登录
                login();

                break;
            case R.id.tv_forget_pwd://忘记密码
                break;
            case R.id.tv_tv_register://注册
                break;
        }
    }
    private void login(){
        if (mPhoneNumber.length()!=11){
            Toast.makeText(this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etPwd.getText().toString())){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        String s = mPhoneNumber.getText().toString();
        String s1 = etPwd.getText().toString();
        if (mPhoneNumber.length()==11&& !TextUtils.isEmpty(etPwd.toString())){
            HttpUtils.login(mPhoneNumber.getText().toString(), etPwd.getText().toString(),new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    Log.v("nihaoma","3333333333");
                    String body = response.body();
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        int code = jsonObject.getInt("code");
                        String  object = jsonObject.getString("object");
                        if (code==100){
                            Gson gson = new Gson();
                            PersonBean personBean = gson.fromJson(object, PersonBean.class);
                            MyApplication application = (MyApplication)getApplication();
                            application.setUer(personBean);
                            Log.v("nihaoma",personBean.toString());
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
                    showLoading();
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

    }
}
