package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.baseadapter.BaseViewHolder;
import com.xbdl.xinushop.adapter.baseadapter.SimpleAdapter;
import com.xbdl.xinushop.bean.PlantingDiaryBean;
import com.xbdl.xinushop.utils.AppPhoneMgr;

public class PlantingDiaryMyGrardenAdapter extends SimpleAdapter<PlantingDiaryBean> {
    public PlantingDiaryMyGrardenAdapter(Context context) {
        super(context, R.layout.item_plantingdirayrmygradenlayout);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, PlantingDiaryBean item) {
        AppCompatImageView ivicon = viewHoder.getAppCompatImageView(R.id.iv_icon);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(AppPhoneMgr.getInstance().getPhoneWidth(context) / 2
                , AppPhoneMgr.getInstance().getPhoneWidth(context) / 3);
        ivicon.setLayoutParams(layoutParams);

        Glide.with(context).load(item.getP1().getImgpath()).into(ivicon);
    }
}
