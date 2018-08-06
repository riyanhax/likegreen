package com.pywl.likegreen.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.pywl.likegreen.R;

/**
 * Created by theWind on 2018/8/1.
 * 我的页面视频
 */

public class MyVideoFragment extends Fragment {

    private int pageSize;//一页刷新的数量
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_video, container, false);
        initView(view);
      //  initData();
        return view;
    }
   private void initView(View view ){
      // mRecyclerView = (LRecyclerView)view.findViewById(R.id.my_video_lrc);
   }
    private void initData(){
        //setRecyclerView();
    }
    private void setRecyclerView(){

    }

}
