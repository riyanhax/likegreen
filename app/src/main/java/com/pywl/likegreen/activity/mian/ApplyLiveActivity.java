package com.pywl.likegreen.activity.mian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.pywl.likegreen.MainActivity;
import com.pywl.likegreen.R;

import java.util.Date;

public class ApplyLiveActivity extends AppCompatActivity implements View.OnClickListener {
    private  EditText goodsname,goodsdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_live);
        initView();
    }

    private void initView() {
       findViewById(R.id.iv_return).setOnClickListener(this);
       findViewById(R.id.rl_data_picker).setOnClickListener(this);//选择开播时间
         goodsname = (EditText)findViewById(R.id.et_goodsname);
         goodsdetails = (EditText)findViewById(R.id.et_goodsdetails);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_return:
                finish();
                break;
            case R.id.rl_data_picker:
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                       // Toast.makeText(this, getTime(date), Toast.LENGTH_SHORT).show();
                    }
                }).build();
                break;
        }
    }
}
