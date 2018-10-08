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
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ReCommentAdapter;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.bean.ReCommentListBean;
import com.xbdl.xinushop.utils.AppPhoneMgr;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论分类 1、种植日记 2、分享生活 3、参与话题 4、精选 5、攻略
 */
public class RecommentCommentDialogFragment extends MyDialogFragment implements View.OnClickListener {
    public RecommentCommentDialogFragment() {
        // Required empty public constructor
    }

    String token = "";
    String pdid = "";
    int notetype = 0;
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
    public static RecommentCommentDialogFragment newInstance(String pdid, int noteType) {
        RecommentCommentDialogFragment fragment = new RecommentCommentDialogFragment();
        Bundle args = new Bundle();
        args.putString("pdid", pdid);
        args.putInt("noteType", noteType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pdid = getArguments().getString("pdid", "");
            notetype = getArguments().getInt("noteType", 0);
        }
    }

    AppCompatTextView tvComment;
    RecyclerView recyclerView;
    ReCommentAdapter adapter = null;
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


        String userjson = SharedPreferencesUtil.getString(getActivity(), MyConstants.User, "");

        PersonBean personBean = new Gson().fromJson(userjson, PersonBean.class);
        token = personBean.getLoginToken();

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
        adapter = new ReCommentAdapter(getActivity());

        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.prossbar);

        etSend = view.findViewById(R.id.et_comment);
        tvSend = view.findViewById(R.id.tv_send);
        tvSend.setOnClickListener(this);
        //   initData();
        setListener();
        return view;
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
HttpUtils.readdcomment(token,
        pdid, etSend.getText().toString(),
        notetype+"", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
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
        HttpUtils.recommentlist(token, pdid, page + "", notetype + "", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
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
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.i("asdf", "onError" + response.body());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
