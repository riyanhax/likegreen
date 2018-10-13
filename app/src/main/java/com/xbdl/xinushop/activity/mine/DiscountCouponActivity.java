package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.mine.CouponAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.DiscountCouponBean;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.utils.ToastUtil;

import java.util.List;
/*
* 优惠券
* */
public class DiscountCouponActivity extends BaseActivity {
    public static final int lOAD_DADA = 1;
    public static final int UP_DADA = lOAD_DADA + 1;

    private int pn=1;
    LRecyclerView recyclerView;
    private View emptylayout;
    private CouponAdapter couponAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
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
        emptylayout = findViewById(R.id.rl_emptylayout);
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
        couponAdapter = new CouponAdapter(getActivity());
        lRecyclerViewAdapter= new LRecyclerViewAdapter(couponAdapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        //分割线
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.dp_5)
                .setPadding(R.dimen.dp_5)
                .setColorResource(R.color.enptyviewbackground)
                .build();
        recyclerView.addItemDecoration(divider);
        //滑动加载更多
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getCoupinList();
            }
        });
        TextView textView = new TextView(getActivity());
        textView.setText("查看失效优惠券 >");
//                        View footview = LayoutInflater.from(context).inflate(R.layout.coupon_foodview, null);
        lRecyclerViewAdapter.addFooterView(textView);
    }

    private void getCoupinList() {
        HttpUtils.getUserCouponsListApi(MyApplication.user.getLoginToken(), pn, MyApplication.user.getUserId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                DiscountCouponBean bean = gson.fromJson(response.body(), DiscountCouponBean.class);
                if (bean.getCode()==1){
                    DiscountCouponBean.PageInfoBean pageInfo = bean.getPageInfo();
                    List<DiscountCouponBean.PageInfoBean.ListBean> list = pageInfo.getList();
                    if (list.size()==0){
                        //没有数据
                        emptylayout.setVisibility(View.VISIBLE);
                    }else {
                        //有数据
                        emptylayout.setVisibility(View.GONE);
                        if (pageInfo.isHasNextPage()){
                            recyclerView.setLoadMoreEnabled(true);
                        }else {
                            recyclerView.setLoadMoreEnabled(false);

                        }
                        if (pn==1){
                            couponAdapter.setDataList(list);
                        }else {
                            couponAdapter.addAll(list);
                        }


                    }


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
