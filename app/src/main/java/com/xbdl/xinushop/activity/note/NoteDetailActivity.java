package com.xbdl.xinushop.activity.note;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
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
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.note.NoteDetailAdapter;
import com.xbdl.xinushop.adapter.note.NoteListAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.NoteDetailBean;
import com.xbdl.xinushop.bean.NoteHotBean;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.dialogfragment.PlantsCommentDialogFragment;
import com.xbdl.xinushop.dialogfragment.RecommentDialogFragment;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/*
* 日记图片点击
* */
public class NoteDetailActivity extends BaseActivity implements View.OnClickListener {
    private LRecyclerView recyclerView;
    private  int diaryRootId,userId,numFans,days,concernState;
    private NoteDetailAdapter noteDetailAdapter;
    private CircleImageView civ_head_icon;
    private TextView tv_username,tv_day_focus,tv_focus,tv_title;
    private ImageView iv_img1,iv_img2;

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
    }

    private void initData() {

        View head = LayoutInflater.from(getActivity()).inflate(R.layout.note_head_recyclerview, (ViewGroup)findViewById(android.R.id.content), false);
       // View headMy = LayoutInflater.from(getActivity()).inflate(R.layout.note_head_my, recyclerView, false);

        civ_head_icon= head.findViewById(R.id.civ_head_icon);
        tv_username= head.findViewById(R.id.tv_username);
        tv_day_focus= head.findViewById(R.id.tv_day_focus);
        tv_focus= head.findViewById(R.id.tv_focus);
        iv_img1= head.findViewById(R.id.iv_img1);
        iv_img2= head.findViewById(R.id.iv_img2);
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
        noteDetailAdapter = new NoteDetailAdapter(getActivity());
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(noteDetailAdapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setPullRefreshEnabled(false);
        lRecyclerViewAdapter.addHeaderView(head);
        noteDetailAdapter.setMyViewClick(new NoteDetailAdapter.MyViewClick() {
            @Override
            public void showCommentPop(View view, NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean bean) {
                Intent intent = new Intent(getActivity(), PlantCommentActivity.class);
                intent.putExtra("bean",bean);
                startActivity(intent);
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
                        tv_title.setText(personBean.getUserName()+"的主页");
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
    //根据根目录id获取下属的日记
    private void getlist() {
        HttpUtils2.appGetCurrentGroupDiaries(MyApplication.user.getLoginToken(), diaryRootId,MyApplication.user.getUserId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma", "onSuccess"+response.body());
               NoteDetailBean noteDetailBean = new Gson().fromJson(response.body(), NoteDetailBean.class);
                if (noteDetailBean.getCode()==100){
                    List<NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean> diarys = noteDetailBean.getExtend().getDiaryRoot().getDiarys();
                    noteDetailAdapter.setDataList(diarys);

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
        switch (v.getId()){
            case R.id.iv_return:
                finish();
                break;
        }
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
}
