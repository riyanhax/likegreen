package com.pywl.likegreen.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pywl.likegreen.R;
import com.pywl.likegreen.base.HomeBottomBarFragment;

/**
 * Created by theWind on 2018/8/1.
 * 加号fragment
 */

public class HomeAddFragment extends HomeBottomBarFragment implements View.OnClickListener {
    private View mShortVideo;//短视频
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add, container, false);
        initView(view);
        initData();
        return view;

    }

    private void initView(View v) {
         mShortVideo = v.findViewById(R.id.ll_add_shortvideo);//短视频
        mShortVideo.setOnClickListener(this);
    }

    private void initData() {


    }

    @Override
    public void onClick(View view) {

    }
}
