package com.xbdl.xinushop.adapter.note;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.note.NoteDetailActivity;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.adapter.MyDirectAdapter;
import com.xbdl.xinushop.bean.Images;
import com.xbdl.xinushop.bean.NoteHotBean;
import com.xbdl.xinushop.constant.UrlConstant;
import com.xbdl.xinushop.utils.AppPhoneMgr;
import com.xbdl.xinushop.utils.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;
import de.hdodenhof.circleimageview.CircleImageView;

public class NoteImagesAdapter extends ListBaseAdapter<NoteHotBean.ExtendBean.DiaryRootsBean.ListBean.DiarysBean> {
    private LayoutInflater mLayoutInflater;
    private int usrid,numFans,days,concernState;

    public NoteImagesAdapter(Context context,int userid,int numFans,int days,int concernState) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.usrid=userid;
        this.numFans=numFans;
        this.days=days;
        this.concernState=concernState;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_noteimagelayout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            ViewHolder viewHolder= (ViewHolder) holder;
            final NoteHotBean.ExtendBean.DiaryRootsBean.ListBean.DiarysBean item = mDataList.get(position);
            String time = item.getDirayCreateTime();
            String tempStr = time.substring(0, 10);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
            //获取当前时间
            Date date = new Date(System.currentTimeMillis());
            String timeNow = simpleDateFormat.format(date);
            String timeNow2 = timeNow.substring(0, 10);
            viewHolder.tv_imagetime.setText(tempStr);
           if (tempStr.equals(timeNow2)){
                viewHolder.rl_today.setVisibility(View.VISIBLE);
            }else {
               viewHolder.rl_today.setVisibility(View.GONE);
           }
            /*//设置今天昨天
            String s = TimeUtil.formatDisplayTime(time, null);
            viewHolder.tv_imagetime.setText(s);*/
            viewHolder.tv_imagetitle.setText("第"+item.getDiaryDay()+"天");

            int width = AppPhoneMgr.getInstance().getPhoneWidth(mContext);
            LinearLayout.LayoutParams params = null;
            int size = mDataList.size();
            if ( size== 1) {
                params = new LinearLayout.LayoutParams(width, width * 2 / 3);
            } else if (size == 2) {
                params = new LinearLayout.LayoutParams(width / 2, width * 1 / 2);
            } else {
                params = new LinearLayout.LayoutParams(width / 2, width * 1 / 3);
            }
            viewHolder.iv_image.setLayoutParams(params);
           String url= UrlConstant.baseimgUrl+item.getDirayIamge().get(0).getDiaryImageUrl();
           // Log.v("nihaoma","图片地址"+url);

            Glide.with(MyApplication.context).load(url).into(viewHolder.iv_image);
            viewHolder.iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(mContext, NoteDetailActivity.class);
                    intent.putExtra("diaryRootId",item.getDiaryRootId());
                    intent.putExtra("userId",usrid);
                    intent.putExtra("numFans",numFans);
                    intent.putExtra("days",days);
                    intent.putExtra("concernState",concernState);
                    mContext.startActivity(intent);
                }
            });
        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_image;
        TextView tv_imagetitle,tv_imagetime;
        View rl_today;
        public ViewHolder(View itemView) {
            super(itemView);
             iv_image = itemView.findViewById(R.id.iv_image);
            tv_imagetitle = itemView.findViewById(R.id.tv_imagetitle);
            tv_imagetime = itemView.findViewById(R.id.tv_imagetime);
            rl_today = itemView.findViewById(R.id.rl_today);
        }
    }
}
