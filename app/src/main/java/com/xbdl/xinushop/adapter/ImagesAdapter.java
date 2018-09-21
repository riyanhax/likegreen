package com.xbdl.xinushop.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.bean.Images;
import com.xbdl.xinushop.utils.AppPhoneMgr;

public class ImagesAdapter extends BaseQuickAdapter<Images, BaseViewHolder> {
    int position;

    public ImagesAdapter(int position) {
        super(R.layout.item_imagelayout);
        this.position = position;
    }
    @Override
    protected void convert(BaseViewHolder helper, Images item) {
        AppCompatImageView iv = helper.getView(R.id.iv_image);
        int width = AppPhoneMgr.getInstance().getPhoneWidth(iv.getContext());
        FrameLayout.LayoutParams params = null;
        if (position == 1) {
            params = new FrameLayout.LayoutParams(width, width * 2 / 3);
        } else if (position == 2) {
            params = new FrameLayout.LayoutParams(width / 2, width * 1 / 2);
        } else {
            params = new FrameLayout.LayoutParams(width / 2, width * 1 / 3);
        }
        iv.setLayoutParams(params);
        Glide.with(iv.getContext()).load(item.getImage()).into(iv);
    }
}
