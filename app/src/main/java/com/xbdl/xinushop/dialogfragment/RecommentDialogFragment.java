package com.xbdl.xinushop.dialogfragment;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ReCommentAdapter;
import com.xbdl.xinushop.adapter.VideoCommentAdapter;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.bean.ReCommentListBean;
import com.xbdl.xinushop.bean.VideoCommentBean;
import com.xbdl.xinushop.utils.AppPhoneMgr;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频评论
 */
public class RecommentDialogFragment extends MyDialogFragment implements View.OnClickListener {
    public RecommentDialogFragment() {
        // Required empty public constructor
    }

    String token = "";
    int pdid =0;//类型id
    int notetype = 0;//类型
    int toCommentId=0;//被评论的id
    MaterialRefreshLayout refreshLayout;

    public static final int lOAD_DADA = 1;
    public static final int UP_DADA = lOAD_DADA + 1;

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0xffffff0));
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
    }

    /**
     * @param noteType 评论分类 1、种植日记 2、分享生活 3、参与话题 4、精选 5、攻略
     */
    // TODO: Rename and change types and number of parameters
    public static RecommentDialogFragment newInstance(int pdid, int noteType,int toCommentId) {
        RecommentDialogFragment fragment = new RecommentDialogFragment();
        Bundle args = new Bundle();
        args.putInt("pdid", pdid);
        args.putInt("noteType", noteType);
        args.putInt("toCommentId", toCommentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pdid = getArguments().getInt("pdid", 0);
            notetype = getArguments().getInt("noteType", 0);
            toCommentId = getArguments().getInt("toCommentId", 0);
        }
    }

    AppCompatTextView tvComment;
    RecyclerView recyclerView;
    VideoCommentAdapter adapter = null;
    ProgressBar progressBar = null;
    AppCompatTextView tvSend = null;
    AppCompatEditText etSend = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recomment_comment_dialog, container, false);
        getDialog().setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);

        FrameLayout f = view.findViewById(R.id.fl_comment);
        int height = AppPhoneMgr.getInstance().getPhoneHeight(getActivity());
        int width = AppPhoneMgr.getInstance().getPhoneWidth(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height * 2 / 3);
        f.setLayoutParams(params);

        token = MyApplication.user.getLoginToken();

        tvComment = view.findViewById(R.id.tv_commentnumber);
        AppCompatImageView close = view.findViewById(R.id.iv_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        refreshLayout = view.findViewById(R.id.refresh);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new VideoCommentAdapter(getActivity());

        recyclerView.setAdapter(adapter);
        adapter.setDeleteClick(new VideoCommentAdapter.DeleteClick() {
            @Override
            public void myClick(int id) {
                //删除评论
                deleteComment(id);
            }
        });
        progressBar = view.findViewById(R.id.prossbar);

        etSend = view.findViewById(R.id.et_comment);
        tvSend = view.findViewById(R.id.tv_send);
        tvSend.setOnClickListener(this);
        //   initData();
        setListener();
        return view;
    }
    //删除评论
    private void deleteComment(int id) {
        HttpUtils2.appDeleteComments(id, MyApplication.user.getUserId(), MyApplication.user.getLoginToken(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    int code = jsonObject.getInt("code");
                    if (code==100){
                        getData(lOAD_DADA);
                        ToastUtil.shortToast(getActivity(),"删除成功");
                    }else {
                        ToastUtil.shortToast(getActivity(),"删除失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                ToastUtil.shortToast(getActivity(),"删除失败");
            }
        });
    }


    private List<ReCommentListBean> getCommentList(String data) {
        if (Judge.getBoolean_isNull(data)) {
            return null;
        }
        try {
            JSONArray array = new JSONArray(data);
            List<ReCommentListBean> datas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                ReCommentListBean reCommentListBean = new Gson().fromJson(array.getString(i), ReCommentListBean.class);
                datas.add(reCommentListBean);
            }
            return datas;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag("recommentlist");
        OkGo.getInstance().cancelTag("readdcomment");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                if (Judge.getBoolean_isNull(etSend.getText().toString())) {
                    Toast.makeText(getActivity(), "请填写内容", Toast.LENGTH_LONG).show();
                    return;
                }
HttpUtils2.appAddComments(notetype,pdid,MyApplication.user.getUserId(),toCommentId,etSend.getText().toString(),MyApplication.user.getLoginToken(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.i("nihaoma", "发表评论onSuccess" + response.body());
                try {
                    JSONObject jsonObject=new JSONObject(response.body());

                    int code=jsonObject.getInt("code");
                    if (code!=100)
                    {
                        Toast.makeText(getActivity(),"发布失败",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getActivity(),"发布成功",Toast.LENGTH_LONG).show();
                        etSend.setText("");
                        getData(lOAD_DADA);
                        progressBar.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    Toast.makeText(getActivity(),"发布失败",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(getActivity(),"发布失败",Toast.LENGTH_LONG).show();
            }
        });


                break;
        }
    }

    int page = 1;

    private void setListener() {
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                //下拉刷新...
                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        page = 1;
                        getData(lOAD_DADA);
                        refreshLayout.setLoadMore(true);
                    }
                }, 500);

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //上拉加载更多...
                        /**
                         * 完成加载数据后，调用此方法，要不然刷新的效果不会消失
                         */
                        page++;
                        getData(UP_DADA);

                    }
                }, 500);

            }
        });
        refreshLayout.autoRefresh();

    }

    private void getData(final int upOrLoad) {
        if (upOrLoad == lOAD_DADA) { // 下拉
            page = 1;
        } else if (upOrLoad == UP_DADA) { // 上拉
            page += 1;
        }
        HttpUtils2.appGetComments(notetype,pdid,MyApplication.user.getLoginToken(), MyApplication.user.getUserId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.i("nihaoma", "视频列表onSuccess" + response.body());
                Gson gson = new Gson();
                VideoCommentBean videoCommentBean = gson.fromJson(response.body(), VideoCommentBean.class);
                if (videoCommentBean.getCode()==100){
                    VideoCommentBean.ExtendBean extend = videoCommentBean.getExtend();
                    VideoCommentBean.ExtendBean.PageBean page = extend.getPage();
                    List<VideoCommentBean.ExtendBean.PageBean.ListBean> beans = page.getList();
                    if (beans!=null&&beans.size()>0)
                    {
                        if (upOrLoad == lOAD_DADA) { // 下拉
                            adapter.refreshData(beans);
                        } else if (upOrLoad == UP_DADA) { // 上拉
                            adapter.loadMoreData(beans);
                        }
                        tvComment.setText(beans.size()+"条评论");
                    }
                }else {
                    ToastUtil.shortToast(getActivity(),getResources().getString(R.string.errrotip));
                }
            /*    try {
                    JSONObject jsonObject = new JSONObject(response.body());

                    int code = jsonObject.getInt("code");
                    String object = jsonObject.getString("object");

                    if (code == 100) {
                        List<ReCommentListBean> reCommentListBeans = getCommentList(object);
                     if (reCommentListBeans!=null&&reCommentListBeans.size()>0)
                     {
                         if (upOrLoad == lOAD_DADA) { // 下拉
                             adapter.refreshData(reCommentListBeans);
                         } else if (upOrLoad == UP_DADA) { // 上拉
                             adapter.loadMoreData(reCommentListBeans);
                         }
                         tvComment.setText(reCommentListBeans.size()+"条评论");
                     }
                    }

                } catch (JSONException e) {
                }*/
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                ToastUtil.shortToast(getActivity(),getResources().getString(R.string.errrotip));
                Log.i("nihaoma", "onError" + response.body());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
