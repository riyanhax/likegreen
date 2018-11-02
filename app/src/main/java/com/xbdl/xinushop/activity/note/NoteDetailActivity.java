package com.xbdl.xinushop.activity.note;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.note.NoteDetailAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.NoteDetailBean;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.constant.UrlConstant;
import com.xbdl.xinushop.dialogfragment.PlantDailog;
import com.xbdl.xinushop.evnetBus.PlantEvnet;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/*
* 日记图片点击
* */
public class NoteDetailActivity extends BaseActivity implements View.OnClickListener, PlantDailog.LoginInputListener {
    private LRecyclerView recyclerView;
    private  int diaryRootId,userId,numFans,days,concernState;
    private NoteDetailAdapter noteDetailAdapter;
    private CircleImageView civ_head_icon;
    private TextView tv_username,tv_day_focus,tv_focus,tv_title,tv_plantname,tv_fewdays;
    private ImageView iv_img1,iv_img2,iv_ed,iv_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        Intent intent = getIntent();
        diaryRootId = intent.getIntExtra("diaryRootId", 0);
        userId = intent.getIntExtra("userId", 0);
        numFans = intent.getIntExtra("numFans", 0);
        days = intent.getIntExtra("days", 0);
        concernState = intent.getIntExtra("concernState", 0);

