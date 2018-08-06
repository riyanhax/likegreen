package com.pywl.likegreen.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pywl.likegreen.R;

/**
 * Created by theWind on 2018/8/1.
 * 喜帖
 */

public class MyWeddingCardFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_weddingcard, container, false);

        return view;
    }
}
