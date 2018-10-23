package com.xbdl.xinushop.fragment.mine.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.mine.order.AllOrderAdapter;
import com.xbdl.xinushop.base.BaseFragment;
import com.xbdl.xinushop.utils.HttpUtils2;

import java.util.ArrayList;

/**
 * 全部订单
 */

public class AllorderFragment extends BaseFragment {

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
        icon.setImageResource(R.drawable.daifahuo);
        emptyView.setVisibility(View.GONE);
        String token = MyApplication.user.getLoginToken();
        int userId = MyApplication.user.getUserId();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("ss");
        strings.add("ss");
        strings.add("ss");
        AllOrderAdapter allOrderAdapter = new AllOrderAdapter(getActivity());
        allOrderAdapter.setDataList(strings);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(allOrderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(lRecyclerViewAdapter);
        HttpUtils2.getOrdersByUserIdApiAll(token, page, userId, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma","全部订单 "+response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.v("nihaoma","全部订单 onError "+response.body());
            }
        });
        }
    }
