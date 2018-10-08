package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;

/**
 * 添加优惠券
 */
public class AddDiscountCouponActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount_coupon);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_adddiscount).setOnClickListener(this);

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

                break;
        }
    }
}
