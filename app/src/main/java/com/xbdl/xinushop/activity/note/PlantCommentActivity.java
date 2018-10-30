package com.xbdl.xinushop.activity.note;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.note.NoteDetailAdapter;
import com.xbdl.xinushop.adapter.note.PlantCommentListAdapter;
import com.xbdl.xinushop.adapter.note.PlantImgListAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.NoteDetailBean;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.bean.PlantCommentBean;
import com.xbdl.xinushop.constant.UrlConstant;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 种植日记评论
 * */
public class PlantCommentActivity extends BaseActivity {
    private NoteDetailBean.ExtendBean.DiaryBean bean;
    private TextView tv_like_conut,tv_username,tv_date,tv_work;
    private ImageView iv_islike;
    private LRecyclerView recyclerView;
    private RecyclerView head_recyclerView;

    private CircleImageView civ_head_icon;
    private PlantCommentListAdapter plantCommentListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_comment);
        bean= (NoteDetailBean.ExtendBean.DiaryBean) getIntent().getSerializableExtra("bean");
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
        tv_like_conut= findViewById(R.id.tv_like_conut);
        iv_islike= findViewById(R.id.iv_islike);
        recyclerView= findViewById(R.id.recyclerView);
        tv_username = findViewById(R.id.tv_username);
        tv_date = findViewById(R.id.tv_date);
        head_recyclerView =findViewById(R.id.recyclerView2);
        civ_head_icon = findViewById(R.id.civ_head_icon);
        tv_work = findViewById(R.id.tv_work);

    }
    private void initData() {
        tv_like_conut.setText(String.valueOf(bean.getDirayToClickTheNumberOfLikes()));
        if (bean.isLike()){
            iv_islike.setImageResource(R.drawable.heart_xuanzhong_luntan);
        }else {
            iv_islike.setImageResource(R.drawable.heart_luntan);
        }
        String time = bean.getDirayCreateTime();
        String tempStr = time.substring(0, 10);
        tv_date.setText(tempStr);
        //设置图片adapter
        List<NoteDetailBean.ExtendBean.DiaryBean.DirayIamgeBean> dirayIamge = bean.getDirayIamge();
        ArrayList<String> urls = new ArrayList<>();
        for (NoteDetailBean.ExtendBean.DiaryBean.DirayIamgeBean bean:dirayIamge){
            urls.add(UrlConstant.baseimgUrl+bean.getDiaryImageUrl());
        }
        PlantImgListAdapter plantImgListAdapter = new PlantImgListAdapter(getActivity());
        plantImgListAdapter.setDataList(urls);
        head_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                //解决ScrollView里存在多个RecyclerView时滑动卡顿的问题
                //如果你的RecyclerView是水平滑动的话可以重写canScrollHorizontally方法
                return false;
            }

        });
        head_recyclerView.setAdapter(plantImgListAdapter);
        //解决数据加载不完的问题
        head_recyclerView.setNestedScrollingEnabled(false);
        head_recyclerView.setHasFixedSize(true);
//解决数据加载完成后, 没有停留在顶部的问题
        head_recyclerView.setFocusable(false);
        //设置大的adapter
        plantCommentListAdapter = new PlantCommentListAdapter(getActivity());
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(plantCommentListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                //解决ScrollView里存在多个RecyclerView时滑动卡顿的问题
                //如果你的RecyclerView是水平滑动的话可以重写canScrollHorizontally方法
                return false;
            }
        });
        recyclerView.setAdapter(lRecyclerViewAdapter);
        recyclerView.setPullRefreshEnabled(false);
        //解决数据加载不完的问题
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
//解决数据加载完成后, 没有停留在顶部的问题
        recyclerView.setFocusable(false);

        getUserInfo();
        //点赞
        iv_islike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                islike();
            }
        });
        getCommentList();
    }
    //获取评论列表
    private void getCommentList() {
        HttpUtils2.appViewAllReviews(MyApplication.user.getLoginToken(), bean.getDiaryId(), MyApplication.user.getUserId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                PlantCommentBean plantCommentBean = new Gson().fromJson(response.body(), PlantCommentBean.class);
                if (plantCommentBean.getCode()==100){
                    Log.v("nihaoma","评论列表"+response.body());
                    List<PlantCommentBean.ExtendBean.DiaryCommentLayersBean> beans = plantCommentBean.getExtend().getDiaryCommentLayers();
                    plantCommentListAdapter.setDataList(beans);
                }else {
                    ToastUtil.shortToast(getActivity(),"数据错误请稍后再试");
                }
            }
        });
    }

    private void islike() {
        HttpUtils2.appDiaryLikes(MyApplication.user.getLoginToken(), MyApplication.user.getUserId(), bean.getDiaryId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma","点赞"+response.body());
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    if (jsonObject.getInt("code")==100){
                        String extend = jsonObject.getString("extend");
                        JSONObject extendObject = new JSONObject(extend);
                        boolean isLike = extendObject.getBoolean("isLike");
                        if (isLike){
                            iv_islike.setImageResource(R.drawable.heart_xuanzhong_luntan);
                        }else {
                           iv_islike.setImageResource(R.drawable.heart_luntan);
                        }
                    }else {
                        ToastUtil.shortToast(getActivity(),"点赞失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void getUserInfo() {
        HttpUtils2.getUserInfoById(MyApplication.user.getLoginToken(), bean.getDiaryUserId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma",response.body());
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    if (jsonObject.getInt("code")==1){
                        String object = jsonObject.getString("user");
                        Gson gson = new Gson();
                        PersonBean personBean = gson.fromJson(object, PersonBean.class);
                        //设置用户信息
                        tv_username.setText(personBean.getUserName());
                        if (MyApplication.user.getHeadPortrait()!=null){
                            Glide.with(getActivity()).load(personBean.getHeadPortrait()).into(civ_head_icon);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }
                dismissLoading();
            }
        });
    }
    @Override
    protected Activity getActivity() {
        return this;
    }
}
