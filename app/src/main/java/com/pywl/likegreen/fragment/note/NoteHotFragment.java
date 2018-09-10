package com.pywl.likegreen.fragment.note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.pywl.likegreen.R;
import com.pywl.likegreen.adapter.NoteHotAdapter;

import java.util.ArrayList;

/**
 * Created by theWind on 2018/8/1.
 * 日记热门
 */


public class NoteHotFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_focus, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("1");
        strings.add("1");
        strings.add("1");
        LRecyclerView recyclerView =(LRecyclerView) view.findViewById(R.id.lr_note_hot);
        NoteHotAdapter noteHotAdapter = new NoteHotAdapter(getContext());
        noteHotAdapter.setDataList(strings);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(noteHotAdapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        DividerDecoration divider = new DividerDecoration.Builder(getActivity())
                .setHeight(R.dimen.dp_5)
                .setColorResource(R.color.enptyviewbackground)
                .build();
        recyclerView.addItemDecoration(divider);
    }
}
