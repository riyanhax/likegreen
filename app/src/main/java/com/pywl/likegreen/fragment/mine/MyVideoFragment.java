package com.pywl.likegreen.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.ItemDecoration.GridItemDecoration;
import com.github.jdsjlzx.ItemDecoration.SpacesItemDecoration;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.pywl.likegreen.R;
import com.pywl.likegreen.adapter.MyVideoAdapter;

/**
 * Created by theWind on 2018/8/1.
 * 我的页面视频
 */

public class MyVideoFragment extends Fragment {
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
        //setRecyclerView();
    }
    private void setRecyclerView(){

        mDataAdapter = new MyVideoAdapter(this);
        mDataAdapter.setData(dataList);

        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mDataAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        //remove a HeaderView
        mLRecyclerViewAdapter.removeHeaderView();

//remove a FooterView下拉刷新
        mLRecyclerViewAdapter.removeFooterView();
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        //下拉刷新后使用更新
        mRecyclerView.refreshComplete(pageSize);
        mLRecyclerViewAdapter.notifyDataSetChanged();
        int spacing = getResources().getDimensionPixelSize(R.dimen.dp_4);
        mRecyclerView.addItemDecoration(SpacesItemDecoration.newInstance(spacing, spacing, manager.getSpanCount(), Color.GRAY));

//根据需要选择使用GridItemDecoration还是SpacesItemDecoration
        GridItemDecoration divider = new GridItemDecoration.Builder(this)
                .setHorizontal(R.dimen.default_divider_padding)
                .setVertical(R.dimen.default_divider_padding)
                .setColorResource(R.color.split)
                .build();
//mRecyclerView.addItemDecoration(divider);
    }

}
