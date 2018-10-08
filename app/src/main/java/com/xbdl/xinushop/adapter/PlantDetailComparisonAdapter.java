package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.baseadapter.BaseViewHolder;
import com.xbdl.xinushop.adapter.baseadapter.SimpleAdapter;
import com.xbdl.xinushop.bean.PlantDetailBean;
import com.xbdl.xinushop.utils.AppPhoneMgr;

/**
 * 种植日记对比适配器
 */
public class PlantDetailComparisonAdapter extends SimpleAdapter<PlantDetailBean.PBean> {
    public PlantDetailComparisonAdapter(Context context) {
        super(context, R.layout.item_plantdetailcomparisonlayout);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, PlantDetailBean.PBean item) {
        AppCompatImageView imageView = viewHoder.getAppCompatImageView(R.id.iv_icon);

        int width = AppPhoneMgr.getInstance().getPhoneWidth(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width / 2, width * 2 / 3);
        imageView.setLayoutParams(params);
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher);
        Glide.with(imageView.getContext()).load(item.getImgpath()).apply(options).into(imageView);

    }
}
