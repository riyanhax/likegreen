package com.pywl.likegreen.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.pywl.likegreen.R;
import com.pywl.likegreen.bean.VideoBean;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder.FRAME_OPTION;

/**
 * Created by theWind on 2018/8/10.
 * 我的视频adapter
 */

public class MyVideoAdapter extends ListBaseAdapter<VideoBean> {
    private LayoutInflater mLayoutInflater;

    public MyVideoAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_my_video, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            VideoBean videoBean = mDataList.get(position);

            ViewHolder viewHolder= (ViewHolder) holder;

            //设置封面
            RequestOptions requestOptions = RequestOptions.frameOf(1);
            requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST);
            requestOptions.transform(new BitmapTransformation() {
                @Override
                protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                    return toTransform;
                }
                @Override
                public void updateDiskCacheKey(MessageDigest messageDigest) {
                    try {
                        messageDigest.update((mContext.getPackageName() + "RotateTransform").getBytes("utf-8"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            Glide.with(mContext).load(videoBean.getVideoUrl()).apply(requestOptions).into(viewHolder.photoBg);
        }

    }


    private class ViewHolder extends RecyclerView.ViewHolder {

       private ImageView photoBg;
       private ImageView sartIcon;
       private TextView playNumber;

        public ViewHolder(View itemView) {
            super(itemView);
             photoBg = (ImageView)itemView.findViewById(R.id.iv_itme_video);
             sartIcon = (ImageView)itemView.findViewById(R.id.iv_itme_sart);
             playNumber = (TextView)itemView.findViewById(R.id.tv_play_number);
        }
    }
}
