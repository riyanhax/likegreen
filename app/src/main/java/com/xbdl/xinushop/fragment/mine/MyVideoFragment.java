package com.xbdl.xinushop.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.jdsjlzx.ItemDecoration.SpacesItemDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.MyVideoActivity;
import com.xbdl.xinushop.adapter.MyVideoAdapter;
import com.xbdl.xinushop.base.BaseFragment;
import com.xbdl.xinushop.bean.VideoBean;
import com.xbdl.xinushop.utils.HttpUtils2;

import java.util.ArrayList;

/**
 * Created by theWind on 2018/8/1.
 * 我的页面视频
 */

public class MyVideoFragment extends BaseFragment {
    private ArrayList<VideoBean> list;
    private int pageSize;//一页刷新的数量
    private LRecyclerView mRecyclerView;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_video, container, false);
        initView(view);
        initData();
        return view;
    }
   private void initView(View view ){
       mRecyclerView = (LRecyclerView)view.findViewById(R.id.my_video_lrc);
   }

    private void initData(){
       // getVideoList();
        list=new ArrayList<>();
        VideoBean videoBean1 = new VideoBean();
        videoBean1.setVideoUrl("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/1BrIAtvV_1818587477_shd.mp4");
        list.add(videoBean1);
        VideoBean videoBean2 = new VideoBean();
        videoBean2.setVideoUrl("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/FmdVOTqd_1818586962_shd.mp4");
        list.add(videoBean2);
        VideoBean videoBean3 = new VideoBean();
        videoBean3.setVideoUrl("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/wq1e35cQ_1818588221_shd.mp4");
        list.add(videoBean3);
        MyVideoAdapter myVideoAdapter = new MyVideoAdapter(getContext());
        myVideoAdapter.setDataList(list);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(myVideoAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        mRecyclerView.setAdapter(lRecyclerViewAdapter);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                VideoBean videoBean = list.get(position);
                Intent intent = new Intent(getContext(), MyVideoActivity.class);
                intent.putExtra("MyVideoFragment",videoBean.getVideoUrl());
                startActivity(intent);
            }
        });
        int spacing = getResources().getDimensionPixelSize(R.dimen.dp_2);
        mRecyclerView.addItemDecoration(SpacesItemDecoration.newInstance
                (spacing,spacing, 3, R.color.enptyviewbackground));
    }


    public void getVideoList() {
        HttpUtils2.myvideolist(MyApplication.user.getLoginToken(), "0", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma",response.body());
                Log.v("nihaoma","4444444");
                dismissLoading();
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                showLoading();
                Log.v("nihaoma","111111111");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissLoading();
                Log.v("nihaoma","22222222");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dismissLoading();
                Log.v("nihaoma","33333333");
            }
        });
    }
}
