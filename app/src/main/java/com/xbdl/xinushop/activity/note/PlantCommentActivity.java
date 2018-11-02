package com.xbdl.xinushop.activity.note;

import android.app.Activity;
import android.content.Intent;
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

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
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
import com.xbdl.xinushop.utils.TimeUtil;
import com.xbdl.xinushop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 种植日记评论
 * */
public class PlantCommentActivity extends BaseActivity implements View.OnClickListener {
    private NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean bean;
    private TextView tv_like_conut,tv_username,tv_date,tv_work,tv_commit;
    private ImageView iv_islike;
    private LRecyclerView recyclerView;
    private RecyclerView head_recyclerView;
    private EditText et_msg;
    private CircleImageView civ_head_icon;
    private PlantCommentListAdapter plantCommentListAdapter;
    private View ll_delete_de;
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
        initCustomTimePicker();
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
        ll_delete_de = findViewById(R.id.ll_delete_de);//删除跟编辑
        findViewById(R.id.iv_ed).setOnClickListener(this);
        findViewById(R.id.iv_delete).setOnClickListener(this);
        if (bean.getDiaryUserId()==MyApplication.user.getUserId()){
            //自己的日记
            ll_delete_de.setVisibility(View.VISIBLE);
        }else {
            ll_delete_de.setVisibility(View.GONE);
        }
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
                   // dialog.show(getSupportFragmentManager(),dialog.getClass().getName());
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
                        et_msg.setText("");
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

        String s = TimeUtil.formatDisplayTime(time, null);
        tv_date.setText(s);
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
        //评论当楼主
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
    KeyMapDailogCommentToComment dialog;
    private void commentToComment(final PlantCommentBean.ExtendBean.DiaryCommentLayersBean bean) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog=new KeyMapDailogCommentToComment("请输入...", new KeyMapDailogCommentToComment.SendBackListener() {
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
                                        dialog.hideProgressdialog();
                                        ToastUtil.shortToast(getActivity(),"发布成功");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });
        dialog.show(getSupportFragmentManager(),dialog.getClass().getName());
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_delete:
                deleteDiary();
                break;
            case R.id.iv_ed:
                pvCustomTime.show(); //弹出自定义时间选择器
                String timeStr=null;
                if (time==null){
                    timeStr= bean.getDirayCreateTime();
                }else {
                    timeStr=time;
                }

                break;
        }
    }
    //删除日记
    private void deleteDiary() {
        HttpUtils2.appdeleteDiary1(MyApplication.user.getLoginToken(), bean.getDiaryId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    if (new JSONObject(response.body()).getInt("code")==100){
                        ToastUtil.shortToast(getActivity(),"删除成功");
                        setResult(100);
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

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    TimePickerView pvCustomTime;
    String time;
    private void initCustomTimePicker() {

        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                String tempStr = getTime(date).substring(0, 10);
                time= getTime(date);
                tv_date.setText(tempStr);
                setDirtyTime();
            }
        })
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentTextSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.animGravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{ true, true, true,false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();

    }
    //修改时间发送请求
    private void setDirtyTime() {
        HttpUtils2.appUpdateDiary(MyApplication.user.getLoginToken(), time, bean.getDiaryId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    if (new JSONObject(response.body()).getInt("code")==100){
                        ToastUtil.shortToast(getActivity(),"修改成功");

                    }else {
                        ToastUtil.shortToast(getActivity(),"修改失败");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
