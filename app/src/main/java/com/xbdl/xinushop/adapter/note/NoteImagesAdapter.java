package com.xbdl.xinushop.adapter.note;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.bean.Images;
import com.xbdl.xinushop.utils.AppPhoneMgr;

public class NoteImagesAdapter extends BaseQuickAdapter<Images, BaseViewHolder> {
    int position;

    public NoteImagesAdapter(int position) {
        super(R.layout.item_noteimagelayout);
        this.position = position;
    }


    @Override
    protected void convert(BaseViewHolder helper, Images item) {
        AppCompatImageView iv = helper.getView(R.id.iv_image);
        int width = AppPhoneMgr.getInstance().getPhoneWidth(iv.getContext());
        LinearLayout.LayoutParams params = null;
        if (position == 1) {
            params = new LinearLayout.LayoutParams(width, width * 2 / 3);
        } else if (position == 2) {
            params = new LinearLayout.LayoutParams(width / 2, width * 1 / 2);
        } else {
            params = new LinearLayout.LayoutParams(width / 2, width * 1 / 3);
        }
        iv.setLayoutParams(params);
        Glide.with(iv.getContext()).load(item.getImage()).into(iv);

        helper.setText(R.id.tv_imagetitle,item.getImagetitle())
                .setText(R.id.tv_imagetime,item.getImagecreatetime());
    }
}
