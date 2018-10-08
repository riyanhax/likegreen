package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.baseadapter.BaseViewHolder;
import com.xbdl.xinushop.adapter.baseadapter.SimpleAdapter;
import com.xbdl.xinushop.bean.ReCommentListBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * item_recommentlistlayout  ReCommentListBean
 */
public class ReCommentAdapter extends SimpleAdapter<ReCommentListBean> {
    public ReCommentAdapter(Context context) {
        super(context, R.layout.item_recommentlistlayout);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, ReCommentListBean item) {
        AppCompatImageView ivUserheard = viewHoder.getAppCompatImageView(R.id.iv_usericon);
        AppCompatTextView tvUserName = viewHoder.getAppCompatTextView(R.id.tv_username);
        AppCompatTextView tvMessage = viewHoder.getAppCompatTextView(R.id.tv_message);
        AppCompatTextView tvTime = viewHoder.getAppCompatTextView(R.id.tv_time);

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher);
        Glide.with(ivUserheard.getContext()).load(item.getComment().getUserHeadPortrait()).apply(options).into(ivUserheard);

        tvUserName.setText(item.getComment().getUserName());
        tvMessage.setText(item.getComment().getContent());


        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");

        tvTime.setText(simpleFormatter.format(new Date(item.getComment().getTime())));


    }
}
