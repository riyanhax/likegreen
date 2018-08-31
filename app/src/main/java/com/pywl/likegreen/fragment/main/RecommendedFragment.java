package com.pywl.likegreen.fragment.main;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.netease.neliveplayer.playerkit.sdk.PlayerManager;
import com.netease.neliveplayer.playerkit.sdk.VodPlayer;
import com.netease.neliveplayer.playerkit.sdk.VodPlayerObserver;
import com.netease.neliveplayer.playerkit.sdk.model.MediaInfo;
import com.netease.neliveplayer.playerkit.sdk.model.StateInfo;
import com.netease.neliveplayer.playerkit.sdk.model.VideoBufferStrategy;
import com.netease.neliveplayer.playerkit.sdk.model.VideoOptions;
import com.netease.neliveplayer.playerkit.sdk.model.VideoScaleMode;
import com.netease.neliveplayer.playerkit.sdk.view.AdvanceSingleTextureView;
import com.netease.neliveplayer.playerkit.sdk.view.AdvanceSurfaceView;
import com.netease.neliveplayer.sdk.NEDefinitionData;
import com.netease.neliveplayer.sdk.NELivePlayer;
import com.pywl.likegreen.R;
import com.pywl.likegreen.view.VerticalViewPager;


import java.util.ArrayList;
import java.util.List;




/**
 * Created by theWind on 2018/8/1.
 * 首页推荐
 */

public class RecommendedFragment extends Fragment implements View.OnClickListener {
    private String TAG = "ShortVideoActivity";
    private View mIvRecommendShare;
    private PopupWindow popupWindow;



    private VerticalViewPager mViewPager;
    private VodPagerAdapter mPagerAdapter;
    private AdvanceSingleTextureView surfaceView; //或者使用 AdvanceMultiTextureView
   // private AdvanceSurfaceView surfaceView; //或者使用 AdvanceMultiTextureView
    private int mCurrentPosition = 0;
    private VodPlayer player;
    private PlayerInfo playerInfo;
    private List<String> mLiveUrlList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended, container, false);
        initDate();
        initView(view);
        return view;
    }

    private void initView(View v) {
        //分享
      /*  mIvRecommendShare = v.findViewById(R.id.iv_recommend_share);
        mIvRecommendShare.setOnClickListener(this);*/
        //视频
        mViewPager = v.findViewById(R.id.recommend_viewpager);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled mCurrentId == " + position + ", positionOffset == " + positionOffset +
                        ", positionOffsetPixels == " + positionOffsetPixels);

