package com.xbdl.xinushop.activity.mine.wallet;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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
import com.xbdl.xinushop.adapter.mine.WalletDetailAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyProductsReleaseBean;
import com.xbdl.xinushop.bean.WalletDetailBean;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
* 交易明细
* */
public class WalletDetailActivity extends BaseActivity {
    private View detail_empty;
    private LRecyclerView recyclerView;
    private int pageNumber=1;
    private WalletDetailAdapter walletDetailAdapter;
    ArrayList<WalletDetailBean.ListBean> listBeans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_detail);
        listBeans= new ArrayList<>();
        initView();
        initData();
    }



    private void initView() {
        findViewById(R.id.iv_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        detail_empty= findViewById(R.id.ll__detail_empty);
        recyclerView= findViewById(R.id.recyclerView);
    }
    private void initData() {
        getListPn(pageNumber);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        walletDetailAdapter= new WalletDetailAdapter(this);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(walletDetailAdapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.dp_5)
                .setColorResource(R.color.enptyviewbackground)
                .build();
        recyclerView.addItemDecoration(divider);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getListPn(pageNumber);
            }
        });
    }

    private void getListPn(int pn) {
        HttpUtils2.getWalletDetailByInfo(MyApplication.user.getLoginToken(),pn, MyApplication.user.getUserId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    if (jsonObject.getInt("code")==1){
                        String pageInfo = jsonObject.getString("pageInfo");
                        Gson gson = new Gson();
                        WalletDetailBean myProductsReleaseBean = gson.fromJson(pageInfo, WalletDetailBean.class);
                        List<WalletDetailBean.ListBean> list = myProductsReleaseBean.getList();
                        if (list!=null){
                            listBeans.addAll(list);
                            walletDetailAdapter.setDataList(listBeans);
                            recyclerView.setVisibility(View.VISIBLE);
                            detail_empty.setVisibility(View.GONE);
                        }else {
                            recyclerView.setVisibility(View.GONE);
                            detail_empty.setVisibility(View.VISIBLE);
                        }

                        if (myProductsReleaseBean.isHasNextPage()){
                            recyclerView.setLoadMoreEnabled(true);
                        }else {
                            recyclerView.setLoadMoreEnabled(false);
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
                Log.v("nihaoma","222222");
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
        OkGo.getInstance().cancelTag("getWalletDetailByInfo");
    }
}
