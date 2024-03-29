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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.FocusVideoAdapter;
import com.xbdl.xinushop.base.BaseFragment;
import com.xbdl.xinushop.evnetBus.CallTab;
import com.xbdl.xinushop.bean.FocusVideoBean;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.TheNewVideoBean;
import com.xbdl.xinushop.dialogfragment.RecommentDialogFragment;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by theWind on 2018/8/1.
 */
//关注
public class FocuFragment extends BaseFragment {
    private int page=1;
    private String TAG = "ShortVideoActivity";
    private boolean isWifi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended, container, false);
        EventBus.getDefault().register(this);
        isWifi = SharedPreferencesUtil.getBoolean(getActivity(), MyConstants.WIFI_AND_MOBILE, true);
        initView(view);
       initDate();
        return view;
    }
   
    private void initDate() {
        HttpUtils2.appGetUserFocusedVideos(MyApplication.user.getLoginToken(),MyApplication.user.getUserId(),page,new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma","我关注的视频信息"+response.body());
               // Type listType = new TypeToken<LinkedList<TheNewVideoBean>>(){}.getType();
                Gson gson = new Gson();
                FocusVideoBean focusVideoBean = gson.fromJson(response.body(), FocusVideoBean.class);
                if (focusVideoBean.getCode()==100){
                    FocusVideoBean.ExtendBean.PageBean page = focusVideoBean.getExtend().getPage();
                    List<FocusVideoBean.ExtendBean.PageBean.ListBean> beans = page.getList();
                    if (beans.size()==0){
                        tv_tip.setVisibility(View.VISIBLE);
                        tv_tip.setText("你还没有关注别人");
                    }else {
                        tv_tip.setVisibility(View.GONE);
                    }

                    initRecyclerView(beans);
                }
                

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
                Log.v("nihaoma","视频信息onError"+response.body());
            }
        });
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
    private FocusVideoAdapter mShortVideoListAdapter;
    private ArrayList<String> mItemList;
    private int mCurrentPosition =  -1;
    private RecyclerView mVideoList;
    private TextView tv_tip;
    private void initView(View v) {
        tv_tip= v.findViewById(R.id.tv_tip);
        mVideoList = v.findViewById(R.id.video_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mVideoList.setLayoutManager(layoutManager);
        mVideoList.setHasFixedSize(true);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mVideoList);

        mShortVideoListAdapter = new FocusVideoAdapter(getActivity());
    }

    private void initRecyclerView(List<FocusVideoBean.ExtendBean.PageBean.ListBean> beans) {

        mShortVideoListAdapter.setDataList(beans);
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
        mShortVideoListAdapter.setMyViewClick(new FocusVideoAdapter.MyViewClick() {
            @Override
            public void showCommentPop(View view, FocusVideoBean.ExtendBean.PageBean.ListBean bean) {
                //弹出评论
                RecommentDialogFragment fragment = RecommentDialogFragment.newInstance(1, bean.getVideo_id(),bean.getUserId());
                fragment.show(getFragmentManager(),fragment.getClass().getName());

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
    Boolean star=true;
    @Override
    public void onResume() {
        super.onResume();
        if (star){
            //第一次进入
            initDate();
            star=false;
        }else {
            if (mShortVideoListAdapter != null) {
               // mShortVideoListAdapter.startCurVideoView();
            } else {
                mShouldPlay = true;
            }
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
            if (star){
                //第一次进入
                initDate();
                star=false;
            }else {
                if (mShortVideoListAdapter != null) {
                    // mShortVideoListAdapter.startCurVideoView();
                } else {
                    mShouldPlay = true;
                }
            }


            if (mShortVideoListAdapter != null) {

                // mShortVideoListAdapter.startCurVideoView();
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
                FocusVideoAdapter.ViewHolder viewHolder = (FocusVideoAdapter.ViewHolder) mVideoList.getChildViewHolder(holderView);
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
