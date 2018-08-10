package com.pywl.likegreen.fragment.mine.messagedirect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import com.pywl.likegreen.R;

import com.pywl.likegreen.activity.ChatActivity;
import com.pywl.likegreen.adapter.Myadapter;

import java.util.ArrayList;
/*
* 私信页
* */

public class MyDirectFragment extends Fragment {
    private LRecyclerView mDirectList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_direct_message, container, false);

       initView(view);
       initData();
        return view;
    }



    private void initView(View v) {
         mDirectList = (LRecyclerView)v.findViewById(R.id.direct_list);
    }

    private void initData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        mDirectList.setLayoutManager(new LinearLayoutManager(getActivity()));
       // MyDirectAdapter myDirectAdapter = new MyDirectAdapter(list);

        //mDirectList.setAdapter(myDirectAdapter);
        Myadapter myadapter = new Myadapter(getActivity());
        myadapter.setDataList(list);

        LRecyclerViewAdapter mLRecyclerViewAdapter = new LRecyclerViewAdapter(myadapter);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(),"点击饿了"+position,Toast.LENGTH_SHORT).show();
            }
        });
        mDirectList.setAdapter(mLRecyclerViewAdapter);
    }
}
