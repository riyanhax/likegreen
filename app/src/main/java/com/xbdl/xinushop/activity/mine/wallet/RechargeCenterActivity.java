package com.xbdl.xinushop.activity.mine.wallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.utils.EditTextUtils;
import com.xbdl.xinushop.utils.ToastUtil;
import com.xbdl.xinushop.view.MyRadioGroup;

public class RechargeCenterActivity extends AppCompatActivity implements View.OnClickListener {
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
                break;
        }
    }
}