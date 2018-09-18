package com.pywl.likegreen.chat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.pywl.likegreen.MyApplication;
import com.pywl.likegreen.R;
import com.pywl.likegreen.base.BaseActivity;
import com.pywl.likegreen.chat.bean.DefaultUser;
import com.pywl.likegreen.chat.bean.MyMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.jiguang.imui.chatinput.ChatInputView;
import cn.jiguang.imui.chatinput.listener.CameraControllerListener;
import cn.jiguang.imui.chatinput.listener.OnCameraCallbackListener;
import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
import cn.jiguang.imui.chatinput.menu.Menu;
import cn.jiguang.imui.chatinput.menu.MenuManager;
import cn.jiguang.imui.chatinput.model.FileItem;
import cn.jiguang.imui.commons.ImageLoader;
import cn.jiguang.imui.commons.models.IMessage;
import cn.jiguang.imui.messages.MessageList;
import cn.jiguang.imui.messages.MsgListAdapter;
import cn.jiguang.imui.messages.ptr.PtrDefaultHeader;
import cn.jiguang.imui.messages.ptr.PtrHandler;
import cn.jiguang.imui.messages.ptr.PullToRefreshLayout;
import cn.jiguang.imui.utils.DisplayUtil;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VideoContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import cn.jpush.im.android.eventbus.EventBus;
import cn.jpush.im.api.BasicCallback;
import jiguang.chat.entity.Event;
import jiguang.chat.entity.EventType;
import jiguang.chat.utils.HandleResponseCode;

public class ChatActivity extends BaseActivity {
    private MsgListAdapter<MyMessage> mAdapter;
    private static final String TAG = "asdf";
    Conversation conversation;
    MsgListAdapter adapter = null;
    PullToRefreshLayout ptrLayout;
    MessageList messageList;
    List<MyMessage> messages = new ArrayList<>();//适配器message

    Activity getActivity() {
        return ChatActivity.this;
    }

