package com.xbdl.xinushop.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.PlantDetailActivity;
import com.xbdl.xinushop.adapter.PlantingDiaryMyGrardenAdapter;
import com.xbdl.xinushop.adapter.baseadapter.BaseAdapter;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.bean.PlantingDiaryBean;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theWind on 2018/8/1.
 * 花园
 */

public class MyGardenFragment extends Fragment {
    String token = "";

    public static final int lOAD_DADA = 1;
    public static final int UP_DADA = lOAD_DADA + 1;
    View rlEmpty;
    MaterialRefreshLayout refreshLayout;
    PlantingDiaryMyGrardenAdapter plantingDiaryMyGrardenAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_garden, container, false);
        rlEmpty = view.findViewById(R.id.rl_emptylayout);
        refreshLayout = view.findViewById(R.id.refresh);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        plantingDiaryMyGrardenAdapter = new PlantingDiaryMyGrardenAdapter(getActivity());
        recyclerView.setAdapter(plantingDiaryMyGrardenAdapter);

        String userjson = SharedPreferencesUtil.getString(getActivity(), MyConstants.User, "");

        PersonBean personBean = new Gson().fromJson(userjson, PersonBean.class);
        token = personBean.getLoginToken();
        getDataNormal(lOAD_DADA);
        setListener();
        return view;
    }

    private void setListener() {
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                //下拉刷新...
                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        page = 1;
                        getData(lOAD_DADA);
                        refreshLayout.setLoadMore(true);
                    }
                }, 500);

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //上拉加载更多...
                        /**
                         * 完成加载数据后，调用此方法，要不然刷新的效果不会消失
                         */
                        page++;
                        getData(UP_DADA);

                    }
                }, 500);

            }
        });
        //     refreshLayout.autoRefresh();
        plantingDiaryMyGrardenAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    int page = 1;

    /**
     * 加载数据
     */
    private void getData(final int upOrLoad) {
        if (upOrLoad == lOAD_DADA) { // 下拉
            page = 1;
        } else if (upOrLoad == UP_DADA) { // 上拉
            page += 1;
        }
        HttpUtils.findPlantList(token, page + "", 3 + "", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    int code = jsonObject.getInt("code");
                    String object = jsonObject.getString("object");

                    if (code == 100) {
                        List<PlantingDiaryBean> plantingDiaryBeans = getPlantingDiaryBeans(object);
                        if (plantingDiaryBeans != null && plantingDiaryBeans.size() > 0) {
                            //   rlEmpty.setVisibility(View.GONE);
                            if (upOrLoad == lOAD_DADA) { // 下拉
                                plantingDiaryMyGrardenAdapter.refreshData(plantingDiaryBeans);
                            } else if (upOrLoad == UP_DADA) { // 上拉
                                plantingDiaryMyGrardenAdapter.loadMoreData(plantingDiaryBeans);
                            }
                        } else {
                            //  rlEmpty.setVisibility(View.VISIBLE);

                        }
                    }

                } catch (JSONException e) {

                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                refreshLayout.finishRefresh();
                refreshLayout.finishRefreshLoadMore();
            }
        });

    }

    /**
     * 加载数据
     */
    private void getDataNormal(final int upOrLoad) {
        if (upOrLoad == lOAD_DADA) { // 下拉
            page = 1;
        } else if (upOrLoad == UP_DADA) { // 上拉
            page += 1;
        }
        HttpUtils.findPlantList(token, page + "", 3 + "", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    int code = jsonObject.getInt("code");
                    String object = jsonObject.getString("object");

                    if (code == 100) {
                        List<PlantingDiaryBean> plantingDiaryBeans = getPlantingDiaryBeans(object);
                        if (plantingDiaryBeans != null && plantingDiaryBeans.size() > 0) {
                            rlEmpty.setVisibility(View.GONE);
                            if (upOrLoad == lOAD_DADA) { // 下拉
                                plantingDiaryMyGrardenAdapter.refreshData(plantingDiaryBeans);
                            } else if (upOrLoad == UP_DADA) { // 上拉
                                plantingDiaryMyGrardenAdapter.loadMoreData(plantingDiaryBeans);
                            }
                        } else {
                            rlEmpty.setVisibility(View.VISIBLE);

                        }
                    }

                } catch (JSONException e) {

                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                refreshLayout.finishRefresh();
                refreshLayout.finishRefreshLoadMore();
            }
        });

    }

    private List<PlantingDiaryBean> getPlantingDiaryBeans(String object) {
        if (Judge.getBoolean_isNull(object)) {
            return null;
        }
        try {
            JSONArray array = new JSONArray(object);
            List<PlantingDiaryBean> datas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                PlantingDiaryBean plantingDiaryBean = new Gson().fromJson(array.getString(i), PlantingDiaryBean.class);
                datas.add(plantingDiaryBean);
            }
            return datas;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OkGo.getInstance().cancelTag("findPlantList");
    }
}
