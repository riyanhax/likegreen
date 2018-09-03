package com.pywl.likegreen.adapter;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.pili.pldroid.player.widget.PLVideoView;
import com.pywl.likegreen.R;


/**
 * Created by theWind on 2018/8/10.
 * 私信adapter
 */

public class ShortVideoAdapter extends ListBaseAdapter<String> {
    private LayoutInflater mLayoutInflater;
    private DisplayImageOptions mDisplayImageOptions;
    private ViewHolder mCurViewHolder;
    public ShortVideoAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
       /* mDisplayImageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.defualt_bg)            //加载图片时的图片
                .showImageForEmptyUri(R.drawable.defualt_bg)         //没有图片资源时的默认图片
                .showImageOnFail(R.drawable.defualt_bg)              //加载失败时的图片
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .considerExifParams(true)                          //启用EXIF和JPEG图像格式
                .build();*/
    }

/*   @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        mCurViewHolder = (ViewHolder) holder;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        mCurViewHolder = (ViewHolder) holder;
        mCurViewHolder.videoView.stopPlayback();
    }*/

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_shortvideo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            ViewHolder viewHolder= (ViewHolder) holder;
            viewHolder.videoPath=mDataList.get(position);
            viewHolder.videoView.setVideoPath(mDataList.get(position));
            viewHolder.videoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
            viewHolder.videoView.setLooping(true);
        }

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private PLVideoView videoView;
        private String videoPath;
        public ViewHolder(View v) {
            super(v);
             videoView = (PLVideoView)v.findViewById(R.id.plv_videoview);
        }
    }


    public void setCurViewHolder(ViewHolder viewHolder) {
        mCurViewHolder = viewHolder;
    }

    public void startCurVideoView() {
        if (mCurViewHolder != null && !mCurViewHolder.videoView.isPlaying()) {
            mCurViewHolder.videoView.setVideoPath(mCurViewHolder.videoPath);
            mCurViewHolder.videoView.start();
          //  mCurViewHolder.pausePlayImage.setVisibility(View.GONE);
        }
    }

    public void pauseCurVideoView() {
        if (mCurViewHolder != null) {
            mCurViewHolder.videoView.pause();
        }
    }

    public void stopCurVideoView() {

        if (mCurViewHolder != null) {
            mCurViewHolder.videoView.stopPlayback();
         //   mCurViewHolder.coverImage.setVisibility(View.VISIBLE);
        }
    }
}