//                mCurrentPosition = position;
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "mVerticalViewPager, onPageSelected position = " + position);
                mCurrentPosition = position;
                // 滑动界面，首先让之前的播放器暂停，并seek到0
                Log.i(TAG, "滑动后，让之前的播放器暂停，player = " + player);
                if (player != null) {
                    player.seekTo(0);
                    player.pause();
                }
            }
        });

        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                Log.i(TAG, "transformPage page.id == " + page.getId() + ", position == " + position + ",mCurrentPosition == " + mCurrentPosition);

                if (position != 0) {
                    return;
                }

                ViewGroup viewGroup = (ViewGroup) page;
                surfaceView = (AdvanceSingleTextureView) viewGroup.findViewById(R.id.live_texture);
                //surfaceView = (AdvanceSurfaceView) viewGroup.findViewById(R.id.live_texture);
                playerInfo = mPagerAdapter.findPlayerInfo(mCurrentPosition);
                if (playerInfo != null) {
                    playerInfo.vodPlayer.start();
                    player = playerInfo.vodPlayer;
                }
            }
        });

        mPagerAdapter = new VodPagerAdapter(mLiveUrlList);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mPagerAdapter);
        //mViewPager.setPageTransformer(false, new DefaultTransformer());
    }

    private void initDate() {
        mLiveUrlList = new ArrayList<>();
        mLiveUrlList.add("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/1BrIAtvV_1818587477_shd.mp4");
        mLiveUrlList.add("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/FmdVOTqd_1818586962_shd.mp4");
        mLiveUrlList.add("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/wq1e35cQ_1818588221_shd.mp4");
        mLiveUrlList.add("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/7eSdPRKt_1818589543_shd.mp4");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /*case R.id.iv_recommend_share:
                showPopuWindow(view);
                break;*/
        }
    }

    private void showPopuWindow(View view) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_recommend_share, null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPagerAdapter.destroy();
        if (player != null) {
            player.registerPlayerObserver(playerObserver, false);
            player.setupRenderView(null, VideoScaleMode.NONE);
            player.stop();
            player = null;
        }
    }




    public class VodPagerAdapter extends PagerAdapter {
        private ArrayList<PlayerInfo> playerInfoList = new ArrayList<>();
        private List<String> liveUrlList;


        public VodPagerAdapter(List<String> liveUrlList) {
            this.liveUrlList = liveUrlList;
        }

        @Override
        public int getCount() {
            return liveUrlList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.i(TAG, "instantiateItem " + position);

            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_buffer, null);
            view.setId(position);


            // 获取此player
            PlayerInfo playerInfo = instantiatePlayerInfo(position);
            AdvanceSingleTextureView playView = (AdvanceSingleTextureView) view.findViewById(R.id.live_texture);
            //AdvanceSurfaceView playView = (AdvanceSurfaceView) view.findViewById(R.id.live_texture);
            playerInfo.playerView = playView;
            playerInfo.vodPlayer.setupRenderView(playView, VideoScaleMode.FULL);
            container.addView(view);

            return view;
        }

        protected PlayerInfo instantiatePlayerInfo(int position) {
            Log.i(TAG, "instantiatePlayerInfo " + position);
            PlayerInfo playerInfo = new PlayerInfo();
            VideoOptions options = new VideoOptions();
            options.bufferStrategy = VideoBufferStrategy.ANTI_JITTER;
            options.loopCount = -1;
            VodPlayer vodPlayer = PlayerManager.buildVodPlayer(getContext(), liveUrlList.get(position), options);
            vodPlayer.registerPlayerObserver(playerObserver, true);
            vodPlayer.start();
            playerInfo.playURL = liveUrlList.get(position);
            playerInfo.vodPlayer = vodPlayer;
            playerInfo.position = position;
            playerInfoList.add(playerInfo);
            return playerInfo;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i(TAG, "destroyItem" + position);
            destroyPlayerInfo(position);
            container.removeView((View) object);
        }

        public void destroy() {
            for (int i = 0; i < playerInfoList.size(); i++) {
                PlayerInfo playerInfo = findPlayerInfo(i);
                if (playerInfo == null || playerInfo.vodPlayer == null) {
                    return;
                }
                playerInfo.vodPlayer.stop();
                playerInfo.vodPlayer = null;
            }
            playerInfoList.clear();
            playerInfoList = null;

        }


        public void destroyPlayerInfo(int position) {
            PlayerInfo playerInfo = findPlayerInfo(position);
            if (playerInfo == null || playerInfo.vodPlayer == null) {
                return;
            }
            playerInfo.vodPlayer.stop();
            playerInfo.vodPlayer = null;
            playerInfoList.remove(playerInfo);

            Log.i(TAG, "destroyPlayerInfo " + position);
        }

        public void pausePlayerInfo(int position) {
            PlayerInfo playerInfo = findPlayerInfo(position);
            if (playerInfo == null || playerInfo.vodPlayer == null) {
                return;
            }
            if (playerInfo.vodPlayer.isPlaying()) {
                playerInfo.vodPlayer.pause();
                Log.i(TAG, "pausePlayerInfo " + position);
            }

        }


        public PlayerInfo findPlayerInfo(int position) {
            for (int i = 0; i < playerInfoList.size(); i++) {
                PlayerInfo playerInfo = playerInfoList.get(i);
                if (playerInfo.position == position) {
                    return playerInfo;
                }
            }
            return null;
        }

    }


    private class PlayerInfo {
        public VodPlayer vodPlayer;
        public String playURL;
        public View playerView;
        public int position;
    }

    private VodPlayerObserver playerObserver = new VodPlayerObserver() {
        @Override
        public void onCurrentPlayProgress(long currentPosition, long duration, float percent, long cachedPosition) {

        }

        @Override
        public void onSeekCompleted() {
            Log.i(TAG, "onSeekCompleted");
        }

        @Override
        public void onCompletion() {

        }

        @Override
        public void onNetStateBad() {

        }

        @Override
        public void onDecryption(int ret) {
            Log.i(TAG, "onDecryption ret = " + ret);
        }

        @Override
        public void onPreparing() {

        }

        @Override
        public void onPrepared(MediaInfo info) {
            Log.i(TAG, "MediaInfo info = " + info);
        }

        @Override
        public void onError(int code, int extra) {
            AlertDialog.Builder build = new AlertDialog.Builder(getContext());
            build.setTitle("播放错误").setMessage("错误码：" + code)
                    .setPositiveButton("确定", null)
                    .setCancelable(false)
                    .show();
        }

        @Override
        public void onFirstVideoRendered() {
            Log.i(TAG, "onFirstVideoRendered ");
            for (int i = 0; i < mLiveUrlList.size(); i++) {
                if (i != mCurrentPosition) {
                    mPagerAdapter.pausePlayerInfo(i);
                }
            }
        }

        @Override
        public void onFirstAudioRendered() {
            Log.i(TAG, "onFirstAudioRendered ");

        }

        @Override
        public void onBufferingStart() {
//                mBuffer.setVisibility(View.VISIBLE);

        }

        @Override
        public void onBufferingEnd() {
//                mBuffer.setVisibility(View.GONE);

        }

        @Override
        public void onBuffering(int percent) {
            Log.d(TAG, "缓冲中..." + percent + "%");
        }

        @Override
        public void onHardwareDecoderOpen() {

        }

        @Override
        public void onStateChanged(StateInfo stateInfo) {

        }

        @Override
        public void onParseDefinition(List<NEDefinitionData> data) {
            showToast("解析到多个清晰度");
        }

        @Override
        public void onAutoSwitchDefinition(NEDefinitionData.DefinitionType definitionType) {
            showToast("自动切换到清晰度" + definitionType);
        }

        @Override
        public void onHttpResponseInfo(int code, String header) {
            Log.i(TAG, "onHttpResponseInfo,code:" + code + " header:" + header);
        }

        @Override
        public void onVideoFrameFilter(final NELivePlayer.NEVideoRawData videoRawData) {

        }

        @Override
        public void onAudioFrameFilter(final NELivePlayer.NEAudioRawData audioRawData) {

        }
    };

    private void showToast(String msg) {
        Log.d(TAG, "showToast" + msg);
        try {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
