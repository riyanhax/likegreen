package com.xbdl.xinushop.dialogfragment;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ReCommentAdapter;
import com.xbdl.xinushop.bean.ReCommentListBean;
import com.xbdl.xinushop.utils.AppPhoneMgr;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.Judge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class RecommentCommentDialogFragment extends MyDialogFragment {
    public RecommentCommentDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0xffffff0));
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
    }

    /**
     */
    // TODO: Rename and change types and number of parameters
    public static RecommentCommentDialogFragment newInstance() {
        RecommentCommentDialogFragment fragment = new RecommentCommentDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    AppCompatTextView tvComment;
    RecyclerView recyclerView;
    ReCommentAdapter adapter = null;
    ProgressBar progressBar = null;

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

        tvComment = view.findViewById(R.id.tv_commentnumber);
        AppCompatImageView close = view.findViewById(R.id.iv_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ReCommentAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.prossbar);

        initData();

        return view;
    }

    private void initData() {
        HttpUtils.recommentlist(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    String data = jsonObject.getString("data");
                    List<ReCommentListBean> list = getCommentList(data);
                    if (list != null && list.size() > 0) {
                        adapter.addData(list);
                        tvComment.setText(list.size() + "条评论");
                    }

                } catch (JSONException e) {
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                progressBar.setVisibility(View.GONE);
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
    }
}