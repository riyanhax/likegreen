package com.xbdl.xinushop.chat;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnPreparedListener;
import com.pili.pldroid.player.PLOnVideoSizeChangedListener;
import com.pili.pldroid.player.widget.PLVideoView;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;

import java.io.File;
import java.util.List;

public class ChatItemActivity extends AppCompatActivity implements PLOnPreparedListener, PLOnInfoListener, PLOnCompletionListener, PLOnVideoSizeChangedListener, PLOnErrorListener {
    private boolean mIsLiveStreaming;
    PLVideoView mVideoView;

    @Override
    protected Activity getActivity() {
        return ChatItemActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_item);
        AppCompatImageView imageView = findViewById(R.id.iv_chatitem);
        mVideoView = findViewById(R.id.pvideo_chatitem);
        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String path = getIntent().getStringExtra("path");

        List<String> list = getIntent().getStringArrayListExtra("pathList");
        if (list != null && list.size() > 0) {
            int position = list.indexOf(path);
            if (position != -1) {
                if (!path.contains(".mp4")) {
                    imageView.setVisibility(View.VISIBLE);
                    mVideoView.setVisibility(View.GONE);
                    imageView.setImageURI(Uri.fromFile(new File(path)));
                } else {
                    imageView.setVisibility(View.GONE);
                    mVideoView.setVisibility(View.VISIBLE);
                    //    mVideoView.setBufferingIndicator(progressBar);
                    mVideoView.setOnPreparedListener(this);
                    mVideoView.setOnInfoListener(this);
                    mVideoView.setOnCompletionListener(this);
                    mVideoView.setOnVideoSizeChangedListener(this);
                    mVideoView.setOnErrorListener(this);
                    int codec = getIntent().getIntExtra("mediaCodec", AVOptions.MEDIA_CODEC_SW_DECODE);
                    AVOptions options = new AVOptions();
                    // the unit of timeout is ms
                    options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
                    // 1 -> hw codec enable, 0 -> disable [recommended]
                    options.setInteger(AVOptions.KEY_MEDIACODEC, codec);
                    // options.setInteger(AVOptions.KEY_LIVE_STREAMING, mIsLiveStreaming ? 1 : 0);
                    boolean disableLog = getIntent().getBooleanExtra("disable-log", false);
//        options.setString(AVOptions.KEY_DNS_SERVER, "127.0.0.1");
                    options.setInteger(AVOptions.KEY_LOG_LEVEL, disableLog ? 5 : 0);
                    boolean cache = getIntent().getBooleanExtra("cache", false);
                    boolean vcallback = getIntent().getBooleanExtra("video-data-callback", false);
                    if (vcallback) {
                        options.setInteger(AVOptions.KEY_VIDEO_DATA_CALLBACK, 1);
                    }
                    boolean acallback = getIntent().getBooleanExtra("audio-data-callback", false);
                    if (acallback) {
                        options.setInteger(AVOptions.KEY_AUDIO_DATA_CALLBACK, 1);
                    }
                    mVideoView.setAVOptions(options);
                    mVideoView.setVideoPath(path);
                    mVideoView.setLooping(true);//播放后自动开始


                }


            }
        }
    }

    @Override
    public void onCompletion() {

    }

    @Override
    public boolean onError(int i) {
        return false;
    }

    @Override
    public void onInfo(int i, int i1) {

    }

    @Override
    public void onVideoSizeChanged(int i, int i1) {

    }

    @Override
    public void onPrepared(int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // mMediaController.getWindow().dismiss();
        mVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }
}
