package com.xbdl.xinushop.activity.mine.order;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 申请售后
* */
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.umeng.commonsdk.debug.I;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.mine.order.SmallImgAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.utils.ToastUtil;

import java.util.ArrayList;

public class SaleAfterActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private int type;//退货1 //退货退款2  换货3
    private TextView tv_type,apply_type,tv_cash;//类型  申请类型   提示最多多少钱
    private View apply_choose,upload_picture,rl_cash;//申请类型选择  图片 金额栏
    private EditText reason,phone,et_cash;//申请原因  电话  输入金额
    private RecyclerView recyclerView;
    private ArrayList<ImageItem> front;
    private SmallImgAdapter smallImgAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_after);
        type = getIntent().getIntExtra("type", 0);
        initView();
        initData();
    }



    private void initView() {
        findViewById(R.id.iv_return).setOnClickListener(this);
        tv_type= findViewById(R.id.tv_type);
        apply_choose=findViewById(R.id.ll_apply_choose);
        apply_choose.setOnClickListener(this);
        apply_type= findViewById(R.id.tv_apply_type);
        reason= findViewById(R.id.et_apply_reason);
        phone= findViewById(R.id.et_phone_number);
        upload_picture= findViewById(R.id.rl_upload_picture1);
        upload_picture.setOnClickListener(this);
        recyclerView= findViewById(R.id.recyclerView);
        //申请退款才有
        rl_cash = findViewById(R.id.rl_cash);
        et_cash = findViewById(R.id.et_cash);
        tv_cash = findViewById(R.id.tv_cash);

    }

    private void initData() {
        if (type==1){
            //退货1
            tv_type.setText("退货");
            rl_cash.setVisibility(View.GONE);
        }else if (type==2){
            //退货退款
            tv_type.setText("退货退款");
            rl_cash.setVisibility(View.VISIBLE);

        }else {
            //换货3
            tv_type.setText("换货");
            rl_cash.setVisibility(View.GONE);
        }
        smallImgAdapter = new SmallImgAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(smallImgAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_return:
               finish();
                break;
            case R.id.ll_apply_choose:
                showSharePop(apply_choose);
                break;
            case R.id.rl_upload_picture1:
                ImagePicker.getInstance().setSelectLimit(3);
                Intent intentPerview = new Intent(this, ImageGridActivity.class);
                intentPerview.putExtra(ImageGridActivity.EXTRAS_IMAGES,front);
                startActivityForResult(intentPerview, 100);
                break;
            case R.id.tv_commit://提交
                if (type==2){
                   //退货退款
                 //   Double.parseDouble( et_cash.getText().toString());

                }
                if (TextUtils.isEmpty(phone.getText())||phone.length()!=11){
                    ToastUtil.shortToast(getActivity(),"请输入正确的手机号码");
                    return;
                }
                break;

            case R.id.tv_colse:
                popupWindow.dismiss();
                break;
        }
    }
    private PopupWindow popupWindow;
    private void showSharePop(View view) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_after_sale_reason, null);
        contentView.findViewById(R.id.tv_colse).setOnClickListener(this);
        RadioGroup radioGroup = contentView.findViewById(R.id.rg_reason);
        radioGroup.setOnCheckedChangeListener(this);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = (RadioButton)findViewById(group.getCheckedRadioButtonId());
        apply_type.setText(radioButton.getText().toString());
        switch (checkedId){
            case R.id.rb_1:
                popupWindow.dismiss();
                break;
            case R.id.rb_2:
                popupWindow.dismiss();
                break;
            case R.id.rb_3:
                popupWindow.dismiss();
                break;
            case R.id.rb_4:
                popupWindow.dismiss();
                break;
            case R.id.rb_5:
                popupWindow.dismiss();
                break;
            case R.id.rb_6:
                popupWindow.dismiss();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                front = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Log.v("nihaoma",front.toString());
                smallImgAdapter.setDataList(front);
                if (front.size()==3){
                    upload_picture.setVisibility(View.GONE);
                }
            }else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
