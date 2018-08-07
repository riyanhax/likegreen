package com.pywl.likegreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pywl.likegreen.R;

/**
 * Created by theWind on 2018/8/1.
 */
//推荐
public class RecommendedFragment extends Fragment implements View.OnClickListener {
    private View mIvRecommendShare;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended, container, false);
        initView(view);
        initDate();
        return view;
    }

    private void initView(View v) {
        //分享
        mIvRecommendShare = v.findViewById(R.id.iv_recommend_share);
        mIvRecommendShare.setOnClickListener(this);
    }

    private void initDate() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_recommend_share:
                showPopuWindow();
                break;
        }
    }

    private void showPopuWindow() {

    }
}
