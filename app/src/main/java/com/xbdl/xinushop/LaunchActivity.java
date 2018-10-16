package com.xbdl.xinushop;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.activity.LoginActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        new Handler().postDelayed(r, 2000); //设置2秒钟后切换到下个Activity
    }

    Runnable r = new Runnable() {
        public void run() {
            final String user = SharedPreferencesUtil.getString(LaunchActivity.this, MyConstants.User, "1");
            final boolean islogin = SharedPreferencesUtil.getBoolean(LaunchActivity.this, MyConstants.ISLOGIN, true);
            if (!TextUtils.isEmpty(user)&&!user.equals("1")&&islogin){
                try {
                    JSONObject jsonObject = new JSONObject(user);
                    String loginToken = jsonObject.getString("loginToken");
                    Log.v("nihaoma",loginToken);
                    HttpUtils2.autoLogin(loginToken, new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String body = response.body();
                            try {
                                JSONObject login = new JSONObject(body);

                                int code = login.getInt("code");
                                Log.v("nihaoma","code"+code);
                                if (code==100){
                                    String object = login.getString("object");
                                    Gson gson = new Gson();
                                    PersonBean personBean = gson.fromJson(object, PersonBean.class);
                                    Log.v("nihaoma",personBean.toString());
                                    MyApplication application = (MyApplication)getApplication();
                                    application.setUer(personBean);
                                    SharedPreferencesUtil.putString(LaunchActivity.this,MyConstants.User,object);
                                    Intent intent = new Intent();
                                    intent.setClass(LaunchActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Intent intent = new Intent();
                                    intent.setClass(LaunchActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onStart(Request<String, ? extends Request> request) {
                            super.onStart(request);
                            Log.v("nihaoma","onStart  "+"launch请求开始");
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            ToastUtil.shortToast(LaunchActivity.this,"网络数据错误");
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else {
                Intent intent = new Intent();
                intent.setClass(LaunchActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag("autoLogin");
    }
}
