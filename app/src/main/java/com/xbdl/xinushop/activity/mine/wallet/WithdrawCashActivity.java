package com.xbdl.xinushop.activity.mine.wallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.utils.ToastUtil;

/**
 * 余额提现
 * */
public class WithdrawCashActivity extends AppCompatActivity implements View.OnClickListener {
    private  View iv_weixin,iv_aliplay;
    private EditText et_payee,et_payeenumber,et_payeemoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_cash);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return).setOnClickListener(this);
        findViewById(R.id.tv_commit_makecash).setOnClickListener(this);
        findViewById(R.id.ll_weixin_play).setOnClickListener(this);
        findViewById(R.id.ll_aliplay).setOnClickListener(this);
        iv_weixin = findViewById(R.id.iv_weixin_check);
        iv_aliplay = findViewById(R.id.iv_aliplay_check);
        et_payee = (EditText)findViewById(R.id.et_payee);
        et_payeenumber = (EditText)findViewById(R.id.et_payeenumber);
        et_payeemoney = (EditText)findViewById(R.id.et_payeemoney);

    }
    private boolean isShow=true;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_weixin_play:
                iv_weixin.setVisibility(View.VISIBLE);
                iv_aliplay.setVisibility(View.INVISIBLE);
                isShow = true;
                break;
            case R.id.ll_aliplay:
                iv_weixin.setVisibility(View.INVISIBLE);
                iv_aliplay.setVisibility(View.VISIBLE);
                isShow = false;
                break;
            case R.id.iv_return:
                finish();
                break;
            case R.id.tv_commit_makecash://提交
                if (!TextUtils.isEmpty(et_payee.getText())&&!TextUtils.isEmpty(et_payeenumber.getText())&&!TextUtils.isEmpty(et_payeemoney.getText())){

                }else {
                    ToastUtil.shortToast(this,"请完整填写信息");
                }
                break;
        }
    }
}
