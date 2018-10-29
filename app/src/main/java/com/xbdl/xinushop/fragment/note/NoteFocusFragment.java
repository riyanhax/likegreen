package com.xbdl.xinushop.fragment.note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.note.NoteListAdapter;
import com.xbdl.xinushop.bean.NoteListBean;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.Judge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theWind on 2018/8/1.
 * 日记关注
 */


public class NoteFocusFragment extends Fragment {
    ProgressBar progressBar;
    NoteListAdapter noteListAdapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_focus, container, false);
      /*  progressBar = view.findViewById(R.id.prossbar);
        LRecyclerView recyclerView = view.findViewById(R.id.lr_note_hot);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        noteListAdapter = new NoteListAdapter(getActivity());
        recyclerView.setAdapter(noteListAdapter);
        initData();*/
        return view;
    }

    private void initData() {
        HttpUtils.notehotandattention(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());

                    String data = jsonObject.getString("data");
                    List<NoteListBean> noteListBeans = getNoteList(data);
                    if (noteListBeans != null && noteListBeans.size() > 0) {
                        /*noteListAdapter.addData(noteListBeans);*/
                    }
                } catch (JSONException e) {
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                progressBar.setVisibility(View.GONE);
            }
        });


    }

    private List<NoteListBean> getNoteList(String data) {
        if (Judge.getBoolean_isNull(data)) {
            return null;
        }
        try {
            JSONArray array = new JSONArray(data);
            List<NoteListBean> datas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                NoteListBean noteListBean = new Gson().fromJson(array.getString(i), NoteListBean.class);
                datas.add(noteListBean);
            }
            return datas;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OkGo.getInstance().cancelTag("notehotandattention");
    }
}
