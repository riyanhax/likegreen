package com.pywl.likegreen.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pywl.likegreen.R;


/**
 * Created by theWind on 2018/8/6.
 */

public class ToPRomoteDialog extends Dialog {
    private TextView tv_promote_phone,tv_to_promote_cancel,to_to_promte_consulting;
    public ToPRomoteDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_to_promote);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }
    private void initView() {
        tv_promote_phone=(TextView)findViewById(R.id.tv_promote_phone);
        tv_to_promote_cancel=(TextView)findViewById(R.id.tv_to_promote_cancel);
        to_to_promte_consulting=(TextView)findViewById(R.id.to_to_promte_consulting);
    }

    private void initData() {


    }

    private void initEvent() {
        //点击取消
        tv_to_promote_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        //点击立即咨询
        to_to_promte_consulting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"立即咨询",Toast.LENGTH_SHORT).show();
            }
        });
    }




}
