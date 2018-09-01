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
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoView;
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
    private PLVideoView surfaceView; //或者使用 AdvanceMultiTextureView

    private int mCurrentPosition = 0;
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
                Log.i(TAG, "滑动后，让之前的播放器暂停，player = " );
                if (surfaceView != null) {
                    surfaceView.seekTo(0);
                    surfaceView.pause();
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
                surfaceView = (PLVideoView) viewGroup.findViewById(R.id.plv_videoview);
                surfaceView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
                surfaceView.start();
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
        if (surfaceView!=null){
            surfaceView.pause();
        }
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
        if (surfaceView!=null){
            surfaceView.start();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (surfaceView != null) {
            surfaceView.stopPlayback();
            surfaceView = null;
        }
    }




    public class VodPagerAdapter extends PagerAdapter {
        private List<String> liveUrlList;
        private PLVideoView playView;
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
            playView = (PLVideoView) view.findViewById(R.id.plv_videoview);
            playView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
            playView.setVideoPath(liveUrlList.get(position));
            playView.start();
            Log.v("nihaoma","2222222222222"+position);
            container.addView(view);
            return view;
        }



        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i(TAG, "destroyItem" + position);

            playView.pause();
            playView.stopPlayback();
            playView=null;
            container.removeView((View) object);
        }


    }



    private void showToast(String msg) {
        Log.d(TAG, "showToast" + msg);
        try {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
