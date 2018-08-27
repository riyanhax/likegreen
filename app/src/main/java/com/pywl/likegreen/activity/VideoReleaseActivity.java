package com.pywl.likegreen.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.pywl.likegreen.MainActivity;
import com.pywl.likegreen.R;
import com.pywl.likegreen.bean.CallTab;

import org.greenrobot.eventbus.EventBus;

public class VideoReleaseActivity extends AppCompatActivity implements View.OnClickListener {
    private View mNextStep;//下一步
    private RadioGroup mVideoType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_release);
        initView();
        initData();
    }

    private void initView() {
        mNextStep = findViewById(R.id.rl_nextstep_btn);//下一步
        mNextStep.setOnClickListener(this);
        mVideoType = (RadioGroup)findViewById(R.id.rg_video_type);


    }

    private void initData() {

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_nextstep_btn://下一步
                if (mVideoType.getCheckedRadioButtonId()==R.id.rb_putongvideo){
                    Log.v("nihaoma","普通视频");
                    Intent intentMain = new Intent(VideoReleaseActivity.this, MainActivity.class);
                    startActivity(intentMain);
                    EventBus.getDefault().post(CallTab.MINE);
                    finish();
                }else {
                    Log.v("nihaoma","广告视频");
                }
                break;
        }
    }
}
