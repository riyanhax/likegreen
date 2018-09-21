package com.xbdl.xinushop.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.netease.neliveplayer.playerkit.common.log.LogUtil;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ChatAdapter;
import com.xbdl.xinushop.takephoto.CameraActivity;
import com.xbdl.xinushop.takephoto.RequestCode;


import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;

import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.api.BasicCallback;


/*
聊天界面
* */
public class ChatAct2ivity extends JGBaseActivity implements View.OnClickListener, View.OnKeyListener {
    private static final int SEND_MY_WORD = 0;
    private static final int SEND_IMG_MSG = 1;
    private View mChatReturnBack,mChatAlbum,mChatShot,mChatShortVideo,mSpeaking,mRlEtChat,mLlChatMore;
    private TextView mChatName;//聊天姓名
    private LRecyclerView mChatList;//聊天列表
    private EditText mChatWord;//输入框
    private ImageView mChatSpeak,mChatMore;
    private boolean isSpeaking=false;
    private boolean isMore=false;
    private Conversation conversation;
    private int mOffset=18;//历史对话
    private static final int DATALIST = 0x4000;
    private  ArrayList<cn.jpush.im.android.api.model.Message> msgData;
    private String   username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);
        JMessageClient.registerEventReceiver(this);
        msgData = new ArrayList<>();
        initView();
        initData();

    }




    private void initView() {
         mChatReturnBack = findViewById(R.id.chat_return_back);//返回按钮
         mChatName =(TextView) findViewById(R.id.tv_chat_name);//聊天姓名
         mChatList =(LRecyclerView) findViewById(R.id.lr_chat_list);//聊天列表
         mChatWord=(EditText) findViewById(R.id.et_chat);//输入框
         mChatSpeak =(ImageView) findViewById(R.id.iv_chat_speak);//说话切换按钮
         mChatMore =(ImageView) findViewById(R.id.iv_more_chat);//加减号显示相册
         mChatAlbum = findViewById(R.id.ll_chat_album);//相册
         mChatShot = findViewById(R.id.ll_chat_shot);//相机
         mChatShortVideo = findViewById(R.id.ll_chat_shortvideo);//小视频
        mSpeaking = findViewById(R.id.rl_chat_speaking);//按住说话
        mRlEtChat=findViewById(R.id.rl_et_chat);//包裹EditText
        mLlChatMore=findViewById(R.id.ll_chat_more);//显示相册拍摄view
        mChatReturnBack.setOnClickListener(this);
         mChatSpeak.setOnClickListener(this);
        mChatAlbum.setOnClickListener(this);
         mChatMore.setOnClickListener(this);
        mChatShot.setOnClickListener(this);
        mChatShortVideo.setOnClickListener(this);
        mSpeaking.setOnClickListener(this);
        mChatWord.setOnKeyListener(this);
    }
    private void initData() {
       // UserInfo myInfo = JMessageClient.getMyInfo();
        Intent intent = getIntent();
        username = intent.getStringExtra("MyDirectFragment");
        mChatName.setText(username);
        //创建会话
        conversation = JMessageClient.getSingleConversation(username, getResources().getString(R.string.app_key));
        if (conversation==null){
            conversation= Conversation.createSingleConversation(username, getResources().getString(R.string.app_key));
        }
        //获取历史对话
       // List<cn.jpush.im.android.api.model.Message> fromNewest = conversation.getMessagesFromNewest(0, mOffset);
        List<cn.jpush.im.android.api.model.Message> fromNewest = conversation.getMessagesFromOldest(0, mOffset);
        for(cn.jpush.im.android.api.model.Message data:fromNewest){
            Message msg = Message.obtain();
            msg.what=DATALIST;
            msg.obj=data;
            mHandler.sendMessage(msg);
        }

    }
    //聊天列表
    private void setList( cn.jpush.im.android.api.model.Message fromNewest) {
        msgData.add(fromNewest);
        ChatAdapter chatAdapter = new ChatAdapter(this,conversation);
        chatAdapter.setDataList(msgData);
        mChatList.setLayoutManager(new LinearLayoutManager(getParent()));
        LRecyclerViewAdapter adapter=new LRecyclerViewAdapter(chatAdapter);
        mChatList.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chat_return_back://返回
                break;
            case R.id.iv_chat_speak://切换语音
                speaking(isSpeaking);
                break;
            case R.id.iv_more_chat://加减号、弹出视频，小视频拍照
                showMore(isMore);
                break;
            case R.id.ll_chat_album://相册

                break;
            case R.id.ll_chat_shot://相机
                Intent intent = new Intent(ChatAct2ivity.this, CameraActivity.class);
                startActivityForResult(intent,RequestCode.TAKE_PHOTO);
                break;
            case R.id.ll_chat_shortvideo://小视频
                break;
            case R.id.rl_chat_speaking://按住说话
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case RequestCode.TAKE_PHOTO:
                if (data != null) {
                    String name = data.getStringExtra("take_photo");
                    Bitmap bitmap = BitmapFactory.decodeFile(name);
                    ImageContent.createImageContentAsync(bitmap, new ImageContent.CreateImageContentCallback() {
                        @Override
                        public void gotResult(int responseCode, String responseMessage, ImageContent imageContent) {
                            if (responseCode == 0) {
                                cn.jpush.im.android.api.model.Message msg = conversation.createSendMessage(imageContent);
                                Message message = Message.obtain();
                                message.what=SEND_IMG_MSG;
                                message.obj=msg;
                                mHandler.handleMessage(message);
                            }
                        }
                    });
                }
                break;
        }
    }

    //弹出视频，小视频拍照
    private void showMore(boolean in) {
        if (in){
            mChatMore.setImageResource(R.drawable.more_liaotian);
            mLlChatMore.setVisibility(View.GONE);
            isMore=false;
        }else {
            mChatMore.setImageResource(R.drawable.more_liaotian_dianjihou);
            mLlChatMore.setVisibility(View.VISIBLE);
            isMore=true;
        }
    }
    //切换语音、文字
    private void speaking(boolean in) {
        if (in){
            mChatSpeak.setImageResource(R.drawable.voice_chat);
            mSpeaking.setVisibility(View.GONE);
            mRlEtChat.setVisibility(View.VISIBLE);
            isSpeaking=false;
        }else {
            mChatSpeak.setImageResource(R.drawable.keyboard);
            mSpeaking.setVisibility(View.VISIBLE);
            mRlEtChat.setVisibility(View.GONE);
            isSpeaking=true;
        }
    }

    @Override
    public void onDestroy() {
        //注销消息接收
        JMessageClient.unRegisterEventReceiver(this);
        super.onDestroy();
    }

    @Override
    protected void threadRun() {

    }

    @Override
    void handlermsg(Message msg) {
        switch (msg.what){
            case DATALIST:
            case SEND_MY_WORD:
            case SEND_IMG_MSG:
                cn.jpush.im.android.api.model.Message fromNewest= (cn.jpush.im.android.api.model.Message) msg.obj;
                setList(fromNewest);
                break;
        }

    }
    public void onEvent(MessageEvent event) {
        cn.jpush.im.android.api.model.Message message = event.getMessage();
        //如果是单聊
        if (message.getTargetType()== ConversationType.single){
            Message msg = Message.obtain();
            msg.what=DATALIST;
            msg.obj=message;
            mHandler.sendMessage(msg);
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                //处理事件
                String userInput = mChatWord.getText().toString();
                if(TextUtils.isEmpty(userInput)){
                    LogUtil.e("nihaoma", "IME_ACTION_SEARCH input is null");
                    return true;
                }
                userInput = userInput.trim();
                sendMsg(userInput);
                return true;
            }
        }
        return false;
    }
    //发送消息
    private void sendMsg(String userInput) {
        // 发送聊天室消息
        Conversation singleConversation = JMessageClient.getSingleConversation(username, getResources().getString(R.string.app_key));
        if (singleConversation==null){
            singleConversation= Conversation.createSingleConversation(username, getResources().getString(R.string.app_key));
        }
        final  cn.jpush.im.android.api.model.Message msg = singleConversation.createSendTextMessage(userInput);//实际聊天室可以支持所有类型的消息发送，demo为了简便，仅仅实现了文本类型的消息发送
        Log.v("nihaoma","#1111"+msg.toString());
        msg.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                if (0 == responseCode) {
                    Log.v("nihaoma",responseCode+"1111?"+responseMessage);
                } else {
                    Log.v("nihaoma",responseCode+"#1111"+responseMessage);
                    Toast.makeText(getApplicationContext(),"发送失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        JMessageClient.sendMessage(msg);
        Message msgHandler = Message.obtain();
        msgHandler.what=SEND_MY_WORD;
        msgHandler.obj=msg;
        mHandler.sendMessage(msgHandler);
    }

}
