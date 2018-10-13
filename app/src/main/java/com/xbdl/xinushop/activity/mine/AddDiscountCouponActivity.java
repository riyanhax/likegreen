package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;

/**
 * 添加优惠券
 */
public class AddDiscountCouponActivity extends BaseActivity implements View.OnClickListener {
    private EditText etCoupon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount_coupon);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_adddiscount).setOnClickListener(this);//完成
        etCoupon = findViewById(R.id.et_coupon);
    }

    @Override
    protected Activity getActivity() {
        return AddDiscountCouponActivity.this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_adddiscount:
                if (TextUtils.isEmpty(etCoupon.getText())){
                    ToastUtil.shortToast(getActivity(),"请输入有优惠券码");
                    return;
                }
                String trim = etCoupon.getText().toString().trim();
                getCoupon(trim);
                break;
        }
    }

    private void getCoupon(String trim) {
        HttpUtils2.collectionOfCouponsApi(MyApplication.user.getLoginToken(), MyApplication.user.getUserId(), Integer.parseInt(trim), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ToastUtil.shortToast(getActivity(),"领取成功");
                finish();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }
}
