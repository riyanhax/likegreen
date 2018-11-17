package com.xbdl.xinushop.adapter.mine;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.MyVideoBean;

import java.security.MessageDigest;

import static com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder.FRAME_OPTION;

/**
 * Created by theWind on 2018/8/10.
 * 我的视频adapter
 */

public class AuditVideoAdapter extends ListBaseAdapter<MyVideoBean> {
    private LayoutInflater mLayoutInflater;

    public AuditVideoAdapter(Context context) {
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
            MyVideoBean videoBean = mDataList.get(position);

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
            Glide.with(mContext).load(videoBean.getUrl()).apply(requestOptions).into(viewHolder.photoBg);
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
