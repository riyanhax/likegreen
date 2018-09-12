package com.pywl.likegreen.fragment.live;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.netease.neliveplayer.playerkit.common.log.LogUtil;
import com.netease.neliveplayer.playerkit.sdk.LivePlayer;
import com.netease.neliveplayer.playerkit.sdk.LivePlayerObserver;
import com.netease.neliveplayer.playerkit.sdk.PlayerManager;
import com.netease.neliveplayer.playerkit.sdk.VodPlayer;
import com.netease.neliveplayer.playerkit.sdk.VodPlayerObserver;
import com.netease.neliveplayer.playerkit.sdk.constant.CauseCode;
import com.netease.neliveplayer.playerkit.sdk.model.MediaInfo;
import com.netease.neliveplayer.playerkit.sdk.model.StateInfo;
import com.netease.neliveplayer.playerkit.sdk.model.VideoBufferStrategy;
import com.netease.neliveplayer.playerkit.sdk.model.VideoOptions;
import com.netease.neliveplayer.playerkit.sdk.model.VideoScaleMode;
import com.netease.neliveplayer.playerkit.sdk.view.AdvanceSingleTextureView;
import com.netease.neliveplayer.playerkit.sdk.view.AdvanceSurfaceView;
import com.netease.neliveplayer.sdk.NEDefinitionData;
import com.netease.neliveplayer.sdk.NELivePlayer;
import com.pywl.likegreen.R;
import com.pywl.likegreen.adapter.LivingRoomAudienceSayAdapter;
import com.pywl.likegreen.adapter.live.HomeLiveAdapter;
import com.pywl.likegreen.adapter.live.HomeLiveAdapter3;
import com.pywl.likegreen.bean.LivingRoomMsgBean;
import com.pywl.likegreen.ne.Observer;
import com.pywl.likegreen.ne.PhoneCallStateObserver;
import com.pywl.likegreen.service.PlayerService;
import com.pywl.likegreen.view.VerticalViewPager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import cn.jpush.im.android.api.ChatRoomManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.RequestCallback;
import cn.jpush.im.android.api.event.ChatRoomMessageEvent;
import cn.jpush.im.android.api.model.ChatRoomInfo;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.api.BasicCallback;


/**
 * Created by theWind on 2018/8/9.
 * 首页直播播放端fragment
 */

public class HomeLiveFragment1 extends Fragment implements View.OnClickListener, View.OnKeyListener {

