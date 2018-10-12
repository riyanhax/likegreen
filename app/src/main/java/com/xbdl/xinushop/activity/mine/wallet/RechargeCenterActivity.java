package com.xbdl.xinushop.activity.mine.wallet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.PayResult;
import com.xbdl.xinushop.bean.WXplayBean;
import com.xbdl.xinushop.utils.EditTextUtils;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;
import com.xbdl.xinushop.utils.WXPayUtils;
import com.xbdl.xinushop.view.MyRadioGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class RechargeCenterActivity extends BaseActivity implements View.OnClickListener {
    private static final int SDK_PAY_FLAG = 0;
    private MyRadioGroup money1;
    private RadioButton rb_100,rb_200,rb_300,rb_500,rb_800,rb_other;
    private EditText etamount;//充值金额
    private View iv_weixin,iv_aliplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_center);
        initView();
        initData();
    }

    private void initView() {
         money1 = findViewById(R.id.rg_money1);
         rb_100 = findViewById(R.id.rb_100);
         rb_200 = findViewById(R.id.rb_200);
         rb_300 = findViewById(R.id.rb_300);
         rb_500 = findViewById(R.id.rb_500);
         rb_800 = findViewById(R.id.rb_800);
         rb_other = findViewById(R.id.rb_other);
         etamount= findViewById(R.id.et_recharge_amount);
         //限制输入
         EditTextUtils.afterDotTwo(etamount);
         findViewById(R.id.ll_weixin_play).setOnClickListener(this);//微信支付栏
         findViewById(R.id.ll_aliplay).setOnClickListener(this);//支付宝支付栏
         findViewById(R.id.ll_rechargeprotocol).setOnClickListener(this);//充值协议
         iv_weixin = findViewById(R.id.iv_weixin_check);
         iv_aliplay = findViewById(R.id.iv_aliplay_check);
         findViewById(R.id.iv_return).setOnClickListener(this);
         findViewById(R.id.tv_commit_recharge).setOnClickListener(this);
    }

    private void initData() {
        money1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_100:
                        rb_100.setChecked(true);
                        rb_500.setChecked(false);
                        rb_800.setChecked(false);
                        rb_other.setChecked(false);
                        etamount.setText(String.valueOf(100));
                        break;
                    case R.id.rb_200:
                        rb_200.setChecked(true);
                        rb_500.setChecked(false);
                        rb_800.setChecked(false);
                        etamount.setText(String.valueOf(200));
                        break;
                    case R.id.rb_300:
                        rb_300.setChecked(true);
                        rb_500.setChecked(false);
                        rb_800.setChecked(false);
                        rb_other.setChecked(false);
                        etamount.setText(String.valueOf(300));
                        break;
                    case R.id.rb_500:
                        rb_500.setChecked(true);
                        rb_100.setChecked(false);
                        rb_200.setChecked(false);
                        rb_300.setChecked(false);
                        etamount.setText(String.valueOf(500));
                        break;
                    case R.id.rb_800:
                        rb_800.setChecked(true);
                        rb_100.setChecked(false);
                        rb_200.setChecked(false);
                        rb_300.setChecked(false);
                        etamount.setText(String.valueOf(800));
                        break;
                    case R.id.rb_other:
                        rb_other.setChecked(true);
                        rb_100.setChecked(false);
                        rb_200.setChecked(false);
                        rb_300.setChecked(false);
                        etamount.setText("");
                        etamount.setHint(R.string.etrechargeamount);
                        break;
                }
            }
        });
    }
    private boolean isShow=true;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_weixin_play:
                iv_weixin.setVisibility(View.VISIBLE);
                iv_aliplay.setVisibility(View.INVISIBLE);
                isShow=true;
                break;
            case R.id.ll_aliplay:
                iv_weixin.setVisibility(View.INVISIBLE);
                iv_aliplay.setVisibility(View.VISIBLE);
                isShow=false;
                break;
            case R.id.ll_rechargeprotocol://充值协议

                break;
            case R.id.iv_return:
                finish();
                break;
            case R.id.tv_commit_recharge:
                if (TextUtils.isEmpty(etamount.getText())){
                    ToastUtil.shortToast(this,"请输入金额");
                    return;
                }

                String money = etamount.getText().toString();
                if (Float.parseFloat(money)<1){
                    ToastUtil.shortToast(this,"请输入大于1金额");
                    return;
                }
                if(isShow){
                    pay(money,2);
                }else {
                    pay(money,1);
                }
                break;
        }
    }

    private void pay(final String money, final int payWay) {
        HttpUtils2.balanceRecharge(MyApplication.user.getLoginToken(),MyApplication.user.getUserId(), payWay, Float.parseFloat(money), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    String one=money;
                    JSONObject body = new JSONObject(response.body());
                    String result = body.getString("result");
                    if (payWay==2){
                        //微信支付
                        Gson gson = new Gson();
                        WXplayBean wXplayBean = gson.fromJson(result, WXplayBean.class);
                        if (wXplayBean.getCode()==1){
                            WXplayBean.JsonBean json = wXplayBean.getJson();
                            WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
                            builder.setAppId(json.getAppid())
                                    .setPartnerId(json.getPartnerid())
                                    .setPrepayId(json.getPrepayid())
                                    .setPackageValue("Sign=WXPay")
                                    .setNonceStr(json.getNoncestr())
                                    .setTimeStamp(String.valueOf(json.getTimestamp()))
                                    .setSign(json.getSign())
                                    .build().toWXPayNotSign(RechargeCenterActivity.this);
                        }else {
                            ToastUtil.shortToast(RechargeCenterActivity.this,wXplayBean.getMsg());
                        }
                    }else {
                        //支付宝支付
                        JSONObject jsonObject = new JSONObject(result);
                        String body1 = jsonObject.getString("body");
                        getAlipay(body1);
                    }


                } catch (JSONException e) {

                }
                dismissLoading();
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                showLoading();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dismissLoading();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissLoading();
            }
        });
    }

    @Override
    protected Activity getActivity() {
        return RechargeCenterActivity.this;
    }
   private void  getAlipay(final String orderInfo){
        //订单签名后的信息（服务器返回的数据）

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(RechargeCenterActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    //支付宝返回数据handler
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    String result = "";
                    // 判断resultStatus 为9000则代表支付成功
                    Log.i("nihaoma", resultStatus);
                    if (TextUtils.equals(resultStatus, "9000")) {
                        //支付成功
                        result = "支付成功";
                       // aliPaySuccess();
                    } else if ("6001".equals(resultStatus)) {
                        result = "您取消了支付";
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        result = "支付失败";
                    }
                    ToastUtil.shortToast(RechargeCenterActivity.this,result);
                    break;
                }
            }
        }
    };




}