package com.xbdl.xinushop.fragment.live;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;


import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.xbdl.xinushop.R;

import java.util.ArrayList;

import cn.jpush.im.android.api.model.Message;


public class HomeLiveAdapter1 extends RecyclerView.Adapter<HomeLiveAdapter1.ViewHolder> {
    private ArrayList<String> mItemList;
    private ViewHolder mCurViewHolder;
    private DisplayImageOptions mDisplayImageOptions;
    private Context  mContext;
    private ArrayList<Message> msgList;
    public HomeLiveAdapter1(Context mConText,ArrayList<String> arrayList,ArrayList<Message> messageList) {
        mItemList = arrayList;
        this.mContext=mConText;
        msgList=messageList;
        mDisplayImageOptions = new DisplayImageOptions.Builder()
                /*.showImageOnLoading(R.drawable.defualt_bg)            //加载图片时的图片
                .showImageForEmptyUri(R.drawable.defualt_bg)         //没有图片资源时的默认图片
                .showImageOnFail(R.drawable.defualt_bg)  */            //加载失败时的图片
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .considerExifParams(true)                          //启用EXIF和JPEG图像格式
                .build();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        PLVideoTextureView videoView;
        String videoPath;
        View holderRootView;
        LRecyclerView chatHead,chatRoom;//头像list
        public ViewHolder(View itemView) {
            super(itemView);
            holderRootView = itemView;
            videoView = (PLVideoTextureView) itemView.findViewById(R.id.video_texture_view);
            videoView.setAVOptions(createAVOptions());
            videoView.setDisplayAspectRatio(PLVideoTextureView.ASPECT_RATIO_PAVED_PARENT);
            chatHead= (LRecyclerView)itemView.findViewById(R.id.lr_chat_head);
            chatRoom= (LRecyclerView)itemView.findViewById(R.id.living_room_audience_say);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_home_live1, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String videoItem = mItemList.get(position);
        //视频的
        holder.videoPath = videoItem;
        holder.holderRootView.setTag(position);
        holder.videoView.setLooping(true);
        //聊天
        ChatRoomAdapter chatRoomAdapter = new ChatRoomAdapter(mContext);
        chatRoomAdapter.setDataList(msgList);
        holder.chatRoom.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        holder.chatRoom.setAdapter(new LRecyclerViewAdapter(chatRoomAdapter));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        mCurViewHolder = holder;

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
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);
        // 1 -> hw codec enable, 0 -> disable [recommended]
        options.setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_AUTO);
        options.setInteger(AVOptions.KEY_LOG_LEVEL, 0);
        return options;
    }

}