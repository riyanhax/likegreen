package com.pywl.likegreen.adapter;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pywl.likegreen.R;
import com.pywl.likegreen.bean.Images;
import com.pywl.likegreen.bean.ShareLifeListBean;

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
