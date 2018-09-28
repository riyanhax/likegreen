package com.xbdl.xinushop.dialogfragment;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.TopsAdapter;
import com.xbdl.xinushop.adapter.baseadapter.BaseAdapter;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.bean.TopsBean;
import com.xbdl.xinushop.listener.OnItemAllTopListener;
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
 * 查找所有话题
 */
public class FindAllSubjectDialogFragment extends MyDialogFragment {
    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0xffffff0));
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
    }

    public FindAllSubjectDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FindAllSubjectDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FindAllSubjectDialogFragment newInstance() {
        FindAllSubjectDialogFragment fragment = new FindAllSubjectDialogFragment();
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

    String token = "";
    TopsAdapter topsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_all_subject_dialog, container, false);
        getDialog().setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog不消失
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER_VERTICAL;
        window.setAttributes(wlp);
//        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.width = AppPhoneMgr.getInstance().getPhoneWidth(getActivity());
        getDialog().getWindow().setAttributes(lp);
        String userjson = SharedPreferencesUtil.getString(getActivity(), MyConstants.User, "");

        PersonBean personBean = new Gson().fromJson(userjson, PersonBean.class);
        token = personBean.getLoginToken();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        topsAdapter = new TopsAdapter(getActivity());
        recyclerView.setAdapter(topsAdapter);
        initData();

        return view;
    }

    /**
     * 找到所有话题
     */
    private void initData() {
        HttpUtils.findAllSubject(token, new StringCallback() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                showProgressDialog("");
            }

            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    String topic = jsonObject.getString("topic");
                    int code = jsonObject.getInt("code");
                    if (code == 100) {
                        final List<TopsBean> topsBeans = getTopsBean(topic);
                        if (topsBeans != null && topsBeans.size() > 0) {
                            topsAdapter.refreshData(topsBeans);
                            topsAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    if (onItemAllTopListener != null) {
                                        onItemAllTopListener.onItemAllTop(topsBeans.get(position));
                                        dismiss();
                                    }

                                }
                            });
                        }
                    }

                } catch (JSONException e) {
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.i("asdf", "onError " + response.body());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dismissProgressDialog();
            }
        });
    }

    public void setOnItemAllTopListener(OnItemAllTopListener onItemAllTopListener) {
        this.onItemAllTopListener = onItemAllTopListener;
    }

    OnItemAllTopListener onItemAllTopListener;


    private List<TopsBean> getTopsBean(String topic) {
        if (Judge.getBoolean_isNull(topic)) {
            return null;
        }
        try {
            JSONArray array = new JSONArray(topic);
            List<TopsBean> datas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                TopsBean topsBean = new Gson().fromJson(array.getString(i), TopsBean.class);
                datas.add(topsBean);
            }
            return datas;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OkGo.getInstance().cancelTag("findAllSubject");
    }
}
