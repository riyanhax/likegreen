package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;

/*
* 我发布的商品交易信息
* */
public class MyProductsDetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private LRecyclerView recyclerView;
    private int activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products_detail);
        Intent intent = getIntent();
        activity= intent.getIntExtra("activity",0);
        initView();
        initData();
    }

    private void initData() {
        findViewById(R.id.iv_return).setOnClickListener(this);
        title= findViewById(R.id.tv_title);
        recyclerView= findViewById(R.id.recyclerView);
    }

    private void initView() {
        if (activity==0){
            //交易消息
            title.setText(getResources().getString(R.string.transactionmsg));
        }else {
            //售后消息
            title.setText(getResources().getString(R.string.aftersalemsg));
        }
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_return:
                    finish();
                    break;
            }
    }
}
