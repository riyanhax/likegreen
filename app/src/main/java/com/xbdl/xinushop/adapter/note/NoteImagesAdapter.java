package com.xbdl.xinushop.adapter.note;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.bean.Images;
import com.xbdl.xinushop.bean.NoteHotBean;
import com.xbdl.xinushop.utils.AppPhoneMgr;

public class NoteImagesAdapter extends BaseQuickAdapter<NoteHotBean.ExtendBean.DiaryRootsBean.ListBean, BaseViewHolder> {
    int position;

    public NoteImagesAdapter(int position) {
        super(R.layout.item_noteimagelayout);
        this.position = position;
    }


    @Override
    protected void convert(BaseViewHolder helper, NoteHotBean.ExtendBean.DiaryRootsBean.ListBean item) {
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

        Glide.with(iv.getContext()).load(item.getDiarys().get(0).getDirayIamge().get(0)).into(iv);
        String tempStr = item.getDiarys().get(0).getDirayCreateTime().substring(item.getDiarys().get(0).getDirayCreateTime().indexOf(0) + 1, item.getDiarys().get(0).getDirayCreateTime().lastIndexOf(10));
        Log.v("nihoama",tempStr);
        helper.setText(R.id.tv_imagetitle,item.getDiarys().get(0).getDiaryDay())
                .setText(R.id.tv_imagetime,tempStr);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
