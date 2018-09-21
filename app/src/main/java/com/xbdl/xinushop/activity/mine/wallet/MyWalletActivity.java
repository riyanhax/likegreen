package com.xbdl.xinushop.activity.mine.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xbdl.xinushop.R;
/*
* 我的钱包
* */
public class MyWalletActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView cash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        initView();
        initData();
    }



    private void initView() {
        findViewById(R.id.tv_goback).setOnClickListener(this);
        findViewById(R.id.tv_walletdetail).setOnClickListener(this);
        findViewById(R.id.tv_recharge).setOnClickListener(this);
        findViewById(R.id.rl_make_cash).setOnClickListener(this);
        cash= findViewById(R.id.tv_my_cash);//账户余额
    }
    private void initData() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_goback:
                finish();
                break;
            case R.id.tv_walletdetail://明细
                Intent intentWalletDetailActivity = new Intent(this, WalletDetailActivity.class);
                startActivity(intentWalletDetailActivity);
                break;
            case R.id.tv_recharge://充值
                Intent intentRechargeCenterActivity = new Intent(this, RechargeCenterActivity.class);
                startActivity(intentRechargeCenterActivity);
                break;
            case R.id.rl_make_cash://提现
               /* Intent intentWalletDetailActivity = new Intent(this, WalletDetailActivity.class);
                startActivity(intentWalletDetailActivity);*/
                break;

        }
    }
}
