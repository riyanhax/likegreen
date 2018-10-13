package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
* 直播预告
* */
public class LivePreViewActivity extends BaseActivity {
    private int myLiveFragment;
    @BindView(R.id.iv_return)
    ImageView iv_return ; //绑定button 控件
    @BindView(R.id.tv_title)
    TextView title ; //绑定button 控件
    @BindView(R.id.recyclerView)
    LRecyclerView recyclerView ; //绑定button 控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_pre_view);
        ButterKnife.bind(this);
        Intent intent = getIntent();
         myLiveFragment = intent.getIntExtra("MyLiveFragment", 0);
         initView();
         initData();
    }



    private void initView() {
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initData() {
        switch (myLiveFragment){
            case 1:
                //预告
                title.setText(getResources().getString(R.string.trailer));
                break;
            case 2:
                //回放
                title.setText(getResources().getString(R.string.playback));
                break;
            case 3:
                //即将开播
                title.setText(getResources().getString(R.string.airon));
                break;
            default:
                break;
        }
    }

    @Override
    protected Activity getActivity() {
        return this;
    }


}
