package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mian.UserDetailActivity;
import com.xbdl.xinushop.activity.note.UserMainpageActivity;
import com.xbdl.xinushop.adapter.MyFocuseAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyFansBean;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;

import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
import cn.jpush.im.android.api.model.UserInfo;
/*
* 我的关注
* */
public class MyFocuseActivity extends BaseActivity {
    private LRecyclerView mList;
    private int page=1;
    private int type;
    private TextView title;
    private MyFocuseAdapter myFocuseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_focuse);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 1);
        initView();
        initData();

    }


    private void initView() {
       mList = (LRecyclerView)findViewById(R.id.focuse_list);
        title= findViewById(R.id.tv_title);
        findViewById(R.id.focuse_return).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              finish();
          }
      });

    }

    private void initData() {
       /* ContactManager.getFriendList(new GetUserInfoListCallback() {
            @Override
            public void gotResult(int i, String s, List<UserInfo> list) {
                if (0 == i) {
                    //获取好友列表成功
                } else {
                    //获取好友列表失败
                }
            }
        });*/


        myFocuseAdapter = new MyFocuseAdapter(this);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(myFocuseAdapter);
        mList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mList.setAdapter(lRecyclerViewAdapter);
        mList.setPullRefreshEnabled(false);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.dp_3)
                .setColorResource(R.color.enptyviewbackground)
                .build();
        mList.addItemDecoration(divider);
       if (type==1){
           //我的关注
           title.setText(getResources().getString(R.string.myfocuse));
           getData(1,page);
       }else {
           //粉丝
           title.setText(getResources().getString(R.string.myfans));
           getData(2,page);
       }
        myFocuseAdapter.setClick(new MyFocuseAdapter.MyClick() {
            @Override
            public void itemClick(MyFansBean.PdBean.ListBean bean) {
                Intent intent = new Intent(getActivity(), UserDetailActivity.class);
                intent.putExtra("id",bean.getUser_id());
                startActivity(intent);
            }

            @Override
            public void focuseClick(MyFansBean.PdBean.ListBean bean) {

            }
        });
    }

    private void getData(int followType,int pn) {
        HttpUtils2.myFansLsit(MyApplication.user.getLoginToken(), pn, followType, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                MyFansBean myFansBean = gson.fromJson(response.body(), MyFansBean.class);
                if ( myFansBean.getCode()==100){
                    MyFansBean.PdBean pd = myFansBean.getPd();
                    List<MyFansBean.PdBean.ListBean> list = pd.getList();
                    if (page==1){
                        myFocuseAdapter.setDataList(list);
                    }else {
                        myFocuseAdapter.addAll(list);
                    }
                    if (pd.isHasNextPage()){
                        mList.setLoadMoreEnabled(true);
                    }else {
                        mList.setLoadMoreEnabled(false);
                    }
                }else {
                    ToastUtil.shortToast(getActivity(),getResources().getString(R.string.errrotip));
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
                ToastUtil.shortToast(getActivity(),getResources().getString(R.string.errrotip));
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
}