    private HomeLiveAdapter3 homeLiveAdapter;
    private RecyclerView mRecyclerView;
    public final static String TAG = HomeLiveFragment1.class.getSimpleName();
    private static final int SHOW_PROGRESS = 0x01;
    private static final int TOTAL_ROOM_COUNT = 2;
    private static final int SET_CHAT_DATA = 3;
    private static final int SEND_MY_WORD = 4;
    private EditText mLiveSay;//输入框
    private TextView mRoomCount;//在线人数
    private long roomId=10101698L;
    private View mLiveGift;//礼物
    private LRecyclerView mAudienceSay;//聊天列表
    private String groupName=null;//群组名
    private String groupDesc=null;//群组描述
    private long  groupId=30041506L;
    private Conversation conv;
    private int offset=10;
    private Conversation mConversationRoom;
    private  ArrayList<cn.jpush.im.android.api.model.Message> msgList;//聊天数据列表
    private LivingRoomMsgBean livingRoomMsgBean;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            long position;
            switch (msg.what) {
                case TOTAL_ROOM_COUNT:
                    int count = (int) msg.obj;
                    setTotalMemberCount(count);
                    break;
                case SET_CHAT_DATA:
                case SEND_MY_WORD:
                    cn.jpush.im.android.api.model.Message data = (cn.jpush.im.android.api.model.Message) msg.obj;
                    setChatRecord(data);
                    break;
            }
        }
    };
    //设置聊天数据
    private void setChatRecord(cn.jpush.im.android.api.model.Message data) {
        msgList.add(data);
        LivingRoomAudienceSayAdapter livingRoomAudienceSayAdapter = new LivingRoomAudienceSayAdapter(getActivity());
        livingRoomAudienceSayAdapter.setDataList(msgList);
        LRecyclerViewAdapter adapter = new LRecyclerViewAdapter(livingRoomAudienceSayAdapter);
        mAudienceSay.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAudienceSay.setAdapter(adapter);
    }


    private ImageButton mPlayBack;
    private TextView mFileName; //文件名称
    private ImageView mAudioRemind; //播音频文件时提示
    private View mBuffer; //用于指示缓冲状态
    private RelativeLayout mPlayToolbar;
    private ImageView mPauseButton;
    private ImageView mSetPlayerScaleButton;
    private ImageView mSnapshotButton;
    private ImageView mMuteButton;
    private SeekBar mProgress;
    private TextView mEndTime;
    private TextView mCurrentTime;

    private AdvanceSingleTextureView surfaceView;


    private LivePlayer player;
    private MediaInfo mediaInfo;

    private String mVideoPath="http://flvee0d5105.live.126.net/live/697e719fa8314e329d27421b7dad681e.flv?netease=flvee0d5105.live.126.net"; //文件路径
    private String mVideoPath2="http://vodhj5bqn44.vod.126.net/vodhj5bqn44/1BrIAtvV_1818587477_shd.mp4"; //文件路径
    private String mVideoPath3="http://vodhj5bqn44.vod.126.net/vodhj5bqn44/FmdVOTqd_1818586962_shd.mp4"; //文件路径
    //private String mVideoPath="http://jdvod6w938ryc.vod.126.net/jdvod6w938ryc/ea89570d-7298-47f9-8ea9-030fe1b5eeef.mp4"; //文件路径
    private String mDecodeType="software";//解码类型，硬解或软解
    private String mMediaType="livestream"; //媒体类型
    private boolean mHardware = true;
    private Uri mUri;
    private String mTitle;
    private boolean mBackPressed;
    private boolean mPaused = false;
    private boolean isMute = false;
    private boolean mIsFullScreen = false;
    protected boolean isPauseInBackgroud;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_live1, container, false);
        Log.i(TAG, "onCreate");
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //保持屏幕常亮
        JMessageClient.registerEventReceiver(this);//注册im监听器
        //来电时观察者
       // PhoneCallStateObserver.getInstance().observeLocalPhoneObserver(localPhoneObserver, true);
        msgList = new ArrayList<>();
        //livingRoomMsgBean = new LivingRoomMsgBean();
       // intoChatRoom();
        initView(view);
       // initData();
        //initPlayer();
        return view;
    }

    private void intoChatRoom() {
        //登录
        JMessageClient.login("fjjpydc1", "84915190qw", new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i==0){
                    Log.v("nihaoma","登录成功");
                    gointoRoom();
                }else {
                    Log.v("nihaoma","登录失败");
                }
            }
        });


