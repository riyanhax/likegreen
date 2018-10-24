package com.xbdl.xinushop.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.jdsjlzx.ItemDecoration.SpacesItemDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.MyVideoActivity;
import com.xbdl.xinushop.adapter.MyVideoAdapter;
import com.xbdl.xinushop.base.BaseFragment;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.MyVideoBean;
import com.xbdl.xinushop.bean.VideoBean;
import com.xbdl.xinushop.utils.HttpUtils2;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by theWind on 2018/8/1.
 * 我的页面视频
 */

public class MyVideoFragment extends BaseFragment {
    private  LinkedList<MyVideoBean> beans;
    private int pageSize;//一页刷新的数量
    private LRecyclerView mRecyclerView;
    private View text;
    private int userId,type;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_video, container, false);
        Bundle bundle = getArguments();
        userId = bundle.getInt("userId");
        type = bundle.getInt("type");
        initView(view);
        initData();
        return view;
    }
   private void initView(View view ){
       mRecyclerView = (LRecyclerView)view.findViewById(R.id.my_video_lrc);
       text = view.findViewById(R.id.tv_video_text);
   }
    private MyVideoAdapter myVideoAdapter;
    private void initData(){
            getVideoList(userId);

         myVideoAdapter = new MyVideoAdapter(getContext());

        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(myVideoAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        mRecyclerView.setAdapter(lRecyclerViewAdapter);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MyVideoBean myVideoBean = beans.get(position);
                Intent intent = new Intent(getContext(), MyVideoActivity.class);
                intent.putExtra("MyVideoFragment", MyConstants.videoUrl+myVideoBean.getUrl());
                startActivity(intent);
            }
        });
        int spacing = getResources().getDimensionPixelSize(R.dimen.dp_2);
        mRecyclerView.addItemDecoration(SpacesItemDecoration.newInstance
                (spacing,spacing, 3, R.color.enptyviewbackground));
        mRecyclerView.setPullRefreshEnabled(false);

    }


    public void getVideoList(int id) {
        HttpUtils2.myvideoAlllist(MyApplication.user.getLoginToken(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma",response.body());
                Type listType = new TypeToken<LinkedList<MyVideoBean>>(){}.getType();
                Gson gson = new Gson();
                beans= gson.fromJson(response.body(), listType);
                myVideoAdapter.setDataList(beans);
                if (beans.size()==0) {
                    text.setVisibility(View.VISIBLE);
                }else {
                    text.setVisibility(View.GONE);
                }
                dismissLoading();
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                showLoading();

            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissLoading();

            }

            @Override
            public void onFinish() {
                super.onFinish();
                dismissLoading();

            }
        });
    }
}
