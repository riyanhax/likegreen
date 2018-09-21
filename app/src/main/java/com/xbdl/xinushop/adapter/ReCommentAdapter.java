package com.xbdl.xinushop.adapter;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.bean.ReCommentListBean;

public class ReCommentAdapter extends BaseQuickAdapter<ReCommentListBean, BaseViewHolder> {
    public ReCommentAdapter() {
        super(R.layout.item_recommentlistlayout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReCommentListBean item) {
        helper.setText(R.id.tv_username, item.getUsername())
                .setText(R.id.tv_message, item.getCommentmessage())
                .setText(R.id.tv_time, item.getCreatetime());
        if (item.getIslike() == 1) {
            helper.setImageResource(R.id.iv_like, R.drawable.heart_zhibodianzan);
        } else {
            helper.setImageResource(R.id.iv_like, R.drawable.heart_pinglun);
            helper.setText(R.id.tv_likenumber,item.getLikenumber()+"");
        }

     AppCompatImageView imageView =helper.getView(R.id.iv_usericon);
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(imageView.getContext()).load(item.getUsericon()).apply(requestOptions).into(imageView);
    }
}
