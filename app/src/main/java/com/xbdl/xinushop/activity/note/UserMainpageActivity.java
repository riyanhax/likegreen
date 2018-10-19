package com.xbdl.xinushop.activity.note;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.util.RecyclerViewUtils;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.note.GardenViewAdapter;
import com.xbdl.xinushop.adapter.note.LifelogAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyFansBean;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/*
* 用户日记
* */
public class UserMainpageActivity extends BaseActivity implements View.OnClickListener {
    private CircleImageView ivhead;
    private RelativeLayout item1, item2;
    private TextView tlog, tlife, tname, tsign, tachive, tv_emptyText;
    private View vline1, vline2;
    private LRecyclerView recylist;
    private GardenViewAdapter gardenAdapter;
    private LifelogAdapter lifelogAdapter;
    private ArrayList<String> potData;
    private ArrayList<String> lifelist = new ArrayList<>();
    private int type = 0;
    //要显示的用户的id
    private int sid;
    //是否关注
    private boolean isAttention;

    private boolean refreshAuto = false;
    private MyFansBean.PdBean.ListBean bean;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_mainpage);
        bean= (MyFansBean.PdBean.ListBean) getIntent().getSerializableExtra("data");
        potData=new ArrayList<String>();

        findViews();
        initData();
    }


    private void findViews() {

        recylist = ((LRecyclerView) findViewById(R.id.recyclist));

        recylist.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

            }
        });
        gardenAdapter=new GardenViewAdapter(potData,getActivity());
        lifelogAdapter=new LifelogAdapter(potData,getActivity());
        lRecyclerViewAdapter= new LRecyclerViewAdapter(gardenAdapter);
        recylist.setAdapter(lRecyclerViewAdapter);
        recylist.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));

        ivhead = ((CircleImageView) findViewById(R.id.ivhead));
        tname = ((TextView) findViewById(R.id.username));
        tsign = ((TextView) findViewById(R.id.usersign));
        tachive = ((TextView) findViewById(R.id.userachive));

        item1 = ((RelativeLayout) findViewById(R.id.item1));
        item2 = ((RelativeLayout) findViewById(R.id.item2));
        tlog = ((TextView) findViewById(R.id.tvlog));
        tlife = ((TextView) findViewById(R.id.tvlife));
        tv_emptyText = ((TextView) findViewById(R.id.tv_emptyText));
        vline1 = findViewById(R.id.vline1);
        vline2 =findViewById(R.id.vline2);
        tv_emptyText.setText("要养成写日记的好习惯哦");
        //标题
        title = findViewById(R.id.tv_title);
//解决数据加载不完的问题
        recylist.setNestedScrollingEnabled(false);
        recylist.setHasFixedSize(true);
