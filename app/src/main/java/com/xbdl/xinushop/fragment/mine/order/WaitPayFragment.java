package com.xbdl.xinushop.fragment.mine.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;

/**
 * 待付款
 */

public class WaitPayFragment extends Fragment {

    private View emptyView;
    private LRecyclerView recyclerView;
    private ImageView icon;
    private int page=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_order, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View v) {
        emptyView = v.findViewById(R.id.ll_empty_view);
        icon= v.findViewById(R.id.tv_icon);
        recyclerView = v.findViewById(R.id.recyclerView);
    }

    private void initData() {
        icon.setImageResource(R.drawable.daifukuan);
        String token = MyApplication.user.getLoginToken();
        int userId = MyApplication.user.getUserId();

        }
    }
