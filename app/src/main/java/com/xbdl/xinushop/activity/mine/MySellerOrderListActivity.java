package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.mine.MyProductsReleaseAdapter;
import com.xbdl.xinushop.adapter.mine.MyProductstTransactionAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.SellerOrderListBean;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;

import java.util.List;

/*
* 我发布的商品交易信息
* */
public class MySellerOrderListActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private LRecyclerView recyclerView;
    private int activity;
    private int pageNumber=1;
    private MyProductstTransactionAdapter myProductstTransactionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products_detail);
        Intent intent = getIntent();
        activity= intent.getIntExtra("activity",0);
        initView();
        initData();
    }



    private void initView() {
        findViewById(R.id.iv_return).setOnClickListener(this);
        title= (TextView)findViewById(R.id.tv_title);
        recyclerView= findViewById(R.id.recyclerView);

    }
    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setPullRefreshEnabled(false);
        myProductstTransactionAdapter= new MyProductstTransactionAdapter(getActivity());
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(myProductstTransactionAdapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.dp_3)
                .setColorResource(R.color.enptyviewbackground)
                .build();
        recyclerView.addItemDecoration(divider);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getSellerOrderList(pageNumber);
            }
        });
        if (activity==0){
            //交易消息
            title.setText(getResources().getString(R.string.transactionmsg));
            getSellerOrderList(pageNumber);
        }else {
            //售后消息
            title.setText(getResources().getString(R.string.aftersalemsg));

        }
    }

    private void getSellerOrderList(int pn) {
        HttpUtils2.getSellerOrdersByUserIdApi(MyApplication.user.getLoginToken(), pn, MyApplication.user.getUserId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                SellerOrderListBean bean = gson.fromJson(response.body(), SellerOrderListBean.class);
                if (bean.getCode()==1){
                    SellerOrderListBean.PageInfoBean pageInfo = bean.getPageInfo();
                    if (pageInfo.isHasNextPage()){
                        recyclerView.setLoadMoreEnabled(true);
                    }else {
                        recyclerView.setLoadMoreEnabled(false);
                    }
                    List<SellerOrderListBean.PageInfoBean.ListBean> list = pageInfo.getList();
                    if (pageNumber==1){
                        myProductstTransactionAdapter.setDataList(list);
                    }else {
                        myProductstTransactionAdapter.addAll(list);
                    }
                    pageNumber++;
                }else {
                    ToastUtil.shortToast(getActivity(),"数据解析异常");
                }
                dismissLoading();
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
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                ToastUtil.shortToast(getActivity(),"数据解析异常");
                dismissLoading();
            }
        });
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_return:
                    finish();
                    break;
            }
    }

}
