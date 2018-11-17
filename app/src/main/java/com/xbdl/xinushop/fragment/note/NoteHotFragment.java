package com.xbdl.xinushop.fragment.note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.note.NoteListAdapter;
import com.xbdl.xinushop.base.BaseFragment;
import com.xbdl.xinushop.bean.NoteHotBean;
import com.xbdl.xinushop.bean.NoteListBean;
import com.xbdl.xinushop.evnetBus.PlantEvnet;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.Judge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theWind on 2018/8/1.
 * 日记热门
 */


public class NoteHotFragment extends BaseFragment {
    ProgressBar progressBar;
    NoteListAdapter noteListAdapter = null;
    private int page=1;
    private LRecyclerView recyclerView;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_focus, container, false);
        EventBus.getDefault().register(this);
        progressBar = view.findViewById(R.id.prossbar);
        recyclerView = view.findViewById(R.id.lr_note_hot);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        noteListAdapter = new NoteListAdapter(getActivity());
        lRecyclerViewAdapter= new LRecyclerViewAdapter(noteListAdapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                noteListAdapter.clear();
                lRecyclerViewAdapter.notifyDataSetChanged();//必须调用此方法
                page=1;
                getData(page);

            }
        });
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getData(page);
            }
        });
        initData();
        return view;
    }

    private void initData() {
        getData(page);
    }
    NoteHotBean.ExtendBean.DiaryRootsBean diaryRoots ;
    private void getData(int pn) {
        HttpUtils.noteHot(MyApplication.user.getLoginToken(),pn,MyApplication.user.getUserId(),new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma",response.body());
                dismissLoading();
               NoteHotBean noteHotBean = new Gson().fromJson(response.body(), NoteHotBean.class);
                if (noteHotBean.getCode()==100){
                    diaryRoots = noteHotBean.getExtend().getDiaryRoots();
                    List<NoteHotBean.ExtendBean.DiaryRootsBean.ListBean> list = diaryRoots.getList();

                    page++;
                    if (diaryRoots.isHasNextPage()){
                        recyclerView.setLoadMoreEnabled(true);
                    }else {
                        recyclerView.setLoadMoreEnabled(false);
                    }
                    if (page==1){
                        noteListAdapter.setDataList(list);
                    }else {
                        noteListAdapter.addAll(list);
                    }
                    recyclerView.refreshComplete(10);
                    lRecyclerViewAdapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                showLoading();
            }
            @Override
            public void onFinish() {
                super.onFinish();
                dismissLoading();
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
        OkGo.getInstance().cancelTag("noteHot");
        EventBus.getDefault().unregister(this);
    }
    //定义处理接收的方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(PlantEvnet userEvent){
        switch (userEvent){
            case Refresh:
                Log.v("nihaoma","日记刷新");
                recyclerView.forceToRefresh();
                        noteListAdapter.clear();
                        lRecyclerViewAdapter.notifyDataSetChanged();//必须调用此方法
                        page=1;
                        getData(page);
                break;
                }


        }

    }