//解决数据加载完成后, 没有停留在顶部的问题
        recylist.setFocusable(false);

    }


    private void initData() {
        title.setText(bean.getUser_name()+"的主页");
        if (!TextUtils.isEmpty(bean.getHead_portrait())){
            Glide.with(getActivity()).load(bean.getHead_portrait()).into(ivhead);
        }
        tname.setText(bean.getUser_name());
        if (!TextUtils.isEmpty(bean.getSignature())){
            tsign.setText(bean.getSignature());
        }else {
            tsign.setText(getResources().getString(R.string.textsign));
        }

        findViewById(R.id.iv_return).setOnClickListener(this);
        findViewById(R.id.tv_focuse).setOnClickListener(this);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);

    }


    private void initOp() {
        potData = new ArrayList<>();
//        tvtoptitle.setText(res.getString(R.string.spagemainpage));
/*        tvtoptitle.setText("");
        btnright.setVisibility(View.VISIBLE);
        gardenAdapter = new GardenViewAdapter(potData, this);
        lifelogAdapter = new LifelogAdapter(lifelist, this);
        gardenAdapter.setOnItemClickListener(this);
        lifelogAdapter.setOnItemClickListener(this);
        recylist.setAdapter(gardenAdapter);
        sid = getIntent().getIntExtra("ID", userId);
//        attentionflag = getIntent().getIntExtra("FLAG", 2);
        isAttention = getIntent().getBooleanExtra("isAttention", false);
        btnright.setVisibility(View.GONE);*/
        showFocusInfo();


    }

    /**
     * 显示关注按钮的信息
     */
    private void showFocusInfo() {
        if (isAttention) {
         /*   btnright.setText("已关注");
            btnright.setBackgroundColor(getResources().getColor(R.color.white));
            btnright.setTextColor(getResources().getColor(R.color.cblack));*/
        } else {
           /* btnright.setText("+ 关注");
            btnright.setBackgroundResource(R.drawable.bg_v2btn);
            btnright.setTextColor(getResources().getColor(R.color.white));*/
        }
    }
    LRecyclerViewAdapter lRecyclerViewAdapter;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.tv_focuse:
                //关注和取消关注
                focusOrCancelOthers();
                break;

            case R.id.item1:
                //changeItem(0);
                recylist.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
                gardenAdapter=new GardenViewAdapter(potData,getActivity());
                lRecyclerViewAdapter= new LRecyclerViewAdapter(gardenAdapter);
                recylist.setAdapter(lRecyclerViewAdapter);
                break;


            case R.id.item2:
                //changeItem(1);
                recylist.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
                lifelogAdapter=new LifelogAdapter(lifelist,getActivity());
                lRecyclerViewAdapter = new LRecyclerViewAdapter(lifelogAdapter);
                recylist.setAdapter(lRecyclerViewAdapter);


                break;

            default:
                break;
        }
    }

    /**
     * 关注和取消关注他人
     */
    private void focusOrCancelOthers() {
       /* params.clear();
        params.put("mid", userId);
        params.put("uid", sid);

        if (isAttention) {

            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("是否取消关注？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            closeDialog();
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //取消关注
                            httpPost(URIConfig.CANCEL_FOCUS, Config.HandKey.CANCEL_OTHERS.ordinal());
                        }
                    })
                    .create()
                    .show();
        } else {
            //关注
            httpPost(URIConfig.FOCUS_OTHERS, Config.HandKey.FOCUS_OTHERS.ordinal());
        }*/
    }


  /*  protected void handleMsg(Message msg) {
        if (msg.what == Config.HandKey.FOCUS_OTHERS.ordinal()
                || msg.what == Config.HandKey.CANCEL_OTHERS.ordinal()) {

            isAttention = !isAttention;

            showFocusInfo();
            setResult(RESULT_OK);

        } else {
            super.handleMsg(msg);
        }
        closeDialog();
    }*/

  /*  private void changeItem(int type) {
        this.type = type;

        tv_emptyText.setText(type == 0 ? "要养成写日记的好习惯哦" : "动动手分享一下你的种植生活！");

        if (type == 1) {
            //关注

            if (gardenAdapter != null) {
                tv_emptyText.setVisibility(gardenAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
            }

            tlife.setTextColor(getResources().getColor(R.color.black));
            vline2.setBackgroundColor(getResources().getColor(R.color.colorfocuse));
            tlog.setTextColor(getResources().getColor(R.color.cblack));
            vline1.setBackgroundColor(Color.TRANSPARENT);

        } else {
            if (lifelogAdapter != null) {
                tv_emptyText.setVisibility(lifelogAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
            }

            tlog.setTextColor(getResources().getColor(R.color.black));
            vline1.setBackgroundColor(getResources().getColor(R.color.colorfocuse));
            tlife.setTextColor(getResources().getColor(R.color.cblack));
            vline2.setBackgroundColor(Color.TRANSPARENT);
        }
    }*/

    private int gardenpage = 1, lifelogpage = 1;
    private HashMap<String, Object> userMap;


    private void updateInfo() {

   /*     isAttention = userMap.get("isLike").toString().equals("1");
        showFocusInfo();
        btnright.setVisibility(View.VISIBLE);

        if (userId == sid) {
            //是自己的，就隐藏头部信息
            ivhead.setVisibility(View.GONE);
            tname.setVisibility(View.GONE);
            tsign.setVisibility(View.GONE);
            tachive.setVisibility(View.GONE);

            View split = findViewById(R.id.split);
            if (split != null) {
                split.setVisibility(View.GONE);
            }

            tvtoptitle.setText("我" + res.getString(R.string.spagemainpage));
            btnright.setVisibility(View.GONE);
            return;
        }

        AsynImageLoader.showImageAsyn(this, ivhead, userMap.get("avatar").toString(), R.mipmap.head_blank);
        String uname = userMap.get("username").toString();
        tvtoptitle.setText(uname + res.getString(R.string.spagemainpage));
        tname.setText(uname);

        if (userMap.get("signature") == null || userMap.get("signature").toString().equalsIgnoreCase("null")) {
            tsign.setText("简介：暂无");
        } else {
            tsign.setText("简介：" + userMap.get("signature").toString());
        }

        tachive.setText("多肉 " + userMap.get("plantCount") + " 棵      关注 " + userMap.get("attentionCount") + " 人");

        ivhead.setVisibility(View.VISIBLE);
        tname.setVisibility(View.VISIBLE);
        tsign.setVisibility(View.VISIBLE);
        tachive.setVisibility(View.VISIBLE);
        findViewById(R.id.split).setVisibility(View.VISIBLE);*/
    }

