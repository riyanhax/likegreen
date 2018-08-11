package com.pywl.likegreen.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.pywl.likegreen.R;
import com.pywl.likegreen.chat.Constants;
import com.pywl.likegreen.chat.EmotionInputDetector;
import com.pywl.likegreen.chat.MessageCenter;
import com.pywl.likegreen.chat.MessageInfo;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

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
    private List<MessageInfo> messageInfos;//聊天数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
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

      /*  mDetector = EmotionInputDetector.with(this)
                .setEmotionView(emotionLayout)
                //.setViewPager(viewpager)
                .bindToContent(mChatList)
                .bindToEditText(mChatWord)
                .bindToEmotionButton(emotionButton)
                .bindToAddButton(emotionAdd)
                .bindToSendButton(emotionSend)
                .bindToVoiceButton(emotionVoice)
                .bindToVoiceText(voiceText)
                .build();*/
    }
    private void handleIncomeAction() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        MessageCenter.handleIncoming(bundle, getIntent().getType(), this);
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
/*        messageInfos = new ArrayList<>();

        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setContent("你好，欢迎使用Rance的聊天界面框架");
        messageInfo.setFileType(Constants.CHAT_FILE_TYPE_TEXT);
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        messageInfo.setHeader("http://img0.imgtn.bdimg.com/it/u=401967138,750679164&fm=26&gp=0.jpg");
        messageInfos.add(messageInfo);

        MessageInfo messageInfo1 = new MessageInfo();
        messageInfo1.setFilepath("http://www.trueme.net/bb_midi/welcome.wav");
        messageInfo1.setVoiceTime(3000);
        messageInfo1.setFileType(Constants.CHAT_FILE_TYPE_VOICE);
        messageInfo1.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo1.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        messageInfo1.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        messageInfos.add(messageInfo1);

        MessageInfo messageInfo2 = new MessageInfo();
        messageInfo2.setFilepath("http://img4.imgtn.bdimg.com/it/u=1800788429,176707229&fm=21&gp=0.jpg");
        messageInfo2.setFileType(Constants.CHAT_FILE_TYPE_IMAGE);
        messageInfo2.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        messageInfo2.setHeader("http://img0.imgtn.bdimg.com/it/u=401967138,750679164&fm=26&gp=0.jpg");
        messageInfos.add(messageInfo2);

        MessageInfo messageInfo3 = new MessageInfo();
        messageInfo3.setContent("[微笑][色][色][色]");
        messageInfo3.setFileType(Constants.CHAT_FILE_TYPE_TEXT);
        messageInfo3.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo3.setSendState(Constants.CHAT_ITEM_SEND_ERROR);
        messageInfo3.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        messageInfos.add(messageInfo3);

        chatAdapter.addAll(messageInfos);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(final MessageInfo messageInfo) {
        messageInfo.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);
        messageInfos.add(messageInfo);
        chatAdapter.notifyItemInserted(messageInfos.size() - 1);
//        chatAdapter.add(messageInfo);
        chatList.scrollToPosition(chatAdapter.getItemCount() - 1);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                chatAdapter.notifyDataSetChanged();
            }
        }, 2000);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                MessageInfo message = new MessageInfo();
                message.setContent("这是模拟消息回复");
                message.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                message.setFileType(Constants.CHAT_FILE_TYPE_TEXT);
                message.setHeader("http://img0.imgtn.bdimg.com/it/u=401967138,750679164&fm=26&gp=0.jpg");
                messageInfos.add(message);
                chatAdapter.notifyItemInserted(messageInfos.size() - 1);
                chatList.scrollToPosition(chatAdapter.getItemCount() - 1);
            }
        }, 3000);*/
    }
}
