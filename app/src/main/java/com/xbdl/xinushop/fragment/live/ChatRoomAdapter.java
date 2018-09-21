package com.xbdl.xinushop.fragment.live;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.adapter.LivingRoomAudienceSayAdapter;


import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatRoomAdapter extends ListBaseAdapter<Message> {
    private LayoutInflater mLayoutInflater;
    public ChatRoomAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(mLayoutInflater.inflate(R.layout.item_living_chatroom,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final MyHolder viewHolder = (MyHolder) holder;
        if (mDataList!=null){
            Message msg = mDataList.get(position);
            viewHolder.username.setText(msg.getFromID()+"：");
            String text = ((TextContent) msg.getContent()).getText();
            viewHolder.word.setText(text);
            //获取用户信息
            JMessageClient.getUserInfo(msg.getFromID(), new GetUserInfoCallback() {
                @Override
                public void gotResult(int i, String s, UserInfo userInfo) {
                    userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                        @Override
                        public void gotResult(int i, String s, Bitmap bitmap) {
                            Log.v("nihaoma",i+"mm"+s+"mm");
                            viewHolder.head.setImageBitmap(bitmap);
                        }
                    });
                }
            });
        }

    }
    public class MyHolder extends RecyclerView.ViewHolder{
        CircleImageView head;
        private TextView username,word;
        public MyHolder(View itemView) {
            super(itemView);
            head= (CircleImageView)itemView.findViewById(R.id.civ_living_head);
            username = (TextView)itemView.findViewById(R.id.tv_living_username);
            word = (TextView)itemView.findViewById(R.id.tv_living_word);
        }
    }
}