/*        //获取这个appkey下的聊天室
        ChatRoomManager.getChatRoomListByApp(0, 8, new RequestCallback<List<ChatRoomInfo>>() {
            @Override
            public void gotResult(int i, String s, List<ChatRoomInfo> chatRoomInfos) {
                Log.v("nihaoma",i+"?"+s+chatRoomInfos.toString());
            }
        });*/


    }

    private void gointoRoom() {
  /*      //创建群聊
        JMessageClient.createPublicGroup(groupName, groupDesc, new CreateGroupCallback() {

            @Override
            public void gotResult(int i, String s, long id) {
                Log.v("nihaoma",i+"<>"+s+"<>"+id);
                //创建成功
                if (i==0){
                    groupId=id;
                }
            }
        });*/
/*       //创建群聊会话
        conv = Conversation.createGroupConversation(groupId);
        //获取会话列表
        Conversation groupConversation = JMessageClient.getGroupConversation(groupId);
        Log.v("nihaoma",groupConversation.toString());
        //全部会话
       // List<cn.jpush.im.android.api.model.Message> allMessage = groupConversation.getAllMessage();

        //获取群成员
        JMessageClient.getGroupMembers(groupId, new GetGroupMembersCallback() {
            @Override
            public void gotResult(int i, String s, List<UserInfo> list) {
                for(UserInfo user:list){
                    user.toString();
                }
                Message msg = Message.obtain();
                msg.what=TOTAL_ROOM_COUNT;
                msg.obj=list.size();
                mHandler.sendMessage(msg);
            }
        });*/
        //进入聊天室
        ChatRoomManager.enterChatRoom(roomId, new RequestCallback<Conversation>() {
            @Override
            public void gotResult(int i, String s, Conversation conversation) {
                Log.v("nihaoma",i+"55"+s+"55"+conversation.toString());
                mConversationRoom=conversation;
            }
        });
        //获取聊天室会话
       mConversationRoom = JMessageClient.getChatRoomConversation(roomId);
        Log.v("nihaoma",mConversationRoom.toString());
       //查询聊天室信息
        ChatRoomManager.getChatRoomInfos(Collections.singleton(roomId), new RequestCallback<List<ChatRoomInfo>>() {
            @Override
            public void gotResult(int i, String s, List<ChatRoomInfo> chatRoomInfos) {
                //设置聊天室人数
                Message msg = Message.obtain();
                msg.what=TOTAL_ROOM_COUNT;
                msg.obj=chatRoomInfos.get(0).getTotalMemberCount();
                mHandler.sendMessage(msg);
            }
        });
    }

   // 接收聊天室消息
    public void onEventMainThread(ChatRoomMessageEvent event) {
        if (mConversationRoom==null){
            mConversationRoom = JMessageClient.getChatRoomConversation(roomId);
            Log.v("nihaoma",mConversationRoom.toString());
        }
        List<cn.jpush.im.android.api.model.Message> msgs = event.getMessages();
        Log.v("nihaoma","3444444444"+msgs.toString());

        for (cn.jpush.im.android.api.model.Message msg : msgs) {
            //这个页面仅仅展示聊天室会话的消息
            Log.v("nihaoma",msgs.size()+"333333"+msg.toString());
            Message obtain = Message.obtain();
            obtain.what=SET_CHAT_DATA;
            obtain.obj=msg;
            mHandler.sendMessage(obtain);
        }
    }
    private boolean mShouldPlay=true;
    private HomeLiveAdapter1 mShortVideoListAdapter;
    private ArrayList<String> mItemList;
    private int mCurrentPosition =  -1;
    private RecyclerView mVideoList;
    private void initView(View v) {
        mItemList= new ArrayList<>();
        mItemList.add(mVideoPath);
        mItemList.add(mVideoPath2);
        mItemList.add(mVideoPath3);
        mVideoList = v.findViewById(R.id.video_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mVideoList.setLayoutManager(layoutManager);
        mVideoList.setHasFixedSize(true);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mVideoList);

        mShortVideoListAdapter = new HomeLiveAdapter1(mItemList);
        mVideoList.setAdapter(mShortVideoListAdapter);
        mVideoList.addOnScrollListener(mOnScrollListener);

        if (mShouldPlay) {
            mVideoList.post(new Runnable() {
               @Override
                public void run() {
                    startCurVideoView();
                    mShouldPlay = false;
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.startCurVideoView();
        } else {
            mShouldPlay = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.pauseCurVideoView();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.stopCurVideoView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.stopCurVideoView();
        }
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                startCurVideoView();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    private void startCurVideoView() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mVideoList.getLayoutManager();
        int visibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();

        if (visibleItemPosition >= 0 && mCurrentPosition != visibleItemPosition) {
            mShortVideoListAdapter.stopCurVideoView();
            mCurrentPosition = visibleItemPosition;
            View holderView = mVideoList.findViewWithTag(mCurrentPosition);
            if (holderView != null) {
                HomeLiveAdapter1.ViewHolder viewHolder = (HomeLiveAdapter1.ViewHolder) mVideoList.getChildViewHolder(holderView);
                mShortVideoListAdapter.setCurViewHolder(viewHolder);
                mShortVideoListAdapter.startCurVideoView();
            }
        }
    }




    private void initData() {

    }





    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");

/*        //退出聊天室
        ChatRoomManager.leaveChatRoom(roomId, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {

            }
        });*/
        //退群
     /*   JMessageClient.adminDissolveGroup(groupId, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {

            }
        });*/
        JMessageClient.unRegisterEventReceiver(this);
    }

    //处理与电话逻辑
    private Observer<Integer> localPhoneObserver = new Observer<Integer>() {

        @Override
        public void onEvent(Integer phoneState) {
            if (phoneState == TelephonyManager.CALL_STATE_IDLE) {
                player.start();
            } else if (phoneState == TelephonyManager.CALL_STATE_RINGING) {
                player.stop();
            } else {
                Log.i(TAG, "localPhoneObserver onEvent " + phoneState);
            }

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_live_gift://礼物

                break;
        }
    }

    public void setTotalMemberCount(int count) {
        mRoomCount.setText(String.valueOf(count));
    }
    //回车键监听
    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if(keyCode == KeyEvent.KEYCODE_ENTER){

            if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                //处理事件
                String userInput = mLiveSay.getText().toString();
                if(TextUtils.isEmpty(userInput)){
                    LogUtil.e("nihaoma", "IME_ACTION_SEARCH input is null");
                    return true;
                }
                userInput = userInput.trim();
                sendRoomMsg(userInput);
                return true;
            }
        }
        return false;
    }
    //发送消息
    private void sendRoomMsg( String userInput){
       // 发送聊天室消息
        Conversation conv = JMessageClient.getChatRoomConversation(roomId);
        if (null == conv) {
            conv = Conversation.createChatRoomConversation(roomId);
        }
        final  cn.jpush.im.android.api.model.Message msg = conv.createSendTextMessage(userInput);//实际聊天室可以支持所有类型的消息发送，demo为了简便，仅仅实现了文本类型的消息发送
        Log.v("nihaoma","#1111"+msg.toString());
        msg.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                if (0 == responseCode) {
                    Log.v("nihaoma",responseCode+"1111?"+responseMessage);
                } else {
                    Log.v("nihaoma",responseCode+"#1111"+responseMessage);
                    Toast.makeText(getActivity(),"发送失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        JMessageClient.sendMessage(msg);
        Message msgHandler = Message.obtain();
        msgHandler.what=SEND_MY_WORD;
        msgHandler.obj=msg;
        mHandler.sendMessage(msgHandler);



    }

/*    //实时监听消息
    public void onEvent(MessageEvent event){
        cn.jpush.im.android.api.model.Message msg = event.getMessage();
        if (msg.getTargetType()== ConversationType.group) {
            //获取全部会话
            if (conv==null){
                conv= JMessageClient.getGroupConversation(groupId);
            }
            List<cn.jpush.im.android.api.model.Message> allMessage = conv.getAllMessage();
            for(cn.jpush.im.android.api.model.Message ms2g:allMessage){
                Log.v("nihaoma", "9999" + ms2g.toString());
            }

            Message obtain = Message.obtain();
            obtain.what=SET_CHAT_DATA;
            obtain.obj=allMessage;
            mHandler.sendMessage(obtain);
            switch (msg.getContentType()) {
                case text:
                    //处理文字消息
                    TextContent textContent = (TextContent) msg.getContent();
                    textContent.getText();
                    Log.v("nihaoma", "?????" + textContent.getText());
                    break;
                case image:
                    //处理图片消息
                    ImageContent imageContent = (ImageContent) msg.getContent();
                    imageContent.getLocalPath();//图片本地地址
                    imageContent.getLocalThumbnailPath();//图片对应缩略图的本地地址
                    break;
                case voice:
                    //处理语音消息
                    VoiceContent voiceContent = (VoiceContent) msg.getContent();
                    voiceContent.getLocalPath();//语音文件本地地址
                    voiceContent.getDuration();//语音文件时长
                    break;
                case custom:
                    //处理自定义消息
                    CustomContent customContent = (CustomContent) msg.getContent();
                    customContent.getNumberValue("custom_num"); //获取自定义的值
                    customContent.getBooleanValue("custom_boolean");
                    customContent.getStringValue("custom_string");
                    break;
                case eventNotification:
                    //处理事件提醒消息
                    EventNotificationContent eventNotificationContent = (EventNotificationContent) msg.getContent();
                    switch (eventNotificationContent.getEventNotificationType()) {
                        case group_member_added:
                            //群成员加群事件

                            break;
                        case group_member_removed:
                            //群成员被踢事件
                            break;
                        case group_member_exit:
                            //群成员退群事件
                            break;
                        case group_info_updated://since 2.2.1
                            //群信息变更事件
                            break;
                    }
                    break;
            }
        }
    }*/


}
