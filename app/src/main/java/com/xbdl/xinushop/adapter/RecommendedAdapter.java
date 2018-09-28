package com.xbdl.xinushop.adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.PopupWindow;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoTextureView;

import com.xbdl.xinushop.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;


public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> implements View.OnClickListener {
    private ArrayList<String> mItemList;
    private ViewHolder mCurViewHolder;
    private DisplayImageOptions mDisplayImageOptions;
    private Activity mContext;
    public RecommendedAdapter(Activity comtext, ArrayList<String> arrayList) {
        mItemList = arrayList;
        mContext=comtext;
        mDisplayImageOptions = new DisplayImageOptions.Builder()
                /*.showImageOnLoading(R.drawable.defualt_bg)            //加载图片时的图片
                .showImageForEmptyUri(R.drawable.defualt_bg)         //没有图片资源时的默认图片
                .showImageOnFail(R.drawable.defualt_bg)  */            //加载失败时的图片
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .considerExifParams(true)                          //启用EXIF和JPEG图像格式
                .build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_recommend_share:
                showSharePop(view);
                break;
            case R.id.ll_share_weixin_circleo:
                new ShareAction(mContext)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//朋友圈
                        .withText("hello")//分享内容
                        .setCallback(umShareListener)//回调监听器
                        .share();
                popupWindow.dismiss();
                break;
            case R.id.ll_share_weixin:
                UMWeb web = new UMWeb("https://www.baidu.com");
                web.setTitle("This is music title");//标题
                // web.setThumb(thumb);  //缩略图
                web.setDescription("my description");//描述
                new ShareAction(mContext)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//微信
                        .withMedia(web)
                        .setCallback(umShareListener)//回调监听器
                        .share();

                popupWindow.dismiss();
                break;
            case R.id.ll_share_weibo:
                new ShareAction(mContext)
                        .setPlatform(SHARE_MEDIA.SINA)//微博
                        .withText("hello")//分享内容
                        .setCallback(umShareListener)//回调监听器
                        .share();
                popupWindow.dismiss();
            case R.id.ll_shareQQ:
                new ShareAction(mContext)
                        .setPlatform(SHARE_MEDIA.QQ)//微博
                        .withText("hello")//分享内容
                        .setCallback(umShareListener)//回调监听器
                        .share();
                popupWindow.dismiss();
                break;
            case R.id.ll_share_colse:
                popupWindow.dismiss();
                break;
            case R.id.ll_share_uninterested:
                popupWindow.dismiss();
                break;
            case R.id.iv_recommended_comment:
                myclick.showCommentPop(view);
                //showCommentPop(view);
                break;
        }
    }

//    private void showCommentPop(View view) {
//        View contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_comment, null);
//
//        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setTouchable(true);
//        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//
//    }

    private  UMShareListener umShareListener= new UMShareListener() {

       @Override
       public void onStart(SHARE_MEDIA share_media) {
           Log.v("nihaoma","1111111");
       }

       @Override
       public void onResult(SHARE_MEDIA share_media) {
           Log.v("nihaoma","2222222 "+share_media.getName());
       }

       @Override
       public void onError(SHARE_MEDIA share_media, Throwable throwable) {
           Log.v("nihaoma",throwable+"33333333 "+share_media.getName());
       }

       @Override
       public void onCancel(SHARE_MEDIA share_media) {
           Log.v("nihaoma","4444444");
       }
   };
    private PopupWindow popupWindow;
    private void showSharePop(View view) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_recommend_share, null);
        contentView.findViewById(R.id.ll_share_weixin_circleo).setOnClickListener(this);//朋友圈
        contentView.findViewById(R.id.ll_share_weixin).setOnClickListener(this);//朋友圈
        contentView.findViewById(R.id.ll_share_weibo).setOnClickListener(this);//微博
        contentView.findViewById(R.id.ll_shareQQ).setOnClickListener(this);//QQ
        contentView.findViewById(R.id.ll_share_colse).setOnClickListener(this);//关闭
        contentView.findViewById(R.id.ll_share_uninterested).setOnClickListener(this);//不感兴趣
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PLVideoTextureView videoView;
        String videoPath;
        View holderRootView;
        View topView;
        View pausePlayImage;
        View mShare,comment;
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
            mShare=itemView.findViewById(R.id.iv_recommend_share);//分享
            comment=itemView.findViewById(R.id.iv_recommended_comment);//评论
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
        holder.mShare.setOnClickListener(this);
        holder.comment.setOnClickListener(this);
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
            mCurViewHolder.pausePlayImage.setVisibility(View.VISIBLE);
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
    public interface MyViewClick{
        public void showCommentPop(View view);
        public void showSharePop();

    }
    private MyViewClick myclick;
    public void setMyViewClick(MyViewClick click){
        myclick=click;
    }
}