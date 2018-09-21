package com.xbdl.xinushop.adapter;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.bean.Images;
import com.xbdl.xinushop.bean.ShareLifeListBean;

public class ImagesAdapter extends BaseQuickAdapter<Images, BaseViewHolder> {
    public ImagesAdapter() {
        super(R.layout.item_imagelayout);
    }


    @Override
    protected void convert(BaseViewHolder helper, Images item) {
        AppCompatImageView iv=helper.getView(R.id.iv_image);
        Glide.with(iv.getContext()).load(item.getImage()).into(iv);
    }
}
