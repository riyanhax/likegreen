package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.DiscountCouponAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.DiscountCouponBean;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/*
* 优惠券
* */
public class DiscountCouponActivity extends BaseActivity {
    public static final int lOAD_DADA = 1;
    public static final int UP_DADA = lOAD_DADA + 1;

    private int pn=1;
    LRecyclerView recyclerView;
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


        getCoupinList();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));



    }

    private void getCoupinList() {
        HttpUtils.getUserCouponsListApi(MyApplication.user.getLoginToken(), pn, MyApplication.user.getUserId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                DiscountCouponBean bean = gson.fromJson(response.body(), DiscountCouponBean.class);
                if (bean.getCode()==100){
                    DiscountCouponBean.PageInfoBean pageInfo = bean.getPageInfo();
                    List<DiscountCouponBean.PageInfoBean.ListBean> list = pageInfo.getList();


                }else {
                    ToastUtil.shortToast(getActivity(),"优惠券查询失败");
                }
                dismissLoading();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dismissLoading();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissLoading();
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                showLoading();
            }
        });
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
