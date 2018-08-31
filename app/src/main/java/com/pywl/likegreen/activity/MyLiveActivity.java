package com.pywl.likegreen.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pywl.likegreen.R;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/*
* 点播，播放页面
*
* */
public class MyLiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_live);
        Intent intent = getIntent();
        String url = intent.getStringExtra("MyVideoFragment");
        JZVideoPlayerStandard videoPlayer = (JZVideoPlayerStandard)findViewById(R.id.video_player);

        videoPlayer.startFullscreen(this, JZVideoPlayerStandard.class,url, "");
    }
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() { //选择适当的声明周期释放
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
