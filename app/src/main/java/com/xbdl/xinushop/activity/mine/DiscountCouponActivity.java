package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseActivity;

public class DiscountCouponActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_discount_coupon);

    }

    @Override
    protected Activity getActivity() {
        return DiscountCouponActivity.this;
    }
}
