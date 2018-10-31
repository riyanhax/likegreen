package com.xbdl.xinushop.activity.note;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
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

import com.xbdl.xinushop.adapter.note.PlantCommentListAdapter;
import com.xbdl.xinushop.adapter.note.PlantImgListAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.NoteDetailBean;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.bean.PlantCommentBean;
import com.xbdl.xinushop.constant.UrlConstant;

import com.xbdl.xinushop.dialogfragment.KeyMapDailogCommentToComment;
import com.xbdl.xinushop.dialogfragment.KeyMapDialog;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 种植日记评论
 * */
public class PlantCommentActivity extends BaseActivity {
    private NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean bean;
    private TextView tv_like_conut,tv_username,tv_date,tv_work,tv_commit;
    private ImageView iv_islike;
    private LRecyclerView recyclerView;
    private RecyclerView head_recyclerView;
    private EditText et_msg;
    private CircleImageView civ_head_icon;
    private PlantCommentListAdapter plantCommentListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_comment);
        bean= (NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean) getIntent().getSerializableExtra("bean");
        Log.v("nihaoma","传过来的数据"+bean.toString());
        if (bean.getDiaryUserId()==0){
            bean.setDiaryUserId(MyApplication.user.getUserId());
        }
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
        et_msg = findViewById(R.id.et_msg);
        tv_commit = findViewById(R.id.tv_commit);

       /* et_msg.setFocusable(true);
        et_msg.setFocusableInTouchMode(true);
        et_msg.requestFocus();*/
        et_msg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                    KeyMapDialog dialog =new KeyMapDialog("请输入...", new KeyMapDialog.SendBackListener() {
                        @Override
                        public void sendBack(String inputText) {
                            //发表评论
                            Log.v("nihaoma","发送打印");
                            sendComment(inputText);
                        }
                    });

                }

            }
        });

    }
    //发布评论
    private void sendComment(String inputText) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        HttpUtils2.appAddedDiaryCommentFloor(MyApplication.user.getLoginToken(), MyApplication.user.getUserId(),inputText, time, bean.getDiaryId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    if (jsonObject.getInt("code")==100){
                        //刷新页面
                        getCommentList();
                        ToastUtil.shortToast(getActivity(),"发布成功");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initData() {
        tv_like_conut.setText(String.valueOf(bean.getDirayToClickTheNumberOfLikes()));
        if (bean.isIsLike()){
            iv_islike.setImageResource(R.drawable.heart_xuanzhong_luntan);
        }else {
            iv_islike.setImageResource(R.drawable.heart_luntan);
        }
        String time = bean.getDirayCreateTime();
        String tempStr = time.substring(0, 10);
        tv_date.setText(tempStr);
        tv_work.setText(bean.getDiaryDynamic());
        //设置图片adapter
        List<NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean.DirayIamgeBean> dirayIamge = bean.getDirayIamge();
        ArrayList<String> urls = new ArrayList<>();
        for (NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean.DirayIamgeBean bean:dirayIamge){
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
        //添加Android自带的分割线
        head_recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
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


        //item的点击事件  评论评论
        plantCommentListAdapter.setClick(new PlantCommentListAdapter.CommentKey() {
            @Override
            public void key(final PlantCommentBean.ExtendBean.DiaryCommentLayersBean bean) {
                Log.v("nihaoma","评论中评论打印");
                commentToComment(bean);
            }
        });
        getUserInfo();
        //点赞
        iv_islike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                islike();
            }
        });
        getCommentList();

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_msg.getText())){
                    ToastUtil.shortToast(getActivity(),"请输入内容");
                    return;
                }
                sendComment(et_msg.getText().toString());
            }
        });
    }
    //评论中评论
    private void commentToComment(final PlantCommentBean.ExtendBean.DiaryCommentLayersBean bean) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        KeyMapDailogCommentToComment dialog =new KeyMapDailogCommentToComment("请输入...", new KeyMapDailogCommentToComment.SendBackListener() {
            @Override
            public void sendBack(String inputText) {
                HttpUtils2.appCommentDiaryCommentFloor(MyApplication.user.getLoginToken(), bean.getDiaryId(), MyApplication.user.getUserId(),
                        bean.getDiaryCommentLayerUserId(), inputText, bean.getDiaryCommentLayerId(), new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    if (jsonObject.getInt("code")==100){
                                        //刷新页面
                                        getCommentList();
                                        ToastUtil.shortToast(getActivity(),"发布成功");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });
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
