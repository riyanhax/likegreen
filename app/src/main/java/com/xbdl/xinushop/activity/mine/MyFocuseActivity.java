package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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
import com.xbdl.xinushop.adapter.MyFocuseAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyFansBean;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/*
* 我的关注
* */
public class MyFocuseActivity extends BaseActivity {
    private LRecyclerView mList;
    private int page=1;
    private int type;
    private TextView title;
    private MyFocuseAdapter myFocuseAdapter;
    private int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_focuse);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 1);
        userid = intent.getIntExtra("userid", 1);
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
           getMyfoucse(page);
       }else {
           //粉丝
           title.setText(getResources().getString(R.string.myfans));
           getfoucseMy(page);
       }
        myFocuseAdapter.setClick(new MyFocuseAdapter.MyClick() {
            @Override
            public void itemClick(MyFansBean.ExtendBean.ConcernListBean.ListBean bean) {
                Intent intent = new Intent(getActivity(), UserDetailActivity.class);
                intent.putExtra("id",bean.getUser().getUserId());
                startActivity(intent);
            }

            @Override
            public void focuseClick(MyFansBean.ExtendBean.ConcernListBean.ListBean bean, final MyFocuseAdapter.MyHolder holder) {
                int whetherToBeConcerned = bean.getWhetherToBeConcerned();
                if (whetherToBeConcerned==0||whetherToBeConcerned==1){
                    //相互关注  已关注
                    HttpUtils2.appCancelYourAttention(MyApplication.user.getLoginToken(),
                            userid, bean.getUser().getUserId(), new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Log.v("nihaoma","点取消关注"+response.body());
                            try {
                                JSONObject jsonObject = new JSONObject(response.body());
                                int code = jsonObject.getInt("code");
                                if (code==100){
                                    //成功
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }else {
                   HttpUtils2.appAddConcern(MyApplication.user.getLoginToken(),
                           userid, bean.getUser().getUserId(), new StringCallback() {
                               @Override
                               public void onSuccess(Response<String> response) {
                                   Log.v("nihaoma","点添加关注  "+response.body());
                               }
                           });
                }
            }
        });
    }

    private void getMyfoucse(int pn) {
        HttpUtils2.appGetMyAttention(MyApplication.user.getLoginToken(), pn, userid, myCallback);
    }
    private void getfoucseMy(int pn) {
        HttpUtils2.appGetWhoWatchingMe(MyApplication.user.getLoginToken(), pn, userid, myCallback);
    }



    StringCallback myCallback=new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //Log.v("nihaoma",response.body());
                        Gson gson = new Gson();
                        MyFansBean myFansBean = gson.fromJson(response.body(), MyFansBean.class);
                        if ( myFansBean.getCode()==100){
                            MyFansBean.ExtendBean extend = myFansBean.getExtend();
                            MyFansBean.ExtendBean.ConcernListBean concernList = extend.getConcernList();
                            List<MyFansBean.ExtendBean.ConcernListBean.ListBean> list = concernList.getList();
                            if (page==1){
                                myFocuseAdapter.setDataList(list);
                            }else {
                                myFocuseAdapter.addAll(list);
                            }
                            if (concernList.isHasNextPage()){
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
                        Log.v("nihaoma","获取我关注的onError");
                        dismissLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissLoading();
                    }
                };

    @Override
    protected Activity getActivity() {
        return this;
    }
}
