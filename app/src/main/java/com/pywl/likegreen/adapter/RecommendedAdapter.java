package com.pywl.likegreen.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pywl.likegreen.R;

import java.util.ArrayList;


public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    private ArrayList<String> mItemList;
    private ViewHolder mCurViewHolder;
    private DisplayImageOptions mDisplayImageOptions;

    public RecommendedAdapter(ArrayList<String> arrayList) {
        mItemList = arrayList;
        mDisplayImageOptions = new DisplayImageOptions.Builder()
                /*.showImageOnLoading(R.drawable.defualt_bg)            //加载图片时的图片
                .showImageForEmptyUri(R.drawable.defualt_bg)         //没有图片资源时的默认图片
                .showImageOnFail(R.drawable.defualt_bg)  */            //加载失败时的图片
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .considerExifParams(true)                          //启用EXIF和JPEG图像格式
                .build();
    }

   public class ViewHolder extends RecyclerView.ViewHolder {
        PLVideoTextureView videoView;
        String videoPath;
        View holderRootView;
       View topView;
       View pausePlayImage;
        public ViewHolder(View itemView) {
            super(itemView);
            holderRootView = itemView;
            videoView = (PLVideoTextureView)itemView.findViewById(R.id.video_texture_view);
            videoView.setAVOptions(createAVOptions());
            videoView.setDisplayAspectRatio(PLVideoTextureView.ASPECT_RATIO_PAVED_PARENT);
            View loadingView = itemView.findViewById(R.id.loading_view);
            videoView.setBufferingIndicator(loadingView);
            topView = itemView.findViewById(R.id.top_view);
            pausePlayImage = itemView.findViewById(R.id.image_pause_play);
            topView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (videoView.isPlaying()) {
                        videoView.pause();
                        pausePlayImage.setVisibility(View.VISIBLE);
                    } else {
                        videoView.start();
                        pausePlayImage.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_recommended, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String videoItem = mItemList.get(position);
        holder.videoPath = videoItem;
        holder.holderRootView.setTag(position);

        holder.videoView.setLooping(true);
    }

    @Override
    public int getItemCount() {
        int size = mItemList.size();
        return mItemList.size();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        mCurViewHolder = holder;
        holder.pausePlayImage.setVisibility(View.GONE);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        holder.videoView.stopPlayback();

    }

    public void setCurViewHolder(ViewHolder viewHolder) {
        mCurViewHolder = viewHolder;
    }

    public void startCurVideoView() {
        if (mCurViewHolder != null && !mCurViewHolder.videoView.isPlaying()) {
            mCurViewHolder.videoView.setVideoPath(mCurViewHolder.videoPath);
            mCurViewHolder.videoView.start();
            mCurViewHolder.pausePlayImage.setVisibility(View.GONE);
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

        }
    }
    public  AVOptions createAVOptions() {
        AVOptions options = new AVOptions();
        // the unit of timeout is ms
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 0);
        // 1 -> hw codec enable, 0 -> disable [recommended]
        options.setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_SW_DECODE);
        options.setInteger(AVOptions.KEY_PREFER_FORMAT, AVOptions.PREFER_FORMAT_MP4);
        boolean disableLog = false;
        options.setInteger(AVOptions.KEY_LOG_LEVEL, disableLog ? 5 : 0);
        return options;
    }

}