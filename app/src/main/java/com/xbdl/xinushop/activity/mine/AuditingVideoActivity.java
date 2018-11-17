package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.SpacesItemDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.mine.AuditVideoAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.MyVideoBean;
import com.xbdl.xinushop.utils.HttpUtils2;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class AuditingVideoActivity extends BaseActivity {
    private  int type; //普通视频1 2  广告视频  3  4
    private  LinkedList<MyVideoBean> beans,typeList;
    private LRecyclerView mRecyclerView;
    private View tv_empty;
    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditing_video);
         type = getIntent().getIntExtra("type", 0);
        initView();
        initData();
    }

    private void initView() {
            findViewById(R.id.iv_return).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        TextView tv_title = findViewById(R.id.tv_title);
        switch (type){
            case 1:
            case 3:
                tv_title.setText(getResources().getString(R.string.auditing_video));
                break;
            case 2:
            case 4:
                tv_title.setText(getResources().getString(R.string.auditing_fail_video));
                break;

        }
        mRecyclerView = findViewById(R.id.recyclerview);
         tv_empty = findViewById(R.id.tv_empty);
    }
    AuditVideoAdapter auditVideoAdapter;
    private void initData() {
         auditVideoAdapter = new AuditVideoAdapter(getActivity());
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(auditVideoAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mRecyclerView.setAdapter(lRecyclerViewAdapter);

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MyVideoBean myVideoBean = beans.get(position);
                Intent intent = new Intent(getActivity(), MyVideoActivity.class);
                intent.putExtra("MyVideoFragment", MyConstants.videoUrl+myVideoBean.getUrl());
                startActivity(intent);
            }
        });
        int spacing = getResources().getDimensionPixelSize(R.dimen.dp_2);
        mRecyclerView.addItemDecoration(SpacesItemDecoration.newInstance
                (spacing,spacing, 3, R.color.enptyviewbackground));
        mRecyclerView.setPullRefreshEnabled(false);

        switch (type){
            case 1:
            case 2:
                getList(1);
                break;
            case 3:
            case 4:
                getList(2);
                break;

        }


    }
    //传1普通视频 2广告视频
    private void getList(final int i) {
        //普通视频1 2  广告视频  3  4
        //	审核中传0 未通过传2 通过传1
        HttpUtils2.myvideolist(MyApplication.user.getLoginToken(), i, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma","视频审核"+response.body());
                Type listType = new TypeToken<LinkedList<MyVideoBean>>(){}.getType();
                Gson gson = new Gson();
                beans= gson.fromJson(response.body(), listType);
                typeList= gson.fromJson(response.body(), listType);

                for (int i=0;i<beans.size();i++){
                    MyVideoBean myVideoBean = beans.get(i);
                        switch (type){
                            case 1:
                                //普通审核中
                                if ( myVideoBean.getStatus()==0){
                                    typeList.add(myVideoBean);
                                }
                                break;
                            case 2:
                                //普通审核不通过
                                if ( myVideoBean.getStatus()==2){
                                    typeList.add(myVideoBean);
                                }
                                break;
                            case 3:
                                if ( myVideoBean.getStatus()==0){
                                    typeList.add(myVideoBean);
                                }
                                break;
                            case 4:
                                if ( myVideoBean.getStatus()==2){
                                    typeList.add(myVideoBean);
                                }
                                break;
                        }
                }
                auditVideoAdapter.setDataList(typeList);
                if (typeList==null||typeList.size()==0){
                    tv_empty.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
