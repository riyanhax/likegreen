package com.pywl.likegreen.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.jdsjlzx.ItemDecoration.SpacesItemDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.pywl.likegreen.R;
import com.pywl.likegreen.activity.mine.MyVideoActivity;
import com.pywl.likegreen.adapter.MyVideoAdapter;
import com.pywl.likegreen.bean.VideoBean;

import java.util.ArrayList;

/**
 * Created by theWind on 2018/8/1.
 * 我的页面视频
 */

public class MyVideoFragment extends Fragment {
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
        list=new ArrayList<>();
        VideoBean videoBean1 = new VideoBean();
        videoBean1.setVideoUrl("http://jdvod6w938ryc.vod.126.net/jdvod6w938ryc/ea89570d-7298-47f9-8ea9-030fe1b5eeef.mp4");
        list.add(videoBean1);
        VideoBean videoBean2 = new VideoBean();
        videoBean2.setVideoUrl("http://jdvod6w938ryc.vod.126.net/jdvod6w938ryc/8cd1ad40-0e96-4545-9ad5-a6c8bd589f05.mp4");
        list.add(videoBean2);
        VideoBean videoBean3 = new VideoBean();
        videoBean3.setVideoUrl("http://jdvod6w938ryc.vod.126.net/jdvod6w938ryc/5e4083d2-d399-460c-9e76-c9a83ada3791.mp4");
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


}
