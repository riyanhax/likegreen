package com.xbdl.xinushop.activity.note;

import android.app.Activity;
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
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.NoteDetailBean;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/*
* 日记图片点击
* */
public class NoteDetailActivity extends BaseActivity implements View.OnClickListener {
    private LRecyclerView recyclerView;
    private  int diaryRootId,userId;
    private NoteDetailAdapter noteDetailAdapter;
    private CircleImageView civ_head_icon;
    private TextView tv_username,tv_day_focus,tv_focus;
    private ImageView iv_img1,iv_img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        diaryRootId = getIntent().getIntExtra("diaryRootId", 0);
        userId = getIntent().getIntExtra("userId", 0);
        initView();
        initData();
    }

    private void initView() {
        findViewById(R.id.iv_return).setOnClickListener(this);
        recyclerView= findViewById(R.id.recyclerView);
    }

    private void initData() {

        View head = LayoutInflater.from(getActivity()).inflate(R.layout.note_head_recyclerview, (ViewGroup)findViewById(android.R.id.content), false);
       // View headMy = LayoutInflater.from(getActivity()).inflate(R.layout.note_head_my, recyclerView, false);
        noteDetailAdapter = new NoteDetailAdapter(getActivity());
        civ_head_icon= head.findViewById(R.id.civ_head_icon);
        tv_username= head.findViewById(R.id.tv_username);
        tv_day_focus= head.findViewById(R.id.tv_day_focus);
        tv_focus= head.findViewById(R.id.tv_focus);
        iv_img1= head.findViewById(R.id.iv_img1);
        iv_img2= head.findViewById(R.id.iv_img2);


        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(noteDetailAdapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setPullRefreshEnabled(false);
        lRecyclerViewAdapter.addHeaderView(head);
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

    private void getlist() {
        HttpUtils2.appGetCurrentGroupDiaries(MyApplication.user.getLoginToken(), diaryRootId, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma", "onSuccess"+response.body());
                NoteDetailBean noteDetailBean = new Gson().fromJson(response.body(), NoteDetailBean.class);
                if (noteDetailBean.getCode()==100){
                    List<NoteDetailBean.ExtendBean.DiaryBean> diary = noteDetailBean.getExtend().getDiary();
                    noteDetailAdapter.setDataList(diary);

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

    @Override
    protected Activity getActivity() {
        return this;
    }

    public void getFocus() {
       HttpUtils2.appJudgeWhetherToPayAttention(MyApplication.user.getUserId(), userId, MyApplication.user.getLoginToken(), new StringCallback() {
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
       });
    }
}
