package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.baseadapter.BaseViewHolder;
import com.xbdl.xinushop.adapter.baseadapter.SimpleAdapter;
import com.xbdl.xinushop.bean.TopsBean;

/**
 * 话题 适配器
 */
public class TopsAdapter extends SimpleAdapter<TopsBean> {
    public TopsAdapter(Context context) {
        super(context, R.layout.item_topslayout);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, TopsBean item) {
        AppCompatImageView iv = viewHoder.getAppCompatImageView(R.id.iv_icon);
        AppCompatTextView tv = viewHoder.getAppCompatTextView(R.id.tv_message);
        AppCompatTextView tvdesc = viewHoder.getAppCompatTextView(R.id.tv_desc);
        Glide.with(context).load(item.getImg_path()).into(iv);
        tv.setText(item.getT_name());
        tvdesc.setText(item.getT_desc());

    }
}
