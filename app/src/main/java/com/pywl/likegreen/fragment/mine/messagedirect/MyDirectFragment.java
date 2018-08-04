package com.pywl.likegreen.fragment.mine.messagedirect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.pywl.likegreen.R;
import com.pywl.likegreen.adapter.MyDriectAdapter;

public class MyDirectFragment extends Fragment {
    private LRecyclerView mLrDirectMsg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_direct_message, container, false);

       initView(view);
       initData();
        return view;
    }



    private void initView(View view) {
//        mLrDirectMsg = (LRecyclerView)view.findViewById(R.id.lr_my_direct_msg);
//        View header = LayoutInflater.from(getActivity()).inflate(R.layout.layout_my_direct_msg_hand,
//                (ViewGroup)findViewById(android.R.id.content), false);
//        mDataAdapter = new MyDriectAdapter(this);
//        mDataAdapter.setData(dataList);
//
//        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mDataAdapter);
//        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
//        mLrDirectMsg.addHeaderView(new SampleHeader(this));

    }

    private void initData() {
        //mLrDirectMsg.addHeaderView(header);
    }
}
