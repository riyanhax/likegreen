package com.xbdl.xinushop.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.AuditingVideoActivity;
import com.xbdl.xinushop.base.BaseFragment;
import com.xbdl.xinushop.bean.MyVideoBean;
import com.xbdl.xinushop.utils.HttpUtils2;

import java.lang.reflect.Type;
import java.util.LinkedList;

/**
 * Created by theWind on 2018/8/1.
 * 普通视频
 */

public class OrdinaryFragment extends BaseFragment implements View.OnClickListener{
    private View mRlinAudit,mRlunAudit;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audit, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View v) {
        mRlinAudit = v.findViewById(R.id.rl_in_audit);
        mRlunAudit = v.findViewById(R.id.rl_unaudit);
        mRlinAudit.setOnClickListener(this);
        mRlunAudit.setOnClickListener(this);
    }
    private  LinkedList<MyVideoBean> beans;
    private void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), AuditingVideoActivity.class);
        switch (view.getId()){
            case R.id.rl_in_audit:
                intent.putExtra("type",1);
                break;
            case R.id.rl_unaudit:
                intent.putExtra("type",2);
                break;
        }
        startActivity(intent);
    }
}