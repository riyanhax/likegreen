package com.xbdl.xinushop.activity.note;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.note.NoteDetailAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.NoteDetailBean;
import com.xbdl.xinushop.utils.HttpUtils2;

import java.util.ArrayList;
import java.util.List;

/*
* 日记图片点击
* */
public class NoteDetailActivity extends BaseActivity implements View.OnClickListener {
    private LRecyclerView recyclerView;
    private  int diaryRootId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        diaryRootId = getIntent().getIntExtra("diaryRootId", 0);
        initView();
        initData();
    }

    private void initView() {
        findViewById(R.id.iv_return).setOnClickListener(this);
        recyclerView= findViewById(R.id.recyclerView);
    }

    private void initData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("dd");
        strings.add("dd");
        strings.add("dd");
        View head = LayoutInflater.from(getActivity()).inflate(R.layout.note_head_recyclerview, recyclerView, false);
       // View headMy = LayoutInflater.from(getActivity()).inflate(R.layout.note_head_my, recyclerView, false);
        NoteDetailAdapter noteDetailAdapter = new NoteDetailAdapter(getActivity());
        noteDetailAdapter.setDataList(strings);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(noteDetailAdapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        recyclerView.setPullRefreshEnabled(false);
        lRecyclerViewAdapter.addHeaderView(head);
        HttpUtils2.appGetCurrentGroupDiaries(MyApplication.user.getLoginToken(), diaryRootId, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma", "onSuccess"+response.body());
                NoteDetailBean noteDetailBean = new Gson().fromJson(response.body(), NoteDetailBean.class);
                if (noteDetailBean.getCode()==100){
                    List<NoteDetailBean.ExtendBean.DiaryBean> diary = noteDetailBean.getExtend().getDiary();
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
                Log.v("nihaoma", "onError"+response.body());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dismissLoading();
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_return:
                finish();
                break;
        }
    }

    @Override
    protected Activity getActivity() {
        return this;
    }
}
