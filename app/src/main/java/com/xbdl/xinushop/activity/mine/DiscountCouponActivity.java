package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.DiscountCouponAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.DiscountCouponBean;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiscountCouponActivity extends BaseActivity {
    public static final int lOAD_DADA = 1;
    public static final int UP_DADA = lOAD_DADA + 1;

    MaterialRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    String userid = "";
    String token = "";
    DiscountCouponAdapter discountCouponAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_discount_coupon);

        String userjson = SharedPreferencesUtil.getString(getActivity(), MyConstants.User, "");
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.iv_adddiscount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adddiscountCoupon = new Intent(getActivity(), AddDiscountCouponActivity.class);
                startActivity(adddiscountCoupon);
            }
        });

        PersonBean personBean = new Gson().fromJson(userjson, PersonBean.class);
        userid = personBean.getUserId() + "";
        token = personBean.getLoginToken();
        refreshLayout = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        discountCouponAdapter = new DiscountCouponAdapter(getActivity());
        recyclerView.setAdapter(discountCouponAdapter);
        getDeaultData(lOAD_DADA);
        setLinstener();
    }

    private void setLinstener() {
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
    }

    int page = 1;

    private void getData(final int upOrLoad) {
//        HttpUtils.findMydiscountCoupon(token, page + "", userid, new StringCallback() {
//            @Override
//            public void onSuccess(Response<String> response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response.body());
//                    int code = jsonObject.getInt("code");
//                    String pageInfo = jsonObject.getString("pageInfo");
//                    if (code != 1) {
////                        findViewById(R.id.rl_emptylayout).setVisibility(View.VISIBLE);
////                        refreshLayout.setVisibility(View.GONE);
//                    } else {
//                        JSONObject pageInfojson = new JSONObject(pageInfo);
//                        String list = pageInfojson.getString("list");
//                        List<DiscountCouponBean> discountCouponBeans = getDisCountCouponList(list);
//                        if (discountCouponBeans != null && discountCouponBeans.size() > 0) {
//                            if (upOrLoad == lOAD_DADA) { // 下拉
//                                discountCouponAdapter.refreshData(discountCouponBeans);
//
//                            } else if (upOrLoad == UP_DADA) { // 上拉
//                                discountCouponAdapter.loadMoreData(discountCouponBeans);
//
//                            }
//                        } else {
////                            findViewById(R.id.rl_emptylayout).setVisibility(View.VISIBLE);
////                            refreshLayout.setVisibility(View.GONE);
//                        }
//                    }
//
//                } catch (JSONException e) {
////                    findViewById(R.id.rl_emptylayout).setVisibility(View.VISIBLE);
////                    refreshLayout.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onError(Response<String> response) {
//                super.onError(response);
//                findViewById(R.id.rl_emptylayout).setVisibility(View.VISIBLE);
//                refreshLayout.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                refreshLayout.finishRefresh();
//                refreshLayout.finishRefreshLoadMore();
//            }
//        });
    }

    private void getDeaultData(final int upOrLoad) {
//        HttpUtils.findMydiscountCoupon(token, page + "", userid, new StringCallback() {
//            @Override
//            public void onSuccess(Response<String> response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response.body());
//                    int code = jsonObject.getInt("code");
//                    String pageInfo = jsonObject.getString("pageInfo");
//                    if (code != 1) {
//                        findViewById(R.id.rl_emptylayout).setVisibility(View.VISIBLE);
//                        refreshLayout.setVisibility(View.GONE);
//                    } else {
//                        JSONObject pageInfojson = new JSONObject(pageInfo);
//                        String list = pageInfojson.getString("list");
//                        List<DiscountCouponBean> discountCouponBeans = getDisCountCouponList(list);
//                        if (discountCouponBeans != null && discountCouponBeans.size() > 0) {
//                            if (upOrLoad == lOAD_DADA) { // 下拉
//                                discountCouponAdapter.refreshData(discountCouponBeans);
//
//                            } else if (upOrLoad == UP_DADA) { // 上拉
//                                discountCouponAdapter.loadMoreData(discountCouponBeans);
//
//                            }
//                        } else {
//                            findViewById(R.id.rl_emptylayout).setVisibility(View.VISIBLE);
//                            refreshLayout.setVisibility(View.GONE);
//                        }
//                    }
//
//                } catch (JSONException e) {
//                    findViewById(R.id.rl_emptylayout).setVisibility(View.VISIBLE);
//                    refreshLayout.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onError(Response<String> response) {
//                super.onError(response);
//                findViewById(R.id.rl_emptylayout).setVisibility(View.VISIBLE);
//                refreshLayout.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                refreshLayout.finishRefresh();
//                refreshLayout.finishRefreshLoadMore();
//            }
//        });
    }

    private List<DiscountCouponBean> getDisCountCouponList(String list) {
        if (Judge.getBoolean_isNull(list)) {
            return null;
        }
        try {
            JSONArray array = new JSONArray(list);
            List<DiscountCouponBean> datas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                DiscountCouponBean discountCouponBean = new Gson().fromJson(array.getString(i), DiscountCouponBean.class);
                datas.add(discountCouponBean);
            }
            return datas;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    protected Activity getActivity() {
        return DiscountCouponActivity.this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag("findMydiscountCoupon");
    }
}
