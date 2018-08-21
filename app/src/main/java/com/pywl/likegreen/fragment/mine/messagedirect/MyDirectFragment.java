package com.pywl.likegreen.fragment.mine.messagedirect;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import com.pywl.likegreen.R;

import com.pywl.likegreen.activity.ChatActivity;
import com.pywl.likegreen.adapter.MyDirectAdapter;
import com.pywl.likegreen.chat.JGBaseFragment;


import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.event.ConversationRefreshEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.MessageReceiptStatusChangeEvent;
import cn.jpush.im.android.api.event.MessageRetractEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
/*
* 私信页
* */

public class MyDirectFragment extends Fragment {
    private static final int REFRESH_CONVERSATION_LIST = 0x3000;
    private static final int DISMISS_REFRESH_HEADER = 0x3001;
    private static final int ROAM_COMPLETED = 0x3002;
    private static final int SETDATA = 0x3003;
    private LRecyclerView mDirectList;

    private View view;
    private ArrayList<Conversation> list;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case REFRESH_CONVERSATION_LIST:
                    Conversation conv= (Conversation) msg.obj;
                    initList(conv);
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_my_direct_message, container, false);
        JMessageClient.registerEventReceiver(this);
        //登录
        JMessageClient.login("fjjpydc1", "84915190qw", new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i==0){
                    Log.v("nihaoma","登录成功");
                    setListData();
                }else {
                    Log.v("nihaoma","登录失败");
                }
            }
        });
        list = new ArrayList<>();
       initView(view);
       initData();
        return view;
    }



    private void initView(View v) {
         mDirectList = (LRecyclerView)v.findViewById(R.id.direct_list);
    }

    private void initData() {

    }
    private void setListData(){
        List<Conversation> conversationList = JMessageClient.getConversationList();
        for(Conversation con:conversationList){
            mHandler.sendMessage(mHandler.obtainMessage(REFRESH_CONVERSATION_LIST,con));
        }
    }
    private void initList(Conversation conversationList){
        list.add(conversationList);
        mDirectList.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyDirectAdapter myadapter = new MyDirectAdapter(getActivity());
        myadapter.setDataList(list);
        LRecyclerViewAdapter mLRecyclerViewAdapter = new LRecyclerViewAdapter(myadapter);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (list!=null){
                    Intent intent = new Intent(getActivity(), ChatActivity.class);
                    intent.putExtra("MyDirectFragment",list.get(position).getTargetId());
                    startActivity(intent);
                }


            }
        });
        mDirectList.setAdapter(mLRecyclerViewAdapter);
    }
    /**
     * 收到消息
     */
    public void onEvent(MessageEvent event) {
        //接收未读信息
       // mConvListView.setUnReadMsg(JMessageClient.getAllUnReadMsgCount());
        Message msg = event.getMessage();
            final UserInfo userInfo = (UserInfo) msg.getTargetInfo();
            String targetId = userInfo.getUserName();
            Conversation conv = JMessageClient.getSingleConversation(targetId, userInfo.getAppKey());
            if (conv != null ) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (TextUtils.isEmpty(userInfo.getAvatar())) {
                            userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                                @Override
                                public void gotResult(int responseCode, String responseMessage, Bitmap avatarBitmap) {
                                    if (responseCode == 0) {
                                        //mConvListController.getAdapter().notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    }
                });
                mHandler.sendMessage(mHandler.obtainMessage(REFRESH_CONVERSATION_LIST, conv));
            }
    }

    /**
     * 接收离线消息
     *
     * @param event 离线消息事件
     */
    public void onEvent(OfflineMessageEvent event) {
        Conversation conv = event.getConversation();
        if (!conv.getTargetId().equals("feedback_Android")) {
            mHandler.sendMessage(mHandler.obtainMessage(REFRESH_CONVERSATION_LIST, conv));
        }
    }

    /**
     * 消息撤回
     */
    public void onEvent(MessageRetractEvent event) {
        Conversation conversation = event.getConversation();
        mHandler.sendMessage(mHandler.obtainMessage(REFRESH_CONVERSATION_LIST, conversation));
    }

    /**
     * 消息已读事件
     */
    public void onEventMainThread(MessageReceiptStatusChangeEvent event) {

    }

    /**
     * 消息漫游完成事件
     *
     * @param event 漫游完成后， 刷新会话事件
     */
    public void onEvent(ConversationRefreshEvent event) {
        Conversation conv = event.getConversation();
        if (!conv.getTargetId().equals("feedback_Android")) {
            mHandler.sendMessage(mHandler.obtainMessage(REFRESH_CONVERSATION_LIST, conv));
            //多端在线未读数改变时刷新
            if (event.getReason().equals(ConversationRefreshEvent.Reason.UNREAD_CNT_UPDATED)) {
                mHandler.sendMessage(mHandler.obtainMessage(REFRESH_CONVERSATION_LIST, conv));
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }
}
