package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
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
public  class MyProductsReleaseActivity extends BaseActivity implements View.OnClickListener {
    private LRecyclerView mRecyclerView;
    private int pageNumber=1;
    private List<MyProductsReleaseBean.ListBean> dataList;
    private MyProductsReleaseAdapter myProductsReleaseAdapter;
    private ArrayList<MyProductsReleaseBean.ListBean> listBeans;
    private View news;
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
        findViewById(R.id.iv_products_return).setOnClickListener(this);
        news = findViewById(R.id.iv_products_news);
        news.setOnClickListener(this);

    }
    private PopupWindow huoZanPop;
    private void showPop() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_my_porducts, null);
        contentView.findViewById(R.id.ll_transactionmsg).setOnClickListener(this);
        contentView.findViewById(R.id.ll_aftersalemsg).setOnClickListener(this);
        huoZanPop = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        huoZanPop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        huoZanPop.setOutsideTouchable(true);
        huoZanPop.setTouchable(true);
        //设置半透明
        WindowManager.LayoutParams lp = getActivity().getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
        //恢复正常
        huoZanPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);
            }
        });
        huoZanPop.showAsDropDown(news,-80,5);

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
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    private void getListPn(int pn) {
        HttpUtils2.getCommodityByUserId(MyApplication.user.getLoginToken(),pn, MyApplication.user.getUserId(), new StringCallback() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_products_return:
                finish();
                break;
            case R.id.iv_products_news:
                showPop();
                break;
            case R.id.ll_transactionmsg:
                Intent intent = new Intent(this, MySellerOrderListActivity.class);
                intent.putExtra("activity",0);
                startActivity(intent);
                huoZanPop.dismiss();
                break;
            case R.id.ll_aftersalemsg:
                Intent intent1 = new Intent(this, MySellerOrderListActivity.class);
                intent1.putExtra("activity",1);
                startActivity(intent1);
                huoZanPop.dismiss();
                break;
        }
    }
}