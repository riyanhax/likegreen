package com.pywl.likegreen.activity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.pywl.likegreen.R;
import com.pywl.likegreen.adapter.ShareLiveListAdapter;
import com.pywl.likegreen.bean.ShareLifeListBean;
import com.pywl.likegreen.dialogfragment.ShareLifeDialogFragment;
import com.pywl.likegreen.listener.OnShareLiveItemListener;
import com.pywl.likegreen.utils.HttpUtils;
import com.pywl.likegreen.utils.Judge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShareLiftActivity extends AppCompatActivity {
    private TextView title;
    private EditText search;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_lift);
        initView();
        initData();
    }

    ShareLiveListAdapter shareLiveListAdapter;

    private void initView() {
        title = findViewById(R.id.tv_title);
        search = findViewById(R.id.et_search);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShareLiftActivity.this));
        shareLiveListAdapter = new ShareLiveListAdapter();
        recyclerView.setAdapter(shareLiveListAdapter);
        shareLiveListAdapter.setOnShareLiveItemListener(new OnShareLiveItemListener() {
            @Override
            public void onShareLiveItemListener(int type, ShareLifeListBean shareLifeListBean) {
                ShareLifeDialogFragment shareLifeDialogFragment = ShareLifeDialogFragment.newInstance(
                        type, shareLifeListBean
                );
                shareLifeDialogFragment.show(getSupportFragmentManager(),
                        shareLifeDialogFragment.getClass().getName());
            }
        });
    }

    private void initData() {
        title.setText(getResources().getString(R.string.sharelift));
        search.setHint("搜索我的分享");
        HttpUtils.shareyourlife(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String datajsoon = response.body();
                try {
                    JSONObject jsonObject = new JSONObject(datajsoon);

                    String data = jsonObject.getString("data");
                    List<ShareLifeListBean> list = getShareLifeBeans(data);
                    if (list != null && list.size() > 0) {
                        shareLiveListAdapter.addData(list);
                    }
                } catch (JSONException e) {
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.i("", "");
            }
        });
    }

    /**
     * 解析json
     *
     * @param data
     * @return
     */
    private List<ShareLifeListBean> getShareLifeBeans(String data) {
        if (Judge.getBoolean_isNull(data)) {
            return null;
        }
        try {
            JSONArray array = new JSONArray(data);
            List<ShareLifeListBean> datas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                ShareLifeListBean shareLifeListBean = new Gson().fromJson(array.getString(i), ShareLifeListBean.class);
                datas.add(shareLifeListBean);
            }
            return datas;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag("shareyourlife");
    }
}
