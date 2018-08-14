package com.pywl.likegreen.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.pywl.likegreen.R;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;

/*
聊天界面
* */
public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    private View mChatReturnBack,mChatAlbum,mChatShot,mChatShortVideo,mSpeaking,mRlEtChat,mLlChatMore;
    private TextView mChatName;//聊天姓名
    private LRecyclerView mChatList;//聊天列表
    private EditText mChatWord;//输入框
    private ImageView mChatSpeak,mChatMore;
    private boolean isSpeaking=false;
    private boolean isMore=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        JMessageClient.registerEventReceiver(this);
        initView();
        initData();
        handleIncomeAction();
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
    }
    private void initData() {
        UserInfo myInfo = JMessageClient.getMyInfo();
        //单聊


/*        mConv = JMessageClient.getSingleConversation(mTargetId, mTargetAppKey);
        if (mConv == null) {
            mConv = Conversation.createSingleConversation(mTargetId, mTargetAppKey);
        }
        mChatAdapter = new ChattingListAdapter(mContext, mConv, longClickListener);*/


    }
    private void handleIncomeAction() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chat_return_back://返回
                break;
            case R.id.iv_chat_speak://切换语音
                speaking(isSpeaking);
                break;
            case R.id.iv_more_chat://加减号
                showMore(isMore);
                break;
            case R.id.ll_chat_album://相册
                break;
            case R.id.ll_chat_shot://相机
                break;
            case R.id.ll_chat_shortvideo://小视频
                break;
            case R.id.rl_chat_speaking://按住说话
                break;

        }
    }

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


    /**
     * 构造聊天数据
     */
    private void LoadData() {

    }
    @Override
    public void onDestroy() {
        //注销消息接收
        JMessageClient.unRegisterEventReceiver(this);
        super.onDestroy();
    }
}
