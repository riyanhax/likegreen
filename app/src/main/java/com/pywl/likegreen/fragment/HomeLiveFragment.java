package com.pywl.likegreen.fragment;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
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

import com.netease.neliveplayer.playerkit.sdk.LivePlayer;
import com.netease.neliveplayer.playerkit.sdk.LivePlayerObserver;
import com.netease.neliveplayer.playerkit.sdk.PlayerManager;
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
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.pywl.likegreen.R;
import com.pywl.likegreen.ne.Observer;
import com.pywl.likegreen.ne.PhoneCallStateObserver;
import com.pywl.likegreen.service.PlayerService;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import cn.jpush.im.android.api.ChatRoomManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.RequestCallback;
import cn.jpush.im.android.api.model.ChatRoomInfo;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.api.BasicCallback;


/**
 * Created by theWind on 2018/8/9.
 * 首页直播fragment
 */

public class HomeLiveFragment extends Fragment implements View.OnClickListener {
    private EditText mLiveSay;//输入框
    private TextView mRoomCount;//在线人数
    private long roomId=10101698;
    private View mLiveGift;//礼物
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            long position;
            switch (msg.what) {
                case SHOW_PROGRESS:
                    position = setProgress();
                    msg = obtainMessage(SHOW_PROGRESS);
                    sendMessageDelayed(msg, 1000 - (position % 1000));
                    break;
            }
        }
    };
    private long setProgress() {
        if (player == null)
            return 0;

        int position = (int) player.getCurrentPosition();
        if (mCurrentTime != null) {
            mCurrentTime.setText(stringForTime(position));
        }
        return position;
    }
    public final static String TAG = HomeLiveFragment.class.getSimpleName();
    private static final int SHOW_PROGRESS = 0x01;

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

    private AdvanceSurfaceView surfaceView;
    private AdvanceSingleTextureView textureView;

    private LivePlayer player;
    private MediaInfo mediaInfo;

    private String mVideoPath="http://flvee0d5105.live.126.net/live/697e719fa8314e329d27421b7dad681e.flv?netease=flvee0d5105.live.126.net"; //文件路径
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
        View view = inflater.inflate(R.layout.fragment_home_live, container, false);
        Log.i(TAG, "onCreate");
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //保持屏幕常亮
        //JMessageClient.registerEventReceiver(this);//注册im监听器
        //来电时观察者
       // PhoneCallStateObserver.getInstance().observeLocalPhoneObserver(localPhoneObserver, true);
        initView(view);
        initData();
        initPlayer();
        return view;
    }



    private void initView(View v) {
         textureView = v.findViewById(R.id.live_texture);
        mLiveSay=(EditText)v.findViewById(R.id.et_live_say);//输入框
        mRoomCount=(TextView)v.findViewById(R.id.tv_live_room_count);//在线人数
       mLiveGift = v.findViewById(R.id.iv_live_gift);//礼物
        mLiveGift.setOnClickListener(this);
    }
    private void initData() {
        mUri = Uri.parse(mVideoPath);
        if (mMediaType != null && mMediaType.equals("localaudio")) { //本地音频文件采用软件解码
            mDecodeType = "software";
        }

        if (mDecodeType != null && mDecodeType.equals("hardware")) {
            mHardware = true;
        } else {
            mHardware = false;
        }

        //进入聊天室
        ChatRoomManager.enterChatRoom(roomId, new RequestCallback<Conversation>() {
            @Override
            public void gotResult(int i, String s, Conversation conversation) {
                String result = null != conversation ? conversation.toString() : null;
                Log.v("nihaoma",result);
            }
        });
        //查询聊天室信息
        ChatRoomManager.getChatRoomInfos(Collections.singleton(roomId), new RequestCallback<List<ChatRoomInfo>>() {
            @Override
            public void gotResult(int i, String s, List<ChatRoomInfo> chatRoomInfos) {
                chatRoomInfos.size();

                Log.v("nihaoma",chatRoomInfos.size()+"111");
            }
        });
    }

    private void initPlayer() {
        VideoOptions options = new VideoOptions();
        options.autoSwitchDefinition = false;
        options.hardwareDecode = mHardware;

        /**
         * isPlayLongTimeBackground 控制退到后台或者锁屏时是否继续播放，开发者可根据实际情况灵活开发,我们的示例逻辑如下：
         * 使用软件解码：
         * isPlayLongTimeBackground 为 false 时，直播进入后台停止播放，进入前台重新拉流播放
         * isPlayLongTimeBackground 为 true 时，直播进入后台不做处理，继续播放,
         *
         * 使用硬件解码：
         * 直播进入后台停止播放，进入前台重新拉流播放
         */
        options.isPlayLongTimeBackground = !isPauseInBackgroud;

//        if (isTimestampEnable) {
//            options.isSyncOpen = true;
//        }

        options.bufferStrategy = VideoBufferStrategy.LOW_LATENCY;
        player = PlayerManager.buildLivePlayer(getActivity(), mVideoPath, options);

        intentToStartBackgroundPlay();

        start();

        if (surfaceView == null) {
            player.setupRenderView(textureView, VideoScaleMode.FIT);
        } else {
            player.setupRenderView(surfaceView, VideoScaleMode.FIT);
        }
    }
    private void start() {
        player.registerPlayerObserver(playerObserver, true);

//        player.registerPlayerCurrentSyncTimestampListener(pullIntervalTime, mOnCurrentSyncTimestampListener, true);
//        player.registerPlayerCurrentSyncContentListener(mOnCurrentSyncContentListener, true);

        player.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        if (player != null) {
            player.onActivityResume(true);
        }
    }

   @Override
   public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");

        enterBackgroundPlay();
        if (player != null) {
            player.onActivityStop(true);
        }
    }


