package com.pywl.likegreen.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pywl.likegreen.R;
import com.pywl.likegreen.bean.Images;
import com.pywl.likegreen.utils.AppPhoneMgr;

public class ImagesAdapter extends BaseQuickAdapter<Images, BaseViewHolder> {
    int position = 0;

    public ImagesAdapter(int position) {
        super(R.layout.item_imagelayout);
        this.position = position;
    }


    @Override
    protected void convert(BaseViewHolder helper, Images item) {
        AppCompatImageView iv = helper.getView(R.id.iv_image);
        FrameLayout.LayoutParams layoutParams=null;
       int width= AppPhoneMgr.getInstance().getPhoneWidth(iv.getContext());
        if (position==1)
        {
            layoutParams=new FrameLayout.LayoutParams(width,width*2/3);

        }else if (position==2)
        {
            layoutParams=new FrameLayout.LayoutParams(width/2,width*1/2);
        }else {
            layoutParams=new FrameLayout.LayoutParams(width/3,width/3);
        }
        iv.setLayoutParams(layoutParams);
        Glide.with(iv.getContext()).load(item.getImage()).into(iv);
    }
}
