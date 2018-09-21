package com.xbdl.xinushop.fragment.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbdl.xinushop.R;

/**
 * Created by theWind on 2018/8/1.
 * 商城
 */


public class ShoppingMallFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_shoppingmall, container, false);
        return view;
    }
}
