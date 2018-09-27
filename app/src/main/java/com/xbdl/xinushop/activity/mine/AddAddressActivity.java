package com.xbdl.xinushop.activity.mine;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.bean.CityJsonBean;
import com.xbdl.xinushop.utils.GetJsonDataUtil;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;

import org.json.JSONArray;

import java.util.ArrayList;


public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<CityJsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private EditText etname,etphone,etemscode,etaddress;
    private TextView region;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        decodeJson();
        initView();
        initData();
    }



    private void initView() {
        etname=(EditText) findViewById(R.id.etname);
        etphone=(EditText) findViewById(R.id.etphone);
        etemscode=(EditText) findViewById(R.id.etemscode);
        etaddress=(EditText) findViewById(R.id.etaddress);
        findViewById(R.id.Re_item4).setOnClickListener(this);
        region = (TextView)findViewById(R.id.region);
    }
    private String consignee,mobile,post,address,cname,province,city,district;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                consignee = etname.getText().toString().trim();
                mobile = etphone.getText().toString().trim();
                post = etemscode.getText().toString().trim();
                address = etaddress.getText().toString().trim();
                if(consignee==null||"".equals(consignee)){
                    ToastUtil.shortToast(this,"收货人姓名为空，请填写");
                    return;
                }
                if(mobile==null||"".equals(mobile)){
                    ToastUtil.shortToast(this,"收货人手机号码为空，请填写");
                    return;
                }
                if(post==null||"".equals(post)){
                    ToastUtil.shortToast(this,"收货邮编为空，请填写");
                    return;
                }
                if(cname==null||"".equals(cname)){
                    ToastUtil.shortToast(this,"收货市区为空，请填写");
                    return;
                }
                if(address==null||"".equals(address)){
                    ToastUtil.shortToast(this,"详细收货地址为空，请填写");
                    return;
                }
                commit();
                break;
        }
    }

    private void commit() {
       /* HttpUtils2.adduserAddress(MyApplication.user.getUserId(),consignee,mobile,);*/
    }

    private void initData() {

    }

    private void decodeJson() {
        if (thread == null) {//如果已创建就不再重新创建子线程了

            Toast.makeText(AddAddressActivity.this, "Begin Parse Data", Toast.LENGTH_SHORT).show();
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 子线程中解析省市区数据
                    initJsonData();
                }
            });
            thread.start();
        }
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<CityJsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }


    }
    public ArrayList<CityJsonBean> parseData(String result) {//Gson 解析
        ArrayList<CityJsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                CityJsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), CityJsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
private void showPickerView() {// 弹出选择器

    OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            //返回的分别是三个级别的选中位置
            String tx = options1Items.get(options1).getPickerViewText() +
                    options2Items.get(options1).get(options2) +
                    options3Items.get(options1).get(options2).get(options3);
            cname=new String(tx);
            String pickerViewText = options1Items.get(options1).getPickerViewText();
            String s = options2Items.get(options1).get(options2);
            String s1 = options3Items.get(options1).get(options2).get(options3);
            Log.v("nihaoma",pickerViewText+s+s1);
            region.setText(cname);
            Toast.makeText(AddAddressActivity.this, tx, Toast.LENGTH_SHORT).show();
        }
    })

            .setTitleText("城市选择")
            .setDividerColor(Color.BLACK)
            .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
            .setContentTextSize(20)
            .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
    pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
    pvOptions.show();
}


}
