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
 */
//日记
public class HomeNoteFragment extends HomeBottomBarFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_note, container, false);
        return view;

    }
}
