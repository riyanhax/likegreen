package com.pywl.likegreen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pywl.likegreen.R;
import com.pywl.likegreen.base.ListBaseAdapter;
import com.pywl.likegreen.base.SuperViewHolder;
import com.pywl.likegreen.bean.MyVideoBean;
import com.pywl.likegreen.utils.BitmapUtil;

/**
 * Created by theWind on 2018/8/2.
 */

public class InitMyVideoAdatper extends ListBaseAdapter<MyVideoBean> {

    public InitMyVideoAdatper(Context context) {
        super(context);

    }

    @Override
    public int getLayoutId() {
        return R.layout.itme_video;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        MyVideoBean item=mDataList.get(position);
        ImageView iv_itme_video = holder.getView(R.id.iv_itme_video);
        ImageView iv_itme_sart = holder.getView(R.id.iv_itme_sart);
        TextView tv_itme_video = holder.getView(R.id.tv_itme_video);
        if (position==0){
            iv_itme_sart.setImageDrawable(mContext.getResources().getDrawable(R.drawable.order));
        }else {
            iv_itme_sart.setImageDrawable(mContext.getResources().getDrawable(R.drawable.order));
        }
        //设置播放数
        tv_itme_video.setText(item.getCount());
        //设置播放图片
        Glide.with(mContext).load(BitmapUtil.qiniuFixWith(item.getImgUrl(),200))
                .into(iv_itme_video)
                //.error(R.drawable.mine_image_default)
        ;
    }


}
