package com.xbdl.xinushop.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.RecommendedAdapter;
import com.xbdl.xinushop.base.BaseFragment;
import com.xbdl.xinushop.evnetBus.CallTab;
import com.xbdl.xinushop.bean.TheNewVideoBean;
import com.xbdl.xinushop.utils.HttpUtils2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.LinkedList;

/**
 * Created by theWind on 2018/8/1.
 */
//关注
public class TheNewFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended, container, false);
       EventBus.getDefault().register(this);
       // initView(view);
       // initDate();
        return view;
    }

  private void initDate() {
      HttpUtils2.selectNewest(new StringCallback() {
          @Override
          public void onSuccess(Response<String> response) {
              Type listType = new TypeToken<LinkedList<TheNewVideoBean>>(){}.getType();
              Gson gson = new Gson();
              LinkedList<TheNewVideoBean> beans= gson.fromJson(response.body(), listType);
              mShortVideoListAdapter.setDataList(beans);
              dismissLoading();
          }

          @Override
          public void onStart(Request<String, ? extends Request> request) {
              super.onStart(request);
              showLoading();
          }

          @Override
          public void onFinish() {
              super.onFinish();
              dismissLoading();
          }

          @Override
          public void onError(Response<String> response) {
              super.onError(response);
              dismissLoading();
          }
      });
     /*   mItemList.add("http://m9pic.mm999.com/video/201804/20180427092550022.mp4");
        mItemList.add("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/FmdVOTqd_1818586962_shd.mp4");
        mItemList.add("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/wq1e35cQ_1818588221_shd.mp4");
        mItemList.add("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/7eSdPRKt_1818589543_shd.mp4");*/
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
    private int mCurrentPosition =  -1;
    private RecyclerView mVideoList;
    private void initView(View v) {

        mVideoList = v.findViewById(R.id.video_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mVideoList.setLayoutManager(layoutManager);
        mVideoList.setHasFixedSize(true);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mVideoList);

        mShortVideoListAdapter = new RecommendedAdapter(getActivity());
        mVideoList.setAdapter(mShortVideoListAdapter);
        mVideoList.addOnScrollListener(mOnScrollListener);

       /* if (mShouldPlay) {
            mVideoList.post(new Runnable() {
                @Override
                public void run() {
                    startCurVideoView();
                    mShouldPlay = false;
                }
            });
        }*/
        mShortVideoListAdapter.setMyViewClick(new RecommendedAdapter.MyViewClick() {
            @Override
            public void showCommentPop(View view, TheNewVideoBean bean) {
//                View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_comment, null);
//                PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,getActivity().getWindowManager().getDefaultDisplay().getHeight()*4/5);
//                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                popupWindow.setOutsideTouchable(true);
//                popupWindow.setTouchable(true);
//                popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                Log.i("asdf","pinglun");
              /*  RecommentCommentDialogFragment dialogFragment=RecommentCommentDialogFragment.newInstance();
                dialogFragment.show(getChildFragmentManager(),"");*/
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
                //mShortVideoListAdapter.startCurVideoView();
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
        if (mShortVideoListAdapter!=null){
            mShortVideoListAdapter.stopCurVideoView();
        }

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
               // mShortVideoListAdapter.startCurVideoView();
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
