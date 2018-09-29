package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.LoginActivity;
import com.xbdl.xinushop.activity.RegisterActivity;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePhoneNumActivity extends BaseActivity implements View.OnClickListener {
    private EditText mPhoneNumber, et_code, etPwd;
    private TextView sendCode, tv_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_num);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return).setOnClickListener(this);
        mPhoneNumber = (EditText) findViewById(R.id.et_register_phone);
        et_code = (EditText) findViewById(R.id.et_register_code);
        etPwd = (EditText) findViewById(R.id.et_register_pwd);
        sendCode = (TextView) findViewById(R.id.tv_send_code);
        sendCode.setOnClickListener(this);
        tv_register = (TextView) findViewById(R.id.tv_register);//注册
        tv_register.setOnClickListener(this);
    }

    //忘记密码
    private void forgetpwd(String phonenum, String pwd, String code) {
        HttpUtils.forget(phonenum, pwd, code, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    int code = jsonObject.getInt("code");
                    String object = jsonObject.getString("object");
                    if (code == 100) {
                        Toast.makeText(ChangePhoneNumActivity.this, object, Toast.LENGTH_SHORT).show();
                        //加密
                        //String phoneEncode = AESUtils.encryptString(phonenum, MyConstants.Key);
                        //String pwdEncode = AESUtils.encryptString(pwd, MyConstants.Key);
                        // SharedPreferencesUtil.putString(RegisterActivity.this,MyConstants.PHONE,phoneEncode);
                        // SharedPreferencesUtil.putString(RegisterActivity.this,MyConstants.PASSWORD,pwdEncode);
                        dismissLoading();
                        Intent intentMainActivity = new Intent(ChangePhoneNumActivity.this, LoginActivity.class);
                        startActivity(intentMainActivity);
                        // time.reset();
                        finish();
                    } else {

                        Toast.makeText(ChangePhoneNumActivity.this, object, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                showLoading();
                Log.v("nihaoma", "4444444444");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissLoading();
                Log.v("nihaoma", response + "55555555555");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dismissLoading();
            }
        });
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.tv_send_code:
                if (mPhoneNumber.length() != 11) {
                    Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendCode();

                break;
            case R.id.tv_register:
                register();
                break;
        }

    }

    private void register() {
        if (mPhoneNumber.length() != 11) {
            Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etPwd.getText().toString())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(et_code.getText().toString())) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        String phonenum = mPhoneNumber.getText().toString();
        String pwd = etPwd.getText().toString();
        String code = et_code.getText().toString();
        if (mPhoneNumber.length() == 11 && !TextUtils.isEmpty(etPwd.toString()) && !TextUtils.isEmpty(et_code.toString())) {
            forgetpwd(phonenum, pwd, code);
        }
    }

    private void sendCode() {
        if (mPhoneNumber.length() != 11) {
            Toast.makeText(ChangePhoneNumActivity.this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        HttpUtils.sendCode(mPhoneNumber.getText().toString(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    int code = jsonObject.getInt("code");
                    if (code == 100) {
                        Toast.makeText(ChangePhoneNumActivity.this, "短信已发送", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ChangePhoneNumActivity.this, "发送不成功", Toast.LENGTH_SHORT).show();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                showLoading();
                Log.v("nihaoma", "4444444444");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissLoading();
                Log.v("nihaoma", response + "55555555555");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dismissLoading();
            }
        });
    }
}