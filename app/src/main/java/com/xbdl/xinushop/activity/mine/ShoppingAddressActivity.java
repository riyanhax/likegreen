package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.mine.AddressListAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.AddressBean;
import com.xbdl.xinushop.constant.UrlConstant2;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/*
 *收货地址
 * */
public class ShoppingAddressActivity extends BaseActivity {
    private LRecyclerView recyclerView;
    private int add=0;
    private int update=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_address);
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
        recyclerView= (LRecyclerView)findViewById(R.id.addresslist);
        //添加收货地址
        findViewById(R.id.ll_add_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingAddressActivity.this, AddAddressActivity.class);
                intent.putExtra("ShoppingAddressActivity",add);
                startActivity(intent);
            }
        });
    }
    private List<AddressBean.AddressListBean> addressList;
    private AddressListAdapter addressListAdapter;
    private void initData() {
        getListNetWord();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setPullRefreshEnabled(false);
        addressListAdapter = new AddressListAdapter(this);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(addressListAdapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        DividerDecoration divider = new DividerDecoration.Builder(this)
               // .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.dp_2)
                .setColorResource(R.color.enptyviewbackground)
                .build();
        recyclerView.addItemDecoration(divider);
        addressListAdapter.setAddressClick(new AddressListAdapter.AddressClick() {
            @Override
            public void checkboxClick(int addressId) {
                HttpUtils2.setAddressDefault(addressId, myStringCallback);
                getListNetWord();
            }

            @Override
            public void editClick(AddressBean.AddressListBean bean) {
                Intent intent = new Intent(ShoppingAddressActivity.this, AddAddressActivity.class);
                intent.putExtra("ShoppingAddressActivity",update);
                intent.putExtra("addressBean",bean);
                startActivity(intent);
                addressListAdapter.notifyDataSetChanged();
            }

            @Override
            public void deleteClick(int addressId) {
                HttpUtils2.delAddress(addressId,myStringCallback);
                getListNetWord();
            }
        });
    }

    private void getListNetWord() {

        HttpUtils2.getAddressList(MyApplication.user.getUserId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma","4444444444");
                Gson gson = new Gson();
                AddressBean addressBean = gson.fromJson(response.body(), AddressBean.class);
                addressList= addressBean.getAddressList();
                Log.v("nihaoma", addressList.toString());
                if (addressList!=null)
                    addressListAdapter.setDataList(addressList);
                dismissLoading();

            }

            @Override
            public void onFinish() {
                super.onFinish();
                dismissLoading();
                Log.v("nihaoma","3333333");
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                showLoading();
                Log.v("nihaoma","1111111111111");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissLoading();
                Log.v("nihaoma","22222222222");
            }
        });
    }




    StringCallback myStringCallback=new StringCallback() {
        @Override
        public void onSuccess(Response<String> response) {
            try {
                JSONObject jsonObject = new JSONObject(response.body());
                int code = jsonObject.getInt("code");
                if (code==1){
                    ToastUtil.shortToast(ShoppingAddressActivity.this,jsonObject.getString("msg"));
                }else {
                    ToastUtil.shortToast(ShoppingAddressActivity.this,jsonObject.getString("msg"));
                }
            } catch (JSONException e) {


            }
            dismissLoading();
        }

        @Override
        public void onFinish() {
            super.onFinish();
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
    };


    @Override
    protected Activity getActivity() {
        return this;
    }
}
