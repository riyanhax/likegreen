package com.xbdl.xinushop.activity.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.xbdl.xinushop.R;

import cn.jzvd.JZVideoPlayer;

/*
* 点播，播放页面
*
* */
public class MyVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private View mVideoStop,mControlview,mVideoReturn;
    private  PLVideoTextureView videoPlayer;
    private boolean isplay=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_video);
        Intent intent = getIntent();
        String url = intent.getStringExtra("MyVideoFragment");
        videoPlayer= (PLVideoTextureView)findViewById(R.id.my_video);
        videoPlayer.setAVOptions(createAVOptions());
        videoPlayer.setVideoPath(url);
        videoPlayer.setDisplayAspectRatio(PLVideoTextureView.ASPECT_RATIO_PAVED_PARENT);
        videoPlayer.start();
        mVideoStop = findViewById(R.id.iv_video_stop);
        mControlview = findViewById(R.id.control_view);
        mControlview.setOnClickListener(this);
        findViewById(R.id.iv_video_return).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.control_view:
                controlVideo(isplay);
                break;
            case R.id.iv_video_return:
                finish();
                break;
        }
    }

    private void controlVideo(boolean b) {
        if (b){
            videoPlayer.start();
            mVideoStop.setVisibility(View.GONE);
            isplay=false;
        }else {
            videoPlayer.pause();
            mVideoStop.setVisibility(View.VISIBLE);
            isplay=true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.start();
    }

    @Override
    public void onBackPressed() {
        videoPlayer.stopPlayback();
        super.onBackPressed();
    }
    public  AVOptions createAVOptions() {
        AVOptions options = new AVOptions();
        // the unit of timeout is ms
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 0);
        // 1 -> hw codec enable, 0 -> disable [recommended]
        options.setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_SW_DECODE);
        options.setInteger(AVOptions.KEY_PREFER_FORMAT, AVOptions.PREFER_FORMAT_MP4);
        boolean disableLog = false;
        options.setInteger(AVOptions.KEY_LOG_LEVEL, disableLog ? 5 : 0);
        return options;
    }
}
