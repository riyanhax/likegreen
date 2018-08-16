package com.pywl.likegreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pywl.likegreen.R;

/**
 * Created by theWind on 2018/8/16.
 */

public class PreviewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_preview, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {

    }

    private void initData() {

    }
}