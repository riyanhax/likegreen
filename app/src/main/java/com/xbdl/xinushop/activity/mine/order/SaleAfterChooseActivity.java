package com.xbdl.xinushop.activity.mine.order;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;

/**
 * 申请售后 选择类型
 * */
public class SaleAfterChooseActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_after_choose);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return).setOnClickListener(this);
        findViewById(R.id.rl_return_goods).setOnClickListener(this);
        findViewById(R.id.rl_refund).setOnClickListener(this);
        findViewById(R.id.exchange_goods).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_return:
                finish();
                break;
            case R.id.rl_return_goods://退货
                Intent intent = new Intent(getActivity(), SaleAfterActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.rl_refund://退货退款
                Intent intent2 = new Intent(getActivity(), SaleAfterActivity.class);
                intent2.putExtra("type",2);
                startActivity(intent2);
                break;
            case R.id.exchange_goods://换货
                Intent intent3 = new Intent(getActivity(), SaleAfterActivity.class);
                intent3.putExtra("type",3);
                startActivity(intent3);
                break;

        }
    }

    @Override
    protected Activity getActivity() {
        return this;
    }
}
