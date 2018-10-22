package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.baseadapter.BaseViewHolder;
import com.xbdl.xinushop.adapter.baseadapter.SimpleAdapter;
import com.xbdl.xinushop.bean.ReCommentListBean;
import com.xbdl.xinushop.bean.VideoCommentBean;
import com.xbdl.xinushop.utils.HttpUtils2;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 视频评论适配器
 */
public class VideoCommentAdapter extends SimpleAdapter<VideoCommentBean.ExtendBean.PageBean.ListBean> {
    public VideoCommentAdapter(Context context) {
        super(context, R.layout.item_recommentlistlayout);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, final VideoCommentBean.ExtendBean.PageBean.ListBean item) {
        ImageView ivUserheard = viewHoder.getImageView(R.id.iv_usericon);
        AppCompatTextView tvUserName = viewHoder.getAppCompatTextView(R.id.tv_username);
        AppCompatTextView tvMessage = viewHoder.getAppCompatTextView(R.id.tv_message);
        AppCompatTextView tvTime = viewHoder.getAppCompatTextView(R.id.tv_time);
        ImageView deleteView = viewHoder.getImageView(R.id.iv_delete);//删除按钮
        //设置圆形图片
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(ivUserheard.getContext()).load(item.getUser().getHeadPortrait()).apply(requestOptions).into(ivUserheard);

        tvUserName.setText(item.getUser().getUserName());
        tvMessage.setText(item.getCommentsContent());


        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");

        //tvTime.setText(simpleFormatter.format(item.getCommentsCreatedTime()));
        tvTime.setText(item.getCommentsCreatedTime());
        if (MyApplication.user.getUserId()==item.getUser().getUserId()){
            //自己评论的
            deleteView.setVisibility(View.VISIBLE);
        }else {
            deleteView.setVisibility(View.GONE);
        }
        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               click.myClick(item.getCommentsId());
            }
        });
    }
    private DeleteClick click;
    public void setDeleteClick(DeleteClick click){
        this.click=click;
    }
    public interface DeleteClick{
        void myClick(int id);
    }
}
