package com.xbdl.xinushop.fragment.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.RecommendedAdapter;
import com.xbdl.xinushop.bean.CallTab;
import com.xbdl.xinushop.dialogfragment.RecommentCommentDialogFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by theWind on 2018/8/16.
 */

public class ReviewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended, container, false);
        EventBus.getDefault().register(this);

        initDate();
        initView(view);
        return view;
    }

    private void initDate() {
        mItemList = new ArrayList<>();
        mItemList.add("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/1BrIAtvV_1818587477_shd.mp4");
        mItemList.add("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/FmdVOTqd_1818586962_shd.mp4");
        mItemList.add("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/wq1e35cQ_1818588221_shd.mp4");
        mItemList.add("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/7eSdPRKt_1818589543_shd.mp4");
    }


    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                startCurVideoView();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };
    private boolean mShouldPlay=true;
    private RecommendedAdapter mShortVideoListAdapter;
    private ArrayList<String> mItemList;
    private int mCurrentPosition =  -1;
    private RecyclerView mVideoList;
    private void initView(View v) {

        mVideoList = v.findViewById(R.id.video_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mVideoList.setLayoutManager(layoutManager);
        mVideoList.setHasFixedSize(true);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mVideoList);

        mShortVideoListAdapter = new RecommendedAdapter(getActivity(),mItemList);
        mShortVideoListAdapter = new RecommendedAdapter(getActivity(),mItemList);
        mVideoList.setAdapter(mShortVideoListAdapter);
        mVideoList.addOnScrollListener(mOnScrollListener);

        if (mShouldPlay) {
            mVideoList.post(new Runnable() {
                @Override
                public void run() {
                    startCurVideoView();
                    mShouldPlay = false;
                }
            });
        }
        mShortVideoListAdapter.setMyViewClick(new RecommendedAdapter.MyViewClick() {
            @Override
            public void showCommentPop(View view) {
//                View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_comment, null);
//                PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,getActivity().getWindowManager().getDefaultDisplay().getHeight()*4/5);
//                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                popupWindow.setOutsideTouchable(true);
//                popupWindow.setTouchable(true);
//                popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                Log.i("asdf","pinglun");
//                RecommentCommentDialogFragment dialogFragment=RecommentCommentDialogFragment.newInstance();
//                dialogFragment.show(getChildFragmentManager(),"");
            }

            @Override
            public void showSharePop() {

            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.pauseCurVideoView();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.startCurVideoView();
        } else {
            mShouldPlay = true;
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.stopCurVideoView();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            if (mShortVideoListAdapter != null) {
                mShortVideoListAdapter.startCurVideoView();
            } else {
                mShouldPlay = true;
            }
        } else {
            //相当于Fragment的onPause
            if (mShortVideoListAdapter != null) {
                mShortVideoListAdapter.pauseCurVideoView();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mShortVideoListAdapter.stopCurVideoView();
    }
    private void startCurVideoView() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mVideoList.getLayoutManager();
        int visibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();

        if (visibleItemPosition >= 0 && mCurrentPosition != visibleItemPosition) {
            mShortVideoListAdapter.stopCurVideoView();
            mCurrentPosition = visibleItemPosition;
            View holderView = mVideoList.findViewWithTag(mCurrentPosition);
            if (holderView != null) {
                RecommendedAdapter.ViewHolder viewHolder = (RecommendedAdapter.ViewHolder) mVideoList.getChildViewHolder(holderView);
                mShortVideoListAdapter.setCurViewHolder(viewHolder);
                mShortVideoListAdapter.startCurVideoView();
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void control(CallTab tab) {
        if (tab.equals(CallTab.MAIN)){
            if (mShortVideoListAdapter != null) {
                mShortVideoListAdapter.startCurVideoView();
            } else {
                mShouldPlay = true;
            }

        }else {
            if (mShortVideoListAdapter != null) {
                mShortVideoListAdapter.pauseCurVideoView();
            }
        }
    }
}
