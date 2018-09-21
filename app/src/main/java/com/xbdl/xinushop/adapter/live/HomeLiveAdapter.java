package com.xbdl.xinushop.adapter.live;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.netease.neliveplayer.playerkit.sdk.LivePlayer;
import com.netease.neliveplayer.playerkit.sdk.LivePlayerObserver;
import com.netease.neliveplayer.playerkit.sdk.PlayerManager;
import com.netease.neliveplayer.playerkit.sdk.model.MediaInfo;
import com.netease.neliveplayer.playerkit.sdk.model.StateInfo;
import com.netease.neliveplayer.playerkit.sdk.model.VideoBufferStrategy;
import com.netease.neliveplayer.playerkit.sdk.model.VideoOptions;
import com.netease.neliveplayer.playerkit.sdk.model.VideoScaleMode;
import com.netease.neliveplayer.playerkit.sdk.view.AdvanceSingleTextureView;
import com.netease.neliveplayer.sdk.NEDefinitionData;
import com.netease.neliveplayer.sdk.NELivePlayer;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ListBaseAdapter;


import java.io.IOException;
import java.util.List;




public class HomeLiveAdapter extends ListBaseAdapter<String> {
    private static AdvanceSingleTextureView mTextureView;
    private LayoutInflater mLayoutInflater;
    private static LivePlayer player;
    public HomeLiveAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;

    }
    public List<String> getList() {
        return mDataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeLiveAdapter.MyHolder(mLayoutInflater.inflate(R.layout.item_home_live,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (mDataList!=null){
            HomeLiveAdapter.MyHolder viewHolder = (HomeLiveAdapter.MyHolder) holder;
            mTextureView=viewHolder.textureView;
            play(mDataList.get(position),viewHolder);
        }
    }
    public class MyHolder extends RecyclerView.ViewHolder{
        private AdvanceSingleTextureView textureView;
        public MyHolder(View itemView) {
            super(itemView);
            textureView=itemView.findViewById(R.id.live_texture);
        }
    }
    public static void play(String videoPath, @NonNull MyHolder holder) {
        if (videoPath.startsWith("http")) {
            initPlayer(videoPath,holder);
        } else {
            AssetManager assets =mContext.getAssets();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                AssetFileDescriptor afd = null;
                try {
                    afd = assets.openFd(videoPath);
                    initPlayer(videoPath,holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(mContext, "手机版本过低", Toast.LENGTH_SHORT).show();
            }


        }
    }
    private  static void initPlayer(String videoPath,MyHolder holder) {
        VideoOptions options = new VideoOptions();
        options.autoSwitchDefinition = false;
        options.hardwareDecode = false;

        /**
         * isPlayLongTimeBackground 控制退到后台或者锁屏时是否继续播放，开发者可根据实际情况灵活开发,我们的示例逻辑如下：
         * 使用软件解码：
         * isPlayLongTimeBackground 为 false 时，直播进入后台停止播放，进入前台重新拉流播放
         * isPlayLongTimeBackground 为 true 时，直播进入后台不做处理，继续播放,
         *
         * 使用硬件解码：
         * 直播进入后台停止播放，进入前台重新拉流播放
         */
        options.isPlayLongTimeBackground = true;

//        if (isTimestampEnable) {
//            options.isSyncOpen = true;
//        }

        options.bufferStrategy = VideoBufferStrategy.LOW_LATENCY;
        player = PlayerManager.buildLivePlayer(mContext, videoPath, options);

        player.registerPlayerObserver(playerObserver, true);
        player.start();
        //设置全屏
        if (holder.textureView == null) {
            player.setupRenderView(holder.textureView, VideoScaleMode.FILL);
        } else {
            player.setupRenderView(holder.textureView, VideoScaleMode.FILL);
        }
    }


    public static void stoplive(){
        if (player!=null){
            player.registerPlayerObserver(playerObserver, false);
            player.setupRenderView(null, VideoScaleMode.NONE);
            mTextureView.releaseSurface();
            mTextureView = null;
            player.stop();
            player = null;
        }
    }
    private static LivePlayerObserver playerObserver=new LivePlayerObserver() {

        @Override
        public void onPreparing() {

        }

        @Override
        public void onPrepared(MediaInfo mediaInfo) {

        }

        @Override
        public void onError(int code, int extra) {

        }

        @Override
        public void onFirstVideoRendered() {

        }

        @Override
        public void onFirstAudioRendered() {

        }

        @Override
        public void onBufferingStart() {

        }

        @Override
        public void onBufferingEnd() {

        }

        @Override
        public void onBuffering(int percent) {

        }

        @Override
        public void onHardwareDecoderOpen() {

        }

        @Override
        public void onStateChanged(StateInfo stateInfo) {

        }

        @Override
        public void onParseDefinition(List<NEDefinitionData> data) {

        }

        @Override
        public void onAutoSwitchDefinition(NEDefinitionData.DefinitionType definitionType) {

        }

        @Override
        public void onVideoFrameFilter(NELivePlayer.NEVideoRawData videoRawData) {

        }

        @Override
        public void onAudioFrameFilter(NELivePlayer.NEAudioRawData audioRawData) {

        }

        @Override
        public void onHttpResponseInfo(int code, String header) {

        }
    };
}
