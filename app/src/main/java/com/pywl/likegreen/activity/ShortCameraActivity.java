package com.pywl.likegreen.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pywl.likegreen.R;


/*
* 发布短视频点进来的照相机
* */
public class ShortCameraActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int SET_COUNT_TIME = 0;
    private  View mTakePhotoHead,mTakePhotofilter,mTakePhotoAlbum,mTakePhotoscBg,mTakePhotoStr,mTakePhotoCancel,mTakePhotoChoose;//顶部一栏,滤镜,拍照按钮,相册,读秒
   private  ImageView mTakePhotoBtn;
    private boolean ishide=true;
    private int countTime=0;
    private TextView mTvCountTime;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SET_COUNT_TIME:
                   countTime= (int) msg.obj;
                    Log.v("nihaoma",""+countTime);
                    mTvCountTime.setText(String.valueOf(countTime));
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_camera);
        initView();
        initData();
    }

    private void initView() {
         mTakePhotoHead = findViewById(R.id.rl_takephoto_head);//顶部一栏
         mTakePhotofilter = findViewById(R.id.ll_takephoto_filter);//滤镜
         mTakePhotoAlbum = findViewById(R.id.ll_takephoto_album);//相册
         mTakePhotoscBg = findViewById(R.id.rl_takephoto_sc_bg);//读秒
         mTakePhotoBtn = (ImageView)findViewById(R.id.iv_takephoto_btn);//拍照
         mTakePhotoBtn.setOnClickListener(this);
         mTakePhotoStr = findViewById(R.id.tv_takephoto_str);//点击拍照字
         mTakePhotoCancel = findViewById(R.id.iv_takephoto_cancel);//取消
         mTakePhotoChoose = findViewById(R.id.iv_takephoto_choose);//确认
         mTakePhotoChoose.setOnClickListener(this);
         mTvCountTime = (TextView)findViewById(R.id.tv_takephoto_counttime);
    }

    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_takephoto_btn:
                hideView(ishide);
                break;
            case R.id.iv_takephoto_choose:
                Intent intentVideoReleaseActivity = new Intent(ShortCameraActivity.this, VideoReleaseActivity.class);
                startActivity(intentVideoReleaseActivity);
                finish();
                break;
        }
    }

    private void hideView(boolean b) {
        if (b){
            mTakePhotoHead.setVisibility(View.GONE);
            mTakePhotofilter.setVisibility(View.GONE);
            mTakePhotoAlbum.setVisibility(View.GONE);
            mTakePhotoStr.setVisibility(View.GONE);
            mTakePhotoscBg.setVisibility(View.VISIBLE);
            mTakePhotoBtn.setImageResource(R.drawable.recording);
            setTime();
            mTakePhotoCancel.setVisibility(View.GONE);
            mTakePhotoChoose.setVisibility(View.GONE);
            ishide=false;
        }else {
            mTakePhotoHead.setVisibility(View.VISIBLE);
            mTakePhotofilter.setVisibility(View.VISIBLE);
            mTakePhotoAlbum.setVisibility(View.VISIBLE);
            mTakePhotoStr.setVisibility(View.VISIBLE);
            mTakePhotoscBg.setVisibility(View.GONE);
            mTakePhotoBtn.setImageResource(R.drawable.photograph);
            if (countTime==0){
                mTakePhotoCancel.setVisibility(View.GONE);
                mTakePhotoChoose.setVisibility(View.GONE);
            }else {
                mTakePhotoCancel.setVisibility(View.VISIBLE);
                mTakePhotoChoose.setVisibility(View.VISIBLE);
            }

            ishide=true;
        }
    }

    private void setTime() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            countTime++;
                            Thread.sleep(1000);
                            Message message = Message.obtain();
                            message.what = SET_COUNT_TIME;
                            message.obj = countTime;
                            mHandler.sendMessage(message);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }


