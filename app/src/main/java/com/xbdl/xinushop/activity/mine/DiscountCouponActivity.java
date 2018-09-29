package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;

public class DiscountCouponActivity extends BaseActivity {
    MaterialRefreshLayout refreshLayout;
    RecyclerView recyclerView;
String userid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_discount_coupon);

        String userjson = SharedPreferencesUtil.getString(getActivity(), MyConstants.User, "");

        PersonBean personBean = new Gson().fromJson(userjson, PersonBean.class);
        userid = personBean.getUserId()+"";

        refreshLayout = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        getDeaultData();
    }
int page=1;

    private void getDeaultData() {
        HttpUtils.findMydiscountCoupon(page + "", userid, 0 + "", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.i("asdf",""+response.body());
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
