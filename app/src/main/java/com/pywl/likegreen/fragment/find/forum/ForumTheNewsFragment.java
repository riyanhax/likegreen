package com.pywl.likegreen.fragment.find.forum;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.pywl.likegreen.R;
import com.pywl.likegreen.adapter.ForumTheNewsAdapter;

import java.util.ArrayList;


/*
* 话题
* */
public class ForumTheNewsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_the_news, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("1");
        strings.add("1");
        strings.add("1");
        LRecyclerView recyclerView=(LRecyclerView)view.findViewById(R.id.the_new_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        ForumTheNewsAdapter forumTheNewsAdapter = new ForumTheNewsAdapter(getContext());
        forumTheNewsAdapter.setDataList(strings);
        recyclerView.setAdapter(new LRecyclerViewAdapter(forumTheNewsAdapter));
        DividerDecoration divider = new DividerDecoration.Builder(getActivity())
                .setHeight(R.dimen.dp_5)
                .setColorResource(R.color.enptyviewbackground)
                .build();
        recyclerView.addItemDecoration(divider);
    }






}
