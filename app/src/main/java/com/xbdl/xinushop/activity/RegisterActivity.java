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
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MainActivity;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.utils.CountDownUtil;
import com.xbdl.xinushop.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText mPhoneNumber,et_code, etPwd;
    private TextView sendCode;
    private CheckBox cb_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initData();
    }



    private void initView() {
        mPhoneNumber = (EditText)findViewById(R.id.et_register_phone);
         et_code = (EditText)findViewById(R.id.et_register_code);
         etPwd = (EditText)findViewById(R.id.et_register_pwd);
         sendCode = (TextView)findViewById(R.id.tv_send_code);
         sendCode();
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
         findViewById(R.id.tv_register).setOnClickListener(this);//注册
    }
    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case  R.id.tv_register:

                break;
        }
    }
    private void sendCode(){
        new CountDownUtil(sendCode)
                .setCountDownMillis(60_000L)//倒计时60000ms
                .setCountDownColor(android.R.color.holo_blue_light,android.R.color.darker_gray)//不同状态字体颜色
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("MainActivity","发送成功");
                    }
                })
                .start();
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
        if (TextUtils.isEmpty(et_code.getText().toString())){
            Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT).show();
            return;
        }

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
                            Log.v("nihaoma",personBean.toString());
                            dismissLoading();
                            Intent intentMainActivity = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intentMainActivity);
                            finish();
                        }else {
                            String msg = jsonObject.getString("msg");
                            Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_SHORT).show();
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