        initView();
        initData();
        addVisableCount();
    }

    private void addVisableCount() {
        HttpUtils2.appAddNumberOfViews(MyApplication.user.getLoginToken(), diaryRootId, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_return).setOnClickListener(this);
        recyclerView= findViewById(R.id.recyclerView);
         tv_title = findViewById(R.id.tv_title);
         iv_delete = findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(this);
    }

    private void initData() {

        View head = LayoutInflater.from(getActivity()).inflate(R.layout.note_head_recyclerview, (ViewGroup)findViewById(android.R.id.content), false);
        View headMy = LayoutInflater.from(getActivity()).inflate(R.layout.note_head_my, (ViewGroup)findViewById(android.R.id.content), false);
        View head2 = LayoutInflater.from(getActivity()).inflate(R.layout.plant_head_2, (ViewGroup)findViewById(android.R.id.content), false);
        tv_plantname = headMy.findViewById(R.id.tv_plantname);
        tv_fewdays = headMy.findViewById(R.id.tv_fewdays);
       headMy.findViewById(R.id.iv_ed).setOnClickListener(this);
        civ_head_icon= head.findViewById(R.id.civ_head_icon);
        tv_username= head.findViewById(R.id.tv_username);
        tv_day_focus= head.findViewById(R.id.tv_day_focus);
        tv_focus= head.findViewById(R.id.tv_focus);
        iv_img1= head2.findViewById(R.id.iv_img1);
        iv_img2= head2.findViewById(R.id.iv_img2);
        tv_day_focus.setText("天数" +days+"| 关注" +numFans);

        //0 未关注 1 关注 2相互关注
        if (concernState==0){
            tv_focus.setText(getResources().getString(R.string.focus));
            tv_focus.setTextColor(getResources().getColor(R.color.white));
            tv_focus.setBackground(getDrawable(R.drawable.my_focuse));
        }else if (concernState==1){
            //已关注
            tv_focus.setText(getResources().getString(R.string.focused));
            tv_focus.setTextColor(getResources().getColor(R.color.cblack));
            tv_focus.setBackground(getDrawable(R.drawable.my_focuse_together));
        }else {
            tv_focus.setText(getResources().getString(R.string.focustogether));
            tv_focus.setTextColor(getResources().getColor(R.color.cblack));
            tv_focus.setBackground(getDrawable(R.drawable.my_focuse_together));
        }
        tv_focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState();
            }
        });
        noteDetailAdapter = new NoteDetailAdapter(getActivity());
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(noteDetailAdapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setPullRefreshEnabled(false);
        if (userId==MyApplication.user.getUserId()){
            //自己的主页
            lRecyclerViewAdapter.addHeaderView(headMy);
            lRecyclerViewAdapter.addHeaderView(head2);
            iv_delete.setVisibility(View.VISIBLE);
        }else {
            lRecyclerViewAdapter.addHeaderView(head);
            lRecyclerViewAdapter.addHeaderView(head2);
            iv_delete.setVisibility(View.GONE);
        }

        noteDetailAdapter.setMyViewClick(new NoteDetailAdapter.MyViewClick() {
            @Override
            public void showCommentPop(View view, NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean bean) {
                //跳到每个日记
                Intent intent = new Intent(getActivity(), PlantCommentActivity.class);
                intent.putExtra("bean",bean);
                startActivityForResult(intent,100);
            }


        });
        getUserInfo();
        getFocus();
        getlist();
    }

    private void getUserInfo() {
       HttpUtils2.getUserInfoById(MyApplication.user.getLoginToken(), userId, new StringCallback() {
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
                        if (userId==MyApplication.user.getUserId()){
                            tv_title.setText("我的主页");
                        }else {
                            tv_title.setText(personBean.getUserName()+"的主页");
                            tv_username.setText(personBean.getUserName());
                        }


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
    //根据根目录id获取下属的日记
    String diaryRootTitle;
    private void getlist() {
        HttpUtils2.appGetCurrentGroupDiaries(MyApplication.user.getLoginToken(), diaryRootId,MyApplication.user.getUserId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma", "onSuccess"+response.body());
               NoteDetailBean noteDetailBean = new Gson().fromJson(response.body(), NoteDetailBean.class);
                if (noteDetailBean.getCode()==100){
                    NoteDetailBean.ExtendBean.DiaryRootBean diaryRoot = noteDetailBean.getExtend().getDiaryRoot();

                    List<NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean> diarys = noteDetailBean.getExtend().getDiaryRoot().getDiarys();

                        noteDetailAdapter.setDataList(diarys);
                        //植物名
                        diaryRootTitle= noteDetailBean.getExtend().getDiaryRoot().getDiaryRootTitle();
                        tv_plantname.setText(diaryRootTitle);
                        tv_fewdays.setText("已养育"+noteDetailBean.getExtend().getDiaryRoot().getFewDays());
                        //设置两张对比图片
                        if (diaryRoot.getFewDays()>=3){
                            iv_img1.setScaleType(ImageView.ScaleType.FIT_XY);
                            iv_img2.setScaleType(ImageView.ScaleType.FIT_XY);
                            Glide.with(MyApplication.context).load(UrlConstant.baseimgUrl+diarys.get(0).getDirayIamge().get(0).getDiaryImageUrl()).into(iv_img1);
                            Glide.with(MyApplication.context).load(UrlConstant.baseimgUrl+diarys.get(diarys.size()-1).getDirayIamge().get(0).getDiaryImageUrl()).into(iv_img2);
                        }

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
                Log.v("nihaoma", "onError"+response.body());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dismissLoading();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.iv_ed:
                PlantDailog plantDailog = new PlantDailog();
                plantDailog.show(getSupportFragmentManager(), plantDailog.getClass().getName());
                break;
            case R.id.iv_delete:
                deleteDiary();
                break;
        }
    }
    //删除日记
    private void deleteDiary() {
        HttpUtils2.appdeleteDiary2(MyApplication.user.getLoginToken(), diaryRootId, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    if (new JSONObject(response.body()).getInt("code")==100){
                        ToastUtil.shortToast(getActivity(),"删除成功");
                        //发送事件
                        EventBus.getDefault().post(PlantEvnet.Refresh);
                        finish();
                    }else {
                        ToastUtil.shortToast(getActivity(),"删除失败");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //修改植物名称
    private void changePlantName(final String plantname) {

        HttpUtils2.appUpdateDiaryRoot(MyApplication.user.getLoginToken(), diaryRootId, plantname, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    if (new JSONObject(response.body()).getInt("code")==100){
                        ToastUtil.shortToast(getActivity(),"修改成功");
                        tv_plantname.setText(plantname);
                    }else {
                        ToastUtil.shortToast(getActivity(),"修改失败");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    //改变状态
    private void changeState() {
        switch (concernState){
            case 2:
                //相互关注
                HttpUtils2.appCancelYourAttention(MyApplication.user.getLoginToken(),
                        MyApplication.user.getUserId(),userId, new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.v("nihaoma","点取消关注"+response.body());
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    int code = jsonObject.getInt("code");
                                    if (code==100){
                                        //成功
                                        tv_focus.setText(getResources().getString(R.string.focus));
                                        tv_focus.setTextColor(getResources().getColor(R.color.white));
                                        tv_focus.setBackground(getDrawable(R.drawable.my_focuse));
                                        concernState=0;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
            case 1:
                //已关注
                HttpUtils2.appCancelYourAttention(MyApplication.user.getLoginToken(),
                        MyApplication.user.getUserId(),userId, new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.v("nihaoma","点取消关注"+response.body());
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    int code = jsonObject.getInt("code");
                                    if (code==100){
                                        //成功
                                        tv_focus.setText(getResources().getString(R.string.focus));
                                        tv_focus.setTextColor(getResources().getColor(R.color.white));
                                        tv_focus.setBackground(getDrawable(R.drawable.my_focuse));
                                        concernState=0;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
            case 0:
                //未关注
                HttpUtils2.appAddConcern(MyApplication.user.getLoginToken(),
                        MyApplication.user.getUserId(), userId, new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.v("nihaoma","点添加关注  "+response.body());
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    int code = jsonObject.getInt("code");
                                    if (code==100){
                                        //成功
                                        tv_focus.setText(getResources().getString(R.string.focused));
                                        tv_focus.setTextColor(getResources().getColor(R.color.cblack));
                                        tv_focus.setBackground(getDrawable(R.drawable.my_focuse_together));
                                        concernState=1;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;

        }
    }



    @Override
    protected Activity getActivity() {
        return this;
    }

    public void getFocus() {
  /*     HttpUtils2.appJudgeWhetherToPayAttention(MyApplication.user.getUserId(), userId, MyApplication.user.getLoginToken(), new StringCallback() {
           @Override
           public void onSuccess(Response<String> response) {
               try {
                   JSONObject jsonObject = new JSONObject(response.body());
                   int code = jsonObject.getInt("code");
                   if (code==100){
                       String extend = jsonObject.getString("extend");
                       JSONObject jsonObject1 = new JSONObject(extend);
                       int isConcern = jsonObject1.getInt("isConcern");
                       //isConcern = 1 表示未关注 如果isConcern = 0 表示已关注
                       if (isConcern==1){
                           tv_focus.setText(getResources().getString(R.string.focused));
                       }else  if (isConcern==0){

                       }

                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       });*/
    }
    //这只植物名字
    @Override
    public void onLoginInputComplete(String userName) {
        changePlantName(userName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==100){
            Log.v("nihaoma","评论返回数据");
            int itemCount = noteDetailAdapter.getItemCount();
            if (itemCount==1||itemCount==0){
                //发送事件
                EventBus.getDefault().post(PlantEvnet.Refresh);
                finish();
            }else {
                getlist();
            }

        }
    }
}