/*
    @Override
    protected void loadData() {
        loadWithDialog(true);
    }

    private void loadWithDialog(boolean withDialog) {
        initParams();
        params.put("mid", userId);
        params.put("uid", sid);
        if (withDialog) {
            showDialog("");
        }
        if (type == 0) {
            params.put("page", gardenpage);
            httpGet(URIConfig.MyMianPage);
        } else {
            params.put("page", lifelogpage);
            httpGet(URIConfig.MyLifeLog);
        }
    }*/

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (refreshAuto) {

            refreshAuto = false;
        }
    }

    protected void showData(String httpData) {

        /*if (type == 0) {
            if (gardenpage == 1) {
                userMap = JsonAnalicy.analicyUinfo(httpData);
                updateInfo();
            }
            ArrayList<Pot> list = JsonAnalicy.analicyGardenlist(httpData);

            if (gardenpage == 1) {
                //第一个页面直接刷新
                potData.clear();
            } else {
                if (potData.size() > 10 * (gardenpage - 1)) {
                    list = (ArrayList<Pot>) list.subList(0, 10 * (gardenpage - 1) - 1);
                }
            }

            potData.addAll(list);
            closeDialog();
            gardenAdapter.notifyDataSetChanged();

            if (potData.isEmpty()) {
                tv_emptyText.setVisibility(View.VISIBLE);
            } else {
                tv_emptyText.setVisibility(View.GONE);
            }

        } else {
            ArrayList<Lifelog> dlist = JsonAnalicy.analicyLifelist(httpData);

            if (lifelogpage == 1) {
                //第一个页面直接刷新
                lifelist.clear();
            } else {
                if (lifelist.size() > 10 * (lifelogpage - 1)) {
                    lifelist = (ArrayList<Lifelog>) lifelist.subList(0, 10 * (lifelogpage - 1) - 1);
                }
            }

            lifelist.addAll(dlist);
            closeDialog();
            lifelogAdapter.notifyDataSetChanged();

            if (lifelist.isEmpty()) {
                tv_emptyText.setVisibility(View.VISIBLE);
            } else {
                tv_emptyText.setVisibility(View.GONE);
            }
        }*/
    }



/*    public void onLoadMore() {
        params.clear();
        params.put("mid", userId);
        params.put("uid", sid);
        if (type == 0) {
            gardenpage++;
            params.put("page", gardenpage);
            httpGet(URIConfig.MyMianPage);
        } else {
            lifelogpage++;
            params.put("page", lifelogpage);
            httpGet(URIConfig.MyLifeLog);
        }
    }*/

    public static final int REQUEST_MODIFY = 0x126;

/*    @Override
    public void onItemClick(Pot pot) {
        LogDebug.Dlog("UserMainpageAct", ",pot=" + pot);
        Intent intef = new Intent();
        intef.setClass(this, BrowserActivity.class);
        intef.putExtra("URL", "file:///android_asset/zplant_detail.html?id=" + pot.getId());
        startActivityForResult(intef, REQUEST_MODIFY);
    }

    @Override
    public void onLifelogItemClick(Lifelog lifelog) {
        refreshAuto = true;
        LogDebug.Dlog("UserMainpageAct", "==onLifelogitemclick=" + ",pot=" + lifelog);
        Intent intent = new Intent(this, BrowserActivity.class);
        intent.putExtra("URL", "file:///android_asset/forum_post_detail.html?post_id=" + lifelog.getPost_id());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_MODIFY || resultCode == RESULT_OK) {
            loadWithDialog(false);
        }
    }*/
}
