package com.xbdl.xinushop.activity.note;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.note.NoteDetailAdapter;
import com.xbdl.xinushop.base.BaseActivity;

import java.util.ArrayList;

/*
* 日记图片点击
* */
public class NoteDetailActivity extends BaseActivity implements View.OnClickListener {
    private LRecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
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
        NoteDetailAdapter noteDetailAdapter = new NoteDetailAdapter(getActivity());
        noteDetailAdapter.setDataList(strings);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(noteDetailAdapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        recyclerView.setPullRefreshEnabled(false);
        lRecyclerViewAdapter.addHeaderView(head);
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