/*    @Override
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed");
        mBackPressed = true;
        finish();
        releasePlayer();

        super.onBackPressed();
    }*/


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        releasePlayer();
        //退出聊天室
        ChatRoomManager.leaveChatRoom(roomId, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {

            }
        });
    }


    private void releasePlayer() {
        if (player == null) {
            return;
        }

        Log.i(TAG, "releasePlayer");

        player.registerPlayerObserver(playerObserver, false);

//        player.registerPlayerCurrentSyncTimestampListener(0, null, false);
//        player.registerPlayerCurrentSyncContentListener(null, false);

        PhoneCallStateObserver.getInstance().observeLocalPhoneObserver(localPhoneObserver, false);
        player.setupRenderView(null, VideoScaleMode.NONE);
        textureView.releaseSurface();
        textureView = null;
        player.stop();
        player = null;
        intentToStopBackgroundPlay();
        mHandler.removeCallbacksAndMessages(null);

    }
    //这里直播可以用 LivePlayerObserver 点播用 VodPlayerObserver
    private LivePlayerObserver playerObserver = new LivePlayerObserver() {

        @Override
        public void onPreparing() {

        }

        @Override
        public void onPrepared(MediaInfo info) {
            mediaInfo = info;
        }

        @Override
        public void onError(int code, int extra) {
            mBuffer.setVisibility(View.INVISIBLE);
            if(code == CauseCode.CODE_VIDEO_PARSER_ERROR) {
                showToast("视频解析出错");
            }else {
                AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
                build.setTitle("播放错误").setMessage("错误码：" + code)
                        .setPositiveButton("确定", null)
                        .setCancelable(false)
                        .show();
            }

        }

        @Override
        public void onFirstVideoRendered() {
            showToast("视频第一帧已解析");

        }

        @Override
        public void onFirstAudioRendered() {
//            showToast("音频第一帧已解析");

        }

        @Override
        public void onBufferingStart() {
            mBuffer.setVisibility(View.VISIBLE);
        }

        @Override
        public void onBufferingEnd() {
            mBuffer.setVisibility(View.GONE);
        }

        @Override
        public void onBuffering(int percent) {
            Log.d(TAG, "缓冲中..." + percent + "%");
        }

        @Override
        public void onHardwareDecoderOpen() {

        }

        @Override
        public void onStateChanged(StateInfo stateInfo) {

        }

        @Override
        public void onParseDefinition(List<NEDefinitionData> data) {
            showToast("解析到多个清晰度");

        }

        @Override
        public void onAutoSwitchDefinition(NEDefinitionData.DefinitionType definitionType) {
            showToast("自动切换到清晰度" + definitionType);

        }

        @Override
        public void onVideoFrameFilter(NELivePlayer.NEVideoRawData videoRawData) {

        }

        @Override
        public void onAudioFrameFilter(NELivePlayer.NEAudioRawData audioRawData) {

        }

        @Override
        public void onHttpResponseInfo(int code, String header) {
            Log.i(TAG, "onHttpResponseInfo,code:" + code + " header:" + header);
        }
    };

    /**
     * 时间戳回调
     */
    private NELivePlayer.OnCurrentSyncTimestampListener mOnCurrentSyncTimestampListener = new NELivePlayer.OnCurrentSyncTimestampListener() {
        @Override
        public void onCurrentSyncTimestamp(long timestamp) {
            Log.v(TAG, "OnCurrentSyncTimestampListener,onCurrentSyncTimestamp:" + timestamp);

        }
    };

    private NELivePlayer.OnCurrentSyncContentListener mOnCurrentSyncContentListener = new NELivePlayer.OnCurrentSyncContentListener() {
        @Override
        public void onCurrentSyncContent(List<String> content) {
            StringBuffer sb = new StringBuffer();
            for (String str : content) {
                sb.append(str + "\r\n");
            }
            showToast("onCurrentSyncContent,收到同步信息:" + sb.toString());
            Log.v(TAG, "onCurrentSyncContent,收到同步信息:" + sb.toString());
        }
    };

    private void showToast(String msg) {
        Log.d(TAG, "showToast" + msg);
        try {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        } catch (Throwable th) {
            th.printStackTrace(); // fuck oppo
        }
    }
    /**
     * 处理service后台播放逻辑
     */
    private void intentToStartBackgroundPlay() {
        if (!mHardware && !isPauseInBackgroud) {
            PlayerService.intentToStart(getActivity());
        }
    }

    private void intentToStopBackgroundPlay() {
        if (!mHardware && !isPauseInBackgroud) {
            PlayerService.intentToStop(getActivity());
            player = null;
        }
    }


    private void enterBackgroundPlay() {
        if (!mHardware && !isPauseInBackgroud) {
            PlayerService.setMediaPlayer(player);
        }
    }

    private void stopBackgroundPlay() {
        if (!mHardware && !isPauseInBackgroud) {
            PlayerService.setMediaPlayer(null);
        }
    }
    private static String stringForTime(long position) {
        int totalSeconds = (int) ((position / 1000.0) + 0.5);

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds).toString();
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
                JMessageClient.login("fjjpydc", "84915190qw", new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                            if (i==0){
                                Log.v("nihaoma","登录成功");
                            }else {
                                Log.v("nihaoma","登录失败");
                            }
                    }
                });
                break;
        }
    }
}