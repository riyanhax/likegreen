package com.pywl.likegreen.adapter.live;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pywl.likegreen.R;
import com.pywl.likegreen.adapter.ListBaseAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.qssq666.videoplayer.playermanager.manager.VideoItem;


public class HomeLiveAdapter3 extends RecyclerView.Adapter<HomeLiveAdapter3.ViewHolder> {
    private ArrayList<String> mItemList;
    private ViewHolder mCurViewHolder;
    private DisplayImageOptions mDisplayImageOptions;
    private Context mContext;
    private  VideoOptions options;
    public HomeLiveAdapter3(ArrayList<String> arrayList,Context context) {
        mItemList = arrayList;
        mContext=context;
        options= new VideoOptions();
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
        options.bufferStrategy = VideoBufferStrategy.LOW_LATENCY;
    }

   public class ViewHolder extends RecyclerView.ViewHolder {
        AdvanceSingleTextureView videoView;
        String videoPath;
        View holderRootView;

        public ViewHolder(View itemView) {
            super(itemView);
            holderRootView = itemView;
            videoView = (AdvanceSingleTextureView)itemView.findViewById(R.id.live_texture);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_home_live, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }


      @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String videoItem = mItemList.get(position);
        holder.videoPath = videoItem;
        holder.holderRootView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        mCurViewHolder = holder;

    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        if (player!=null)
        player.stop();
    }


    public void setCurViewHolder(ViewHolder viewHolder) {
        mCurViewHolder = viewHolder;
    }

    public void startCurVideoView() {
        if (mCurViewHolder != null) {
            VideoOptions options= new VideoOptions();
            options.autoSwitchDefinition = false;
            options.hardwareDecode = false;
            options.isPlayLongTimeBackground = true;
            options.bufferStrategy = VideoBufferStrategy.LOW_LATENCY;
            player = PlayerManager.buildLivePlayer(mContext, mCurViewHolder.videoPath, options);
            player.registerPlayerObserver(playerObserver, true);
            if (!player.isPlaying()){
                player.start();
                    player.setupRenderView(mCurViewHolder.videoView, VideoScaleMode.FILL);
            }
        }
    }


    public void stopCurVideoView() {
        if (mCurViewHolder != null&&player!=null) {
            player.registerPlayerObserver(playerObserver, false);
            player.setupRenderView(null, VideoScaleMode.NONE);
            mCurViewHolder.videoView.releaseSurface();
            mCurViewHolder.videoView=null;
            player.stop();
            player=null;
        }
    }
    private LivePlayer player;

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
