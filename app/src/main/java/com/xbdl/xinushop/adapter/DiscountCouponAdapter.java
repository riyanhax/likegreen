package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.baseadapter.BaseViewHolder;
import com.xbdl.xinushop.adapter.baseadapter.SimpleAdapter;
import com.xbdl.xinushop.bean.DiscountCouponBean;

/**
 * 我的优惠券 adapter
 */
public class DiscountCouponAdapter extends SimpleAdapter<DiscountCouponBean> {
    public DiscountCouponAdapter(Context context) {
        super(context, R.layout.item_discountcouponlayout);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, DiscountCouponBean item) {

    }
}
