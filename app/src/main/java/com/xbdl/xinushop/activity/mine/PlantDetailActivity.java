package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.bean.PlantDetailBean;
import com.xbdl.xinushop.bean.PlantingDiaryBean;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 植物详情
 */
public class PlantDetailActivity extends BaseActivity {
    PlantingDiaryBean plantingDiaryBean;
    String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);
        String userjson = SharedPreferencesUtil.getString(getActivity(), MyConstants.User, "");

        PersonBean personBean = new Gson().fromJson(userjson, PersonBean.class);
        token = personBean.getLoginToken();
        plantingDiaryBean = getIntent().getParcelableExtra("plantingDiaryBean");
        initView();
        initData();
    }

    AppCompatTextView tvUsername;
    AppCompatTextView tvDays;

    private void initView() {
        tvUsername = findViewById(R.id.tv_username);
        tvDays = findViewById(R.id.days);
    }

    private void initData() {
        HttpUtils.findPlantDetail(token, plantingDiaryBean.getP1().getPdId() + "", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());

                    int code = jsonObject.getInt("code");
                    String object = jsonObject.getString("object");
                    if (code == 100) {

                        List<PlantDetailBean> plantDetailBeans = getPlantDetails(object);
                        if (plantDetailBeans != null && plantDetailBeans.size() > 0) {
                            tvUsername.setText(plantDetailBeans.get(0).getP().getName());
                            String lasttime = plantDetailBeans.get(plantDetailBeans.size() - 1).getP().getPlantTime();
                            String firsttime = plantDetailBeans.get(0).getP().getPlantTime();
                            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
                            long saa = s.parse(lasttime).getTime();
                            long sa = s.parse(firsttime).getTime();
                            tvDays.setText("已养育" + (saa - sa) / (24 * 3600 * 1000) + "天");

                        }
                    }
                } catch (JSONException e) {
                } catch (ParseException e) {

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
                Log.i("asdf", "onFinish");
            }
        });
    }

    private List<PlantDetailBean> getPlantDetails(String object) {
        if (Judge.getBoolean_isNull(object)) {
            return null;
        }
        try {
            JSONArray array = new JSONArray(object);
            List<PlantDetailBean> datas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                PlantDetailBean plantDetailBean = new Gson().fromJson(array.getString(i), PlantDetailBean.class);
                datas.add(plantDetailBean);
            }
            return datas;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    protected Activity getActivity() {
        return PlantDetailActivity.this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag("findPlantDetail");
    }
}
