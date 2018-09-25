package com.xbdl.xinushop.activity;

import android.content.Intent;
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

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MainActivity;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.utils.AESUtils;
import com.xbdl.xinushop.utils.CountDownUtil;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText mPhoneNumber,et_code, etPwd;
    private TextView sendCode, tv_register;
    private CheckBox cb_pwd;
    private  int loginActivity;
    private View userXieyi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent = getIntent();
        loginActivity= intent.getIntExtra("LoginActivity", 1);
        initView();
        initData();
    }



    private void initView() {
        mPhoneNumber = (EditText)findViewById(R.id.et_register_phone);
         et_code = (EditText)findViewById(R.id.et_register_code);
         etPwd = (EditText)findViewById(R.id.et_register_pwd);
         sendCode = (TextView)findViewById(R.id.tv_send_code);
         sendCode.setOnClickListener(this);
         cb_pwd = findViewById(R.id.cb_show_pwd);
         cb_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        tv_register= (TextView)findViewById(R.id.tv_register);//注册
        tv_register.setOnClickListener(this);
        View userXieyi = findViewById(R.id.ll_user_text);
    }
    private void initData() {
        if (loginActivity==0){
            tv_register.setText("修改密码");
        }else {
            tv_register.setText("注册");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.tv_send_code:
                if (mPhoneNumber.length()!=11){
                    Toast.makeText(this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
                    return;
                }
                    sendCode();

                break;
            case  R.id.tv_register:
                    register();
                break;
        }
    }




    private CountDownUtil time;
    private void sendCode(){
        if (mPhoneNumber.length()!=11){
            Toast.makeText(RegisterActivity.this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
            return;
        }

        HttpUtils.sendCode(mPhoneNumber.getText().toString(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    int code = jsonObject.getInt("code");
                    if (code==100){
                        Toast.makeText(RegisterActivity.this,"短信已发送",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegisterActivity.this,"发送不成功",Toast.LENGTH_SHORT).show();


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


/*         time=new CountDownUtil(sendCode)
                .setCountDownMillis(60_000L)//倒计时60000ms
                .setCountDownColor(android.R.color.holo_blue_light,android.R.color.darker_gray)//不同状态字体颜色
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (mPhoneNumber.length()!=11){
                            Toast.makeText(RegisterActivity.this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
                            return;
                        }

                       HttpUtils.sendCode(mPhoneNumber.getText().toString(), new StringCallback() {
                           @Override
                           public void onSuccess(Response<String> response) {
                               String body = response.body();
                               try {
                                   JSONObject jsonObject = new JSONObject(body);
                                   int code = jsonObject.getInt("code");
                                   if (code==100){
                                       Toast.makeText(RegisterActivity.this,"短信已发送",Toast.LENGTH_SHORT).show();
                                   }else {
                                       Toast.makeText(RegisterActivity.this,"发送不成功",Toast.LENGTH_SHORT).show();


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
                })
                .start();*/
    }




    private void register(){
        if (mPhoneNumber.length()!=11){
            Toast.makeText(this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etPwd.getText().toString())){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(et_code.getText().toString())){
            Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT).show();
            return;
        }
        final String phonenum = mPhoneNumber.getText().toString();
        final String pwd = etPwd.getText().toString();
        String code = et_code.getText().toString();
        if (mPhoneNumber.length()==11&& !TextUtils.isEmpty(etPwd.toString())&& !TextUtils.isEmpty(et_code.toString())){
            if (loginActivity==0){
                HttpUtils.forget(phonenum,pwd,code, new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            int code = jsonObject.getInt("code");
                            String  object = jsonObject.getString("object");
                            if (code==100){
                                Toast.makeText(RegisterActivity.this,object,Toast.LENGTH_SHORT).show();
                                //加密
                                String phoneEncode = AESUtils.encryptString(phonenum, MyConstants.Key);
                                String pwdEncode = AESUtils.encryptString(pwd, MyConstants.Key);
                                // SharedPreferencesUtil.putString(RegisterActivity.this,MyConstants.PHONE,phoneEncode);
                                // SharedPreferencesUtil.putString(RegisterActivity.this,MyConstants.PASSWORD,pwdEncode);
                                dismissLoading();
                                Intent intentMainActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intentMainActivity);
                                // time.reset();
                                finish();
                            }else {

                                Toast.makeText(RegisterActivity.this,object,Toast.LENGTH_SHORT).show();
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
            }else {
                HttpUtils.register(phonenum,pwd,code,new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            int code = jsonObject.getInt("code");
                            String  object = jsonObject.getString("object");
                            if (code==100){
                                Toast.makeText(RegisterActivity.this,object,Toast.LENGTH_SHORT).show();
                                //加密
                                String phoneEncode = AESUtils.encryptString(phonenum, MyConstants.Key);
                                String pwdEncode = AESUtils.encryptString(pwd, MyConstants.Key);
                                // SharedPreferencesUtil.putString(RegisterActivity.this,MyConstants.PHONE,phoneEncode);
                                // SharedPreferencesUtil.putString(RegisterActivity.this,MyConstants.PASSWORD,pwdEncode);
                                dismissLoading();
                                Intent intentMainActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intentMainActivity);
                                // time.reset();
                                finish();
                            }else {

                                Toast.makeText(RegisterActivity.this,object,Toast.LENGTH_SHORT).show();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        OkGo.getInstance().cancelTag(this);
    }
}
