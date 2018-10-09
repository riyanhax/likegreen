package com.xbdl.xinushop.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

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
import com.xbdl.xinushop.adapter.mine.MyProductsReleaseAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyProductsReleaseBean;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
我发布的商品
* */
public class MyProductsReleaseActivity extends BaseActivity {
    private LRecyclerView mRecyclerView;
    private int pageNumber=1;
    private List<MyProductsReleaseBean.ListBean> dataList;
    private MyProductsReleaseAdapter myProductsReleaseAdapter;
    private ArrayList<MyProductsReleaseBean.ListBean> listBeans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products_release);
        listBeans= new ArrayList<>();
        initView();
        initData();
    }



    private void initView() {
        dataList=new ArrayList<>();
        mRecyclerView =(LRecyclerView) findViewById(R.id.lr_products_list);
        findViewById(R.id.iv_products_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initData() {
        getListPn(pageNumber);
        myProductsReleaseAdapter= new MyProductsReleaseAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(myProductsReleaseAdapter);
        mRecyclerView.setAdapter(lRecyclerViewAdapter);
       DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.dp_5)
                .setColorResource(R.color.enptyviewbackground)
                .build();
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setPullRefreshEnabled(false);

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getListPn(pageNumber);
            }
        });
    }

    private void getListPn(int pn) {
        HttpUtils2.getCommodityByUserId(pn, MyApplication.user.getUserId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    if (jsonObject.getInt("code")==1){
                        String pageInfo = jsonObject.getString("pageInfo");
                        Gson gson = new Gson();
                        MyProductsReleaseBean myProductsReleaseBean = gson.fromJson(pageInfo, MyProductsReleaseBean.class);
                        List<MyProductsReleaseBean.ListBean> list = myProductsReleaseBean.getList();
                        listBeans.addAll(list);
                        myProductsReleaseAdapter.setDataList(listBeans);
                        if ( jsonObject.getBoolean("hasNextPage")){
                            mRecyclerView.setLoadMoreEnabled(true);
                        }else {
                            mRecyclerView.setLoadMoreEnabled(false);
                        }
                        pageNumber++;
                    }else {
                        ToastUtil.shortToast(getActivity(),jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dismissLoading();
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                showLoading();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissLoading();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dismissLoading();
            }
        });
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag("getCommodityByUserId");
    }
}