    InputMethodManager mImm;
    ChatInputView chatInputView;//输入项菜单
    String myid = "";
    String targetId = ""; //获取用户的username
    String type = "";//回话类别
    String appkey = "";//获取会话target appkey,只有单聊会话中会有target appkey这个概念，群聊和聊天室类型会话直接返回空字符串
    String id = "";//获取Conversation的ID
    long groupid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        this.mImm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        myid = getString(R.string.appidjiguang);
        targetId = getIntent().getStringExtra(MyApplication.TARGET_ID); //获取用户的username
        type = getIntent().getStringExtra(MyApplication.message_tyoe);//回话类别
        appkey = getIntent().getStringExtra(MyApplication.TARGET_APP_KEY);//获取会话target appkey,只有单聊会话中会有target appkey这个概念，群聊和聊天室类型会话直接返回空字符串
        id = getIntent().getStringExtra(MyApplication.DRAFT);//获取Conversation的ID
        groupid = getIntent().getLongExtra(MyApplication.GROUP_ID, 0);
        ptrLayout = (PullToRefreshLayout) findViewById(R.id.pull_to_refresh_layout);
        PtrDefaultHeader header = new PtrDefaultHeader(getActivity());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        header.setPadding(0, DisplayUtil.dp2px(getActivity(), 15), 0, DisplayUtil.dp2px(getActivity(), 10));
        header.setPtrFrameLayout(ptrLayout);
        ptrLayout.setLoadingMinTime(1000);
        ptrLayout.setDurationToCloseHeader(1500);
        ptrLayout.setHeaderView(header);
        ptrLayout.addPtrUIHandler(header);
// 如果设置为 true，下拉刷新时，内容固定，只有 Header 变化
        ptrLayout.setPinContent(true);
        ptrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PullToRefreshLayout layout) {
                Log.i("asdf", "Loading next page");
                //loadNextPage();
                // 加载完历史消息后调用
                ptrLayout.refreshComplete();
            }
        });

        messageList = (MessageList) findViewById(R.id.msg_list);
        messageList.setShowSenderDisplayName(true);
        messageList.setShowReceiverDisplayName(true);

        if (!TextUtils.isEmpty(targetId)) {
            //单聊
            conversation = JMessageClient.getSingleConversation(targetId, appkey);
            //  adapter = new MsgListAdapter<>(targetId, holdersConfig, imageLoader);
        } else//群聊
        {
            conversation = JMessageClient.getGroupConversation(groupid);
            //   adapter = new MsgListAdapter<>(groupid + "", holdersConfig, imageLoader);
        }

        List<cn.jpush.im.android.api.model.Message> allMessage = conversation.getAllMessage();//会话message
        getMessages(allMessage);

        initMsgAdapter();
        //    messageList.setAdapter(adapter);
        mAdapter.addToEndChronologically(messages);


        //  messageList.setOnTouchListener(this);
        //输入 菜单项
        chatInputView = findViewById(R.id.chat_input);
        MenuManager menuManager = chatInputView.getMenuManager();
        menuManager.setMenu(Menu.newBuilder().
                customize(true).
                setRight(Menu.TAG_SEND).
                setBottom(Menu.TAG_VOICE, Menu.TAG_GALLERY, Menu.TAG_CAMERA).
                build());
        chatInputView.setMenuClickListener(new OnMenuClickListener() {
            @Override
            public boolean onSendTextMessage(final CharSequence input) {
                if (input.length() == 0) {
                    return false;
                }
                // m.setUserInfo(new DefaultUser(userInfo.getUserName() + "", userInfo.getNickname(), "R.drawable.iv_deicon"));
                JMessageClient.getUserInfo(getString(R.string.appidjiguang), new GetUserInfoCallback() {
                    @Override
                    public void gotResult(int i, String s, UserInfo userInfo) {
                        final MyMessage message = new MyMessage(input.toString(), IMessage.MessageType.SEND_TEXT.ordinal());
                        if (userInfo.getAvatar() != null) {
                            message.setUserInfo(new DefaultUser(userInfo.getUserName(), userInfo.getNickname(), userInfo.getAvatar()));
                        } else {
                            message.setUserInfo(new DefaultUser(userInfo.getUserName(), userInfo.getNickname(), "R.drawable.ironman"));
                        }

                        message.setTimeString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                        message.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                        mAdapter.addToStart(message, true);
                        messages.add(message);
                        Conversation c = null;
                        if (!TextUtils.isEmpty(targetId)) {
                            //单聊
                            c = conversation.createSingleConversation(targetId, appkey);
                        } else {
                            c = conversation.createGroupConversation(groupid);
                        }

// 参数：文本内容
                        TextContent text = new TextContent(input.toString());
                        Message textMessage = c.createSendMessage(text);
                        MessageSendingOptions options = new MessageSendingOptions();
                        options.setNeedReadReceipt(true);
                        JMessageClient.sendMessage(textMessage, options);

                        textMessage.setOnSendCompleteCallback(new BasicCallback() {
                            @Override
                            public void gotResult(int i, String s) {
                                if (i == 0) {
                                    Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
                                    message.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                                } else {
                                    HandleResponseCode.onHandle(getActivity(), i, false);
                                    message.setMessageStatus(IMessage.MessageStatus.SEND_FAILED);
                                }
                            }
                        });
                    }
                });


                return true;
            }

            @Override
            public void onSendFiles(List<FileItem> list) {

            }

            @Override
            public boolean switchToMicrophoneMode() {
                return false;
            }

            @Override
            public boolean switchToGalleryMode() {
                return false;
            }

            @Override
            public boolean switchToCameraMode() {
                return false;
            }

            @Override
            public boolean switchToEmojiMode() {
                return false;
            }
        });
        //相机
       // chatInputView.setCameraControllerListener(new OnCameraCallbackListener());


    }

    private void initMsgAdapter() {
        final float density = getResources().getDisplayMetrics().density;
        final float MIN_WIDTH = 60 * density;
        final float MAX_WIDTH = 200 * density;
        final float MIN_HEIGHT = 60 * density;
        final float MAX_HEIGHT = 200 * density;
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadAvatarImage(ImageView avatarImageView, String string) {
                Log.i("asdf", "loadAvatarImage  " + string);
                // You can use other image load libraries.
                if (string.contains("R.drawable")) {
                    Integer resId = getResources().getIdentifier(string.replace("R.drawable.", ""),
                            "drawable", getPackageName());

                    avatarImageView.setImageResource(resId);
                } else {
                    Glide.with(getActivity())
                            .load(string)
                            .apply(new RequestOptions().placeholder(R.drawable.aurora_headicon_default))
                            .into(avatarImageView);
                }
            }

            /**
             * Load image message
             * @param imageView Image message's ImageView.
             * @param string A file path, or a uri or url.
             */
            @Override
            public void loadImage(final ImageView imageView, String string) {
                // You can use other image load libraries.
                Glide.with(getApplicationContext())
                        .asBitmap()
                        .load(string)
                        .apply(new RequestOptions().fitCenter().placeholder(R.drawable.aurora_picture_not_found))
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                int imageWidth = resource.getWidth();
                                int imageHeight = resource.getHeight();
                                Log.d(TAG, "Image width " + imageWidth + " height: " + imageHeight);

                                // 裁剪 bitmap
                                float width, height;
                                if (imageWidth > imageHeight) {
                                    if (imageWidth > MAX_WIDTH) {
                                        float temp = MAX_WIDTH / imageWidth * imageHeight;
                                        height = temp > MIN_HEIGHT ? temp : MIN_HEIGHT;
                                        width = MAX_WIDTH;
                                    } else if (imageWidth < MIN_WIDTH) {
                                        float temp = MIN_WIDTH / imageWidth * imageHeight;
                                        height = temp < MAX_HEIGHT ? temp : MAX_HEIGHT;
                                        width = MIN_WIDTH;
                                    } else {
                                        float ratio = imageWidth / imageHeight;
                                        if (ratio > 3) {
                                            ratio = 3;
                                        }
                                        height = imageHeight * ratio;
                                        width = imageWidth;
                                    }
                                } else {
                                    if (imageHeight > MAX_HEIGHT) {
                                        float temp = MAX_HEIGHT / imageHeight * imageWidth;
                                        width = temp > MIN_WIDTH ? temp : MIN_WIDTH;
                                        height = MAX_HEIGHT;
                                    } else if (imageHeight < MIN_HEIGHT) {
                                        float temp = MIN_HEIGHT / imageHeight * imageWidth;
                                        width = temp < MAX_WIDTH ? temp : MAX_WIDTH;
                                        height = MIN_HEIGHT;
                                    } else {
                                        float ratio = imageHeight / imageWidth;
                                        if (ratio > 3) {
                                            ratio = 3;
                                        }
                                        width = imageWidth * ratio;
                                        height = imageHeight;
                                    }
                                }
                                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                                params.width = (int) width;
                                params.height = (int) height;
                                imageView.setLayoutParams(params);
                                Matrix matrix = new Matrix();
                                float scaleWidth = width / imageWidth;
                                float scaleHeight = height / imageHeight;
                                matrix.postScale(scaleWidth, scaleHeight);
                                imageView.setImageBitmap(Bitmap.createBitmap(resource, 0, 0, imageWidth, imageHeight, matrix, true));
                            }
                        });
            }

            /**
             * Load video message
             * @param imageCover Video message's image cover
             * @param uri Local path or url.
             */
            @Override
            public void loadVideo(ImageView imageCover, String uri) {
                long interval = 5000 * 1000;
                Glide.with(getActivity())
                        .asBitmap()
                        .load(uri)
                        // Resize image view by change override size.
                        .apply(new RequestOptions().frame(interval).override(200, 400))
                        .into(imageCover);
            }
        };

        // Use default layout
        MsgListAdapter.HoldersConfig holdersConfig = new MsgListAdapter.HoldersConfig();
        mAdapter = new MsgListAdapter<>("0", holdersConfig, imageLoader);
        // If you want to customise your layout, try to create custom ViewHolder:
        // holdersConfig.setSenderTxtMsg(CustomViewHolder.class, layoutRes);
        // holdersConfig.setReceiverTxtMsg(CustomViewHolder.class, layoutRes);
        // CustomViewHolder must extends ViewHolders defined in MsgListAdapter.
        // Current ViewHolders are TxtViewHolder, VoiceViewHolder.

        mAdapter.setOnMsgClickListener(new MsgListAdapter.OnMsgClickListener<MyMessage>() {
            @Override
            public void onMessageClick(MyMessage message) {
                // do something
                if (message.getType() == IMessage.MessageType.RECEIVE_VIDEO.ordinal()
                        || message.getType() == IMessage.MessageType.SEND_VIDEO.ordinal()) {
                    //跳转到videoactivity
                    if (!TextUtils.isEmpty(message.getMediaFilePath())) {
//                        Intent intent = new Intent(getActivity(), VideoActivity.class);
//                        intent.putExtra(VideoActivity.VIDEO_PATH, message.getMediaFilePath());
//                        startActivity(intent);
                    }
                } else if (message.getType() == IMessage.MessageType.RECEIVE_IMAGE.ordinal()
                        || message.getType() == IMessage.MessageType.SEND_IMAGE.ordinal()) {
                    //跳转到图片
//                    Intent intent = new Intent(MessageListActivity.this, BrowserImageActivity.class);
//                    intent.putExtra("msgId", message.getMsgId());
//                    intent.putStringArrayListExtra("pathList", mPathList);
//                    intent.putStringArrayListExtra("idList", mMsgIdList);
//                    startActivity(intent);
                } else {
//                    Toast.makeText(getApplicationContext(),
//                            getApplicationContext().getString(R.string.message_click_hint),
//                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAdapter.setMsgLongClickListener(new MsgListAdapter.OnMsgLongClickListener<MyMessage>() {
            @Override
            public void onMessageLongClick(View view, MyMessage message) {
                //长按事件
//                Toast.makeText(getApplicationContext(),
//                        getApplicationContext().getString(R.string.message_long_click_hint),
//                        Toast.LENGTH_SHORT).show();
                // do something
            }
        });

        mAdapter.setOnAvatarClickListener(new MsgListAdapter.OnAvatarClickListener<MyMessage>() {
            //头像点击事件
            @Override
            public void onAvatarClick(MyMessage message) {
                DefaultUser userInfo = (DefaultUser) message.getFromUser();
//                Toast.makeText(getApplicationContext(),
//                        getApplicationContext().getString(R.string.avatar_click_hint),
//                        Toast.LENGTH_SHORT).show();
                // do something
            }
        });

        mAdapter.setMsgStatusViewClickListener(new MsgListAdapter.OnMsgStatusViewClickListener<MyMessage>() {
            @Override
            public void onStatusViewClick(MyMessage message) {
                // message status view click, resend or download here
            }
        });


        messageList.setAdapter(mAdapter);
        mAdapter.getLayoutManager().scrollToPosition(0);
    }

    /**
     * 遍历
     *
     * @param allMessage
     */
    private void getMessages(List<cn.jpush.im.android.api.model.Message> allMessage) {
        for (cn.jpush.im.android.api.model.Message message : allMessage) {
            Log.i("asdf", "" + message.toString());
            MyMessage m = null;
            if (message.getContentType() == ContentType.text)//文字
            {
                TextContent text = (TextContent) message.getContent();
                if (message.getDirect() == MessageDirect.send)//发送方文本
                {
                    m = new MyMessage(text.getText(), IMessage.MessageType.SEND_TEXT.ordinal());
                } else//接收方文本
                {
                    m = new MyMessage(text.getText(), IMessage.MessageType.RECEIVE_TEXT.ordinal());
                }
            } else if (message.getContentType() == ContentType.image) {
                ImageContent imageContent = (ImageContent) message.getContent();
                if (message.getDirect() == MessageDirect.send)//发送方图片
                {
                    m = new MyMessage(null, IMessage.MessageType.SEND_IMAGE.ordinal());
                } else//接收方图片
                {
                    m = new MyMessage(null, IMessage.MessageType.RECEIVE_IMAGE.ordinal());
                }
                m.setMediaFilePath(imageContent.getLocalThumbnailPath());
            } else if (message.getContentType() == ContentType.video) {
                VideoContent videoContent = (VideoContent) message.getContent();
                if (message.getDirect() == MessageDirect.send)//发送方视频
                {
                    m = new MyMessage(null, IMessage.MessageType.SEND_VIDEO.ordinal());
                } else//接收方视频
                {
                    m = new MyMessage(null, IMessage.MessageType.RECEIVE_VIDEO.ordinal());
                }
                m.setDuration(videoContent.getDuration());
                m.setMediaFilePath(videoContent.getVideoLocalPath());
            } else if (message.getContentType() == ContentType.voice) {
                m = new MyMessage(null, IMessage.MessageType.RECEIVE_VOICE.ordinal());
            }
            if (m != null) {
                m.setTimeString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                        Locale.getDefault()).format(message.getCreateTime()));
                UserInfo userInfo = ((UserInfo) message.getTargetInfo());
                if (userInfo.getAvatar() == null) {
                    m.setUserInfo(new DefaultUser(userInfo.getUserName() + "", userInfo.getNickname(), "R.drawable.iv_deicon"));
                } else {

                    m.setUserInfo(new DefaultUser(userInfo.getUserName() + "", userInfo.getNickname(), userInfo.getAvatar()));
                }
                if (message.getDirect() == MessageDirect.send)//发送方视频
                {
                    m.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                } else {
                    m.setMessageStatus(IMessage.MessageStatus.RECEIVE_SUCCEED);
                }
                message.setHaveRead(new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                    }
                });

                // m.setUserInfo(new DefaultUser(userInfo.getUserName() + "", userInfo.getNickname(), userInfo.getAvatar()));
            }
            messages.add(m);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        returnBtn();
    }

    private void returnBtn() {
        conversation.resetUnreadCount();
        dismissSoftInput();
        JMessageClient.exitConversation();
        //    conversation.	setUnReadMessageCnt(messages.size());
        //ekBar发送
        //发送保存为草稿事件到会话列表
        EventBus.getDefault().post(new Event.Builder().setType(EventType.draft)
                .setConversation(conversation)
                //    .setDraft(ekBar.getEtChat().getText().toString())
                .build());
        finish();
    }

    private boolean mShowSoftInput = false;

    private void dismissSoftInput() {
        if (mShowSoftInput) {
            if (mImm != null) {
                //         mImm.hideSoftInputFromWindow(ekBar.getEtChat().getWindowToken(), 0);
                mShowSoftInput = false;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
