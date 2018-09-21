package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbdl.xinushop.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by theWind on 2018/8/10.
 * 私信adapter
 */

public class MyDirectAdapter extends ListBaseAdapter<Conversation> {
    private LayoutInflater mLayoutInflater;

    public MyDirectAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_my_direct_msg, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            Conversation conversation = mDataList.get(position);
            final ViewHolder viewHolder = (ViewHolder) holder;
            //UserInfo userInfo= (UserInfo) conversation.getTargetInfo();
            //viewHolder.userName.setText(userInfo.getUserName());
            //获取用户信息
            JMessageClient.getUserInfo(conversation.getTargetId(), new GetUserInfoCallback() {
                @Override
                public void gotResult(int i, String s, UserInfo userInfo) {
                    viewHolder.userName.setText(userInfo.getUserName());
                    userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                        @Override
                        public void gotResult(int i, String s, Bitmap bitmap) {
                           if (bitmap!=null){
                               viewHolder.headIcon.setImageBitmap(bitmap);
                           }
                        }
                    });
                }
            });
            viewHolder.directMsg.setText(conversation.getLatestText());
            long timeStamp = conversation.getLastMsgDate();  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
            String sd = sdf.format(new Date(timeStamp));   // 时间戳转换成时间
            viewHolder.directData.setText(sd);
            Log.v("nihaoma",conversation.toString());
           // Log.v("nihaoma",userInfo.toString());
        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView directMsg;
        private TextView userName;
        private TextView directData;
        private CircleImageView headIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            directMsg = (TextView) itemView.findViewById(R.id.tv_mydirect_word_item);
            userName = (TextView) itemView.findViewById(R.id.tv_mydirect_username_item);
            headIcon = (CircleImageView) itemView.findViewById(R.id.iv_my_direct_headicon_item);
            directData = (TextView) itemView.findViewById(R.id.tv_mydirect_date_item);
        }
    }
}
