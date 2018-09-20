package com.pywl.likegreen.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.jiguang.imui.chatinput.ChatInputView;
import cn.jiguang.imui.chatinput.listener.CameraControllerListener;
import cn.jiguang.imui.chatinput.listener.OnCameraCallbackListener;
import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
import cn.jiguang.imui.chatinput.listener.RecordVoiceListener;
import cn.jiguang.imui.chatinput.menu.Menu;
import cn.jiguang.imui.chatinput.menu.MenuManager;
import cn.jiguang.imui.chatinput.model.FileItem;
import cn.jiguang.imui.chatinput.model.VideoItem;
import cn.jiguang.imui.chatinput.record.RecordVoiceButton;
import cn.jiguang.imui.commons.ImageLoader;
import cn.jiguang.imui.commons.models.IMessage;
import cn.jiguang.imui.messages.MessageList;
import cn.jiguang.imui.messages.MsgListAdapter;
import cn.jiguang.imui.messages.ptr.PtrDefaultHeader;
import cn.jiguang.imui.utils.DisplayUtil;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.content.FileContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.exceptions.JMFileSizeExceedException;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import cn.jpush.im.android.eventbus.EventBus;
import cn.jpush.im.api.BasicCallback;
import jiguang.chat.entity.Event;
import jiguang.chat.entity.EventType;
import jiguang.chat.pickerimage.uitls.BitmapDecoder;
import jiguang.chat.utils.FileHelper;
import jiguang.chat.utils.HandleResponseCode;

public class ChatActivity extends BaseActivity {
    /**
     * so that click image message can browser all images.
     */
    private ArrayList<String> mPathList = new ArrayList<>();//图片  视频 集合


    private MsgListAdapter<MyMessage> mAdapter;
    private static final String TAG = "asdf";
    Conversation conversation;

    //  PullToRefreshLayout ptrLayout;
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

    String myusername = "";
    String myusernickname = "";
    String myavater = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initImageload();
        JMessageClient.registerEventReceiver(this);
        // Use default layout
        MsgListAdapter.HoldersConfig holdersConfig = new MsgListAdapter.HoldersConfig();
        mAdapter = new MsgListAdapter<>("0", holdersConfig, imageLoader);
        this.mImm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        myusername = getIntent().getStringExtra(MyApplication.myusername); //获取我的的username
        myusernickname = getIntent().getStringExtra(MyApplication.myusernickername); //获取我的用户的username
        myavater = getIntent().getStringExtra(MyApplication.myuseravater); //获取我的用户的username

        targetId = getIntent().getStringExtra(MyApplication.TARGET_ID); //获取用户的username
        type = getIntent().getStringExtra(MyApplication.message_tyoe);//回话类别
        appkey = getIntent().getStringExtra(MyApplication.TARGET_APP_KEY);//获取会话target appkey,只有单聊会话中会有target appkey这个概念，群聊和聊天室类型会话直接返回空字符串
        id = getIntent().getStringExtra(MyApplication.DRAFT);//获取Conversation的ID
        groupid = getIntent().getLongExtra(MyApplication.GROUP_ID, 0);
        //  ptrLayout = (PullToRefreshLayout) findViewById(R.id.pull_to_refresh_layout);
        PtrDefaultHeader header = new PtrDefaultHeader(getActivity());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        header.setPadding(0, DisplayUtil.dp2px(getActivity(), 15), 0, DisplayUtil.dp2px(getActivity(), 10));
//        header.setPtrFrameLayout(ptrLayout);
        //   ptrLayout.setLoadingMinTime(1000);
        //    ptrLayout.setDurationToCloseHeader(1500);
        //    ptrLayout.setHeaderView(header);
        //   ptrLayout.addPtrUIHandler(header);
// 如果设置为 true，下拉刷新时，内容固定，只有 Header 变化
        //   ptrLayout.setPinContent(true);
//        ptrLayout.setPtrHandler(new PtrHandler() {
//            @Override
//            public void onRefreshBegin(PullToRefreshLayout layout) {
//                Log.i("asdf", "Loading next page");
//                //loadNextPage();
//                // 加载完历史消息后调用
//                ptrLayout.refreshComplete();
//            }
//        });

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
        initMsgAdapter();
        List<Message> allMessage = conversation.getAllMessage();//会话message
        getMessages(allMessage);


        //    messageList.setAdapter(adapter);
        mAdapter.addToEndChronologically(messages);


        //  messageList.setOnTouchListener(this);
        //输入 菜单项
        chatInputView = findViewById(R.id.chat_input);
        //  chatInputView.setCameraCaptureFile(path, fileName);//拍摄路径
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
                        if (myavater != null) {
                            message.setUserInfo(new DefaultUser(myusername, myusernickname, userInfo.getAvatarFile().getAbsolutePath()));
                        } else {
                            message.setUserInfo(new DefaultUser(myusername, myusernickname, userInfo.getAvatarFile().getAbsolutePath()));
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
            public void onSendFiles(final List<FileItem> list) {

                if (list == null) {
                    return;
                }
                JMessageClient.getUserInfo(myusername, new GetUserInfoCallback() {
                    @Override
                    public void gotResult(int i, String s, UserInfo userInfo) {
                        if (i == 0) {
                            for (int j = 0; j < list.size(); j++) {
                                FileItem item = list.get(j);
                                MyMessage message = null;
                                if (item.getType() == FileItem.Type.Image) {
                                    message = new MyMessage(null, IMessage.MessageType.SEND_IMAGE.ordinal());
                                    message.setMediaFilePath(item.getFilePath());
                                } else if (item.getType() == FileItem.Type.Video) {
                                    message = new MyMessage(null, IMessage.MessageType.SEND_VIDEO.ordinal());
                                    message.setDuration(((VideoItem) item).getDuration());
                                    message.setMediaFilePath(item.getFilePath());
                                } else {
                                    throw new RuntimeException("Invalid FileItem type. Must be Type.Image or Type.Video");
                                }

                                message.setTimeString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                                message.setMediaFilePath(item.getFilePath());
                                message.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);

                                if (myavater != null) {
                                    message.setUserInfo(new DefaultUser(myusername, myusernickname, userInfo.getAvatarFile().getAbsolutePath()));
                                } else {
                                    message.setUserInfo(new DefaultUser(myusername, myusernickname, "R.drawable.ironman"));
                                }

                                final MyMessage fMsg = message;


                                for (final FileItem file : list) {
                                    if (item.getType() == FileItem.Type.Image) {
                                        //所有图片都在这里拿到
                                        ImageContent.createImageContentAsync(new File(file.getFilePath()), new ImageContent.CreateImageContentCallback() {
                                            @Override
                                            public void gotResult(int responseCode, String responseMessage, ImageContent imageContent) {
                                                if (responseCode == 0) {
                                                    final Message msg = conversation.createSendMessage(imageContent);
                                                    MessageSendingOptions options = new MessageSendingOptions();
                                                    options.setNeedReadReceipt(true);
                                                    JMessageClient.sendMessage(msg, options);

                                                    msg.setOnSendCompleteCallback(new BasicCallback() {
                                                        @Override
                                                        public void gotResult(int i, String s) {
                                                            if (i == 0) {
                                                                Log.i("asdf", "发送图片成功");
                                                                mPathList.add(file.getFilePath());
                                                                getActivity().runOnUiThread(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        mAdapter.addToStart(fMsg, true);
                                                                    }
                                                                });
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        });
                                    } else if (item.getType() == FileItem.Type.Video) {
                                        FileContent fileContent = null;
                                        try {
                                            fileContent = new FileContent(new File(file.getFilePath()));
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (JMFileSizeExceedException e) {
                                            e.printStackTrace();
                                        }
                                        fileContent.setStringExtra("video", "mp4");
                                        final Message msg = conversation.createSendMessage(fileContent);
                                        MessageSendingOptions options = new MessageSendingOptions();
                                        options.setNeedReadReceipt(true);
                                        JMessageClient.sendMessage(msg, options);

                                        msg.setOnSendCompleteCallback(new BasicCallback() {
                                            @Override
                                            public void gotResult(int i, String s) {
                                                mPathList.add(file.getFilePath());
                                                getActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        mAdapter.addToStart(fMsg, true);
                                                    }
                                                });
                                            }
                                        });
                                    } else {
                                        throw new RuntimeException("Invalid FileItem type. Must be Type.Image or Type.Video");
                                    }
                                }
                            }
                        }

                    }
                });


            }

            @Override
            public boolean switchToMicrophoneMode() {
                Log.i("asdf", "switchToMicrophoneMode");
                return true;
            }

            @Override
            public boolean switchToGalleryMode() {
                Log.i("asdf", "switchToMicrophoneMode");
                return true;
            }

            @Override
            public boolean switchToCameraMode() {
                Log.i("asdf", "switchToCameraMode");
                return true;
            }

            @Override
            public boolean switchToEmojiMode() {
                Log.i("asdf", "switchToEmojiMode");
                return true;
            }
        });
        final RecordVoiceButton mRecordVoiceBtn = chatInputView.getRecordVoiceButton();
        mRecordVoiceBtn.setRecordVoiceListener(new RecordVoiceListener() {
            @Override
            public void onStartRecord() {
                // Show record voice interface
                // 设置存放录音文件目录
                File rootDir = getActivity().getFilesDir();
                String fileDir = rootDir.getAbsolutePath() + "/voice";
                mRecordVoiceBtn.setVoiceFilePath(fileDir, new Date().getTime() + ".mp3");
            }

            @Override
            public void onFinishRecord(final File voiceFile, final int duration) {

                JMessageClient.getUserInfo(myusername, new GetUserInfoCallback() {
                    @Override
                    public void gotResult(int i, String s, UserInfo userInfo) {
                        if (i==0)
                        {
                            Conversation c = null;
                            if (!TextUtils.isEmpty(targetId)) {
                                //单聊
                                c = conversation.createSingleConversation(targetId, appkey);
                            } else {
                                c = conversation.createGroupConversation(groupid);
                            }
                            final MyMessage message = new MyMessage(null, IMessage.MessageType.SEND_VOICE.ordinal());
                            message.setMediaFilePath(voiceFile.getPath());
                            message.setDuration(duration);
                            message.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                            message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
                            if (myavater != null) {
                                message.setUserInfo(new DefaultUser(userInfo.getUserName(), userInfo.getNickname(), userInfo.getAvatarFile().getAbsolutePath()));
                            } else {
                                message.setUserInfo(new DefaultUser(userInfo.getUserName(), userInfo.getNickname(), userInfo.getAvatarFile().getAbsolutePath()));
                            }
                            // 参数：文本内容
                            VoiceContent voicecontent = null;
                            try {
                                voicecontent = new VoiceContent(voiceFile, duration);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            Message voicemessage = c.createSendMessage(voicecontent);
                            MessageSendingOptions options = new MessageSendingOptions();

                            options.setNeedReadReceipt(true);
                            JMessageClient.sendMessage(voicemessage, options);

                            voicemessage.setOnSendCompleteCallback(new BasicCallback() {
                                @Override
                                public void gotResult(int i, String s) {
                                    if (i == 0) {
                                        Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
                                        message.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                                        mAdapter.addToStart(message, true);
                                    } else {
                                        HandleResponseCode.onHandle(getActivity(), i, false);
                                        message.setMessageStatus(IMessage.MessageStatus.SEND_FAILED);
                                    }
                                }
                            });
                        }
                    }
                });

            }

            @Override
            public void onCancelRecord() {
                Log.i("asdf", "onCancelRecord");
            }

            /**
             * 录音试听界面，点击取消按钮触发
             * 0.7.3 后添加此事件
             */
            @Override
            public void onPreviewCancel() {
                Log.i("asdf", "onPreviewCancel");
            }

            /**
             * 录音试听界面，点击发送按钮触发
             * 0.7.3 后增加此事件
             */
            @Override
            public void onPreviewSend() {
                Log.i("asdf", "onPreviewSend");
            }
        });
//camera
        chatInputView.setOnCameraCallbackListener(new OnCameraCallbackListener() {
            @Override
            public void onTakePictureCompleted(final String photoPath) {
                JMessageClient.getUserInfo(myusername, new GetUserInfoCallback() {
                    @Override
                    public void gotResult(int i, String s, UserInfo userInfo) {
                        if (i == 0) {
                            Log.i("asdf", "onTakePictureCompleted");
                            MyMessage message = null;
                            message = new MyMessage(null, IMessage.MessageType.SEND_IMAGE.ordinal());
                            message.setMediaFilePath(photoPath);
                            message.setTimeString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                            message.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                            if (myavater != null) {
                                message.setUserInfo(new DefaultUser(userInfo.getUserName(), userInfo.getNickname(), userInfo.getAvatarFile().getAbsolutePath()));
                            } else {
                                message.setUserInfo(new DefaultUser(userInfo.getUserName(), userInfo.getNickname(), userInfo.getAvatarFile().getAbsolutePath()));
                            }
                            final MyMessage fMsg = message;

                            //所有图片都在这里拿到
                            ImageContent.createImageContentAsync(new File(photoPath), new ImageContent.CreateImageContentCallback() {
                                @Override
                                public void gotResult(int responseCode, String responseMessage, ImageContent imageContent) {
                                    if (responseCode == 0) {
                                        Message msg = conversation.createSendMessage(imageContent);
                                        MessageSendingOptions options = new MessageSendingOptions();
                                        options.setNeedReadReceipt(true);
                                        JMessageClient.sendMessage(msg, options);
                                        mPathList.add(photoPath);
                                        msg.setOnSendCompleteCallback(new BasicCallback() {
                                            @Override
                                            public void gotResult(int i, String s) {
                                                if (i == 0) {
                                                    Log.i("asdf", "发送图片成功");
                                                    getActivity().runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            mAdapter.addToStart(fMsg, true);
                                                        }
                                                    });
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }
                });


            }

            @Override
            public void onStartVideoRecord() {
                Log.i("asdf", "onStartVideoRecord");
            }

            @Override
            public void onFinishVideoRecord(String videoPath) {
                Log.i("asdf", "onFinishVideoRecord");
            }

            @Override
            public void onCancelVideoRecord() {
                Log.i("asdf", "onCancelVideoRecord");
            }

        });
        chatInputView.setCameraControllerListener(new CameraControllerListener() {
            @Override
            public void onFullScreenClick() {
                Log.i("asdf", "onFullScreenClick");
            }

            @Override
            public void onRecoverScreenClick() {
                Log.i("asdf", "onRecoverScreenClick");
            }

            @Override
            public void onCloseCameraClick() {
                Log.i("asdf", "onCloseCameraClick");
            }

            @Override
            public void onSwitchCameraModeClick(boolean isRecordVideoMode) {
                Log.i("asdf", "onSwitchCameraModeClick");
            }
        });

    }

    float density = 0;
    float MIN_WIDTH = 0;
    float MAX_WIDTH = 0;
    float MIN_HEIGHT = 0;
    float MAX_HEIGHT = 0;
    ImageLoader imageLoader = null;

    private void initImageload() {
        density = getResources().getDisplayMetrics().density;
        MIN_WIDTH = 60 * density;
        MAX_WIDTH = 200 * density;
        MIN_HEIGHT = 60 * density;
        MAX_HEIGHT = 200 * density;
        imageLoader = new ImageLoader() {
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
                Log.i("asdf", "loadImage  " + string);
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
//                Log.i("asdf","loadVideo"+uri);
//                Log.i("asdf","loadVideo"+new File(uri).exists());
                long interval = 5000 * 1000;
                Glide.with(getActivity())
                        .load(uri)
                        // Resize image view by change override size.
                        .apply(new RequestOptions().frame(interval).override(200, 400))
                        .into(imageCover);
            }
        };
    }

    private void initMsgAdapter() {


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
                        Intent intent = new Intent(getActivity(), ChatItemActivity.class);
                        intent.putExtra("path", message.getMediaFilePath());
                        intent.putStringArrayListExtra("pathList", mPathList);
                        startActivity(intent);
                    }
                } else if (message.getType() == IMessage.MessageType.RECEIVE_IMAGE.ordinal()
                        || message.getType() == IMessage.MessageType.SEND_IMAGE.ordinal()) {
                    //跳转到图片
                    Intent intent = new Intent(getActivity(), ChatItemActivity.class);
                    intent.putExtra("path", message.getMediaFilePath());
                    intent.putStringArrayListExtra("pathList", mPathList);
                    startActivity(intent);
                } else {
//                    Toast.makeText(getApplicationContext(),
//                            "其他",
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
    private void getMessages(List<Message> allMessage) {
        for (final Message message : allMessage) {
            if (message.getContentType() == ContentType.text)//文字
            {
                MyMessage m = null;
                TextContent text = (TextContent) message.getContent();
                if (message.getDirect() == MessageDirect.send)//发送方文本
                {
                    m = new MyMessage(text.getText(), IMessage.MessageType.SEND_TEXT.ordinal());
                } else//接收方文本
                {
                    m = new MyMessage(text.getText(), IMessage.MessageType.RECEIVE_TEXT.ordinal());
                }
                setMessageStatus(m, message);
                messages.add(m);
            } else if (message.getContentType() == ContentType.image) {
                final ImageContent imageContent = (ImageContent) message.getContent();
              //  File file = new File(imageContent.getLocalThumbnailPath());

                if (imageContent.getLocalThumbnailPath()!=null&&!imageContent.getLocalThumbnailPath().equals("")) {
                    MyMessage m = null;
                    if (message.getDirect() == MessageDirect.send)//发送方图片
                    {
                        m = new MyMessage(null, IMessage.MessageType.SEND_IMAGE.ordinal());
                    } else//接收方图片
                    {
                        m = new MyMessage(null, IMessage.MessageType.RECEIVE_IMAGE.ordinal());
                    }
                    setMessageStatus(m, message);
                    m.setMediaFilePath(imageContent.getLocalThumbnailPath());
                    messages.add(m);
                    mPathList.add(imageContent.getLocalThumbnailPath());
                } else//不存在本地
                {

                    imageContent.downloadOriginImage(message, new DownloadCompletionCallback() {
                        @Override
                        public void onComplete(int i, String s, File file) {
                            if (i == 0) {
                                MyMessage m = null;
                                if (message.getDirect() == MessageDirect.send)//发送方图片
                                {
                                    m = new MyMessage(null, IMessage.MessageType.SEND_IMAGE.ordinal());
                                } else//接收方图片
                                {
                                    m = new MyMessage(null, IMessage.MessageType.RECEIVE_IMAGE.ordinal());
                                }
                                setMessageStatus(m, message);
                                m.setMediaFilePath(file.getAbsolutePath());
                                messages.add(m);
                                mPathList.add(file.getAbsolutePath());
                            }
                        }
                    });
                }


            } else if (message.getContentType() == ContentType.video || message.getContentType() == ContentType.file) {
//                FileContent fileContent = (FileContent) message.getContent();
//                String extra = fileContent.getStringExtra("video");
//                String videoPath = fileContent.getLocalPath();
//                //判断视频是否存在
//                if (videoPath != null && !videoPath.equals("")) {

//                } else {
//                    fileContent.downloadFile(message, new DownloadCompletionCallback() {
//                        @Override
//                        public void onComplete(int i, String s, File file) {
//                            if (i == 0) {
//                                String fff=file.getAbsolutePath();
//                                MyMessage m = null;
//                                if (message.getDirect() == MessageDirect.send)//发送方视频
//                                {
//                                    m = new MyMessage(null, IMessage.MessageType.SEND_VIDEO.ordinal());
//                                } else//接收方视频
//                                {
//                                    m = new MyMessage(null, IMessage.MessageType.RECEIVE_VIDEO.ordinal());
//                                }
//                                setMessageStatus(m, message);
//                                m.setMediaFilePath(file.getAbsolutePath());
//                                mPathList.add(file.getAbsolutePath());
//                                messages.add(m);
//                            }
//                        }
//                    });
//                }

                FileContent content = (FileContent) message.getContent();
                String fileName = content.getFileName();
                String extra = content.getStringExtra("video");
                if (extra != null) {
                    fileName = message.getServerMessageId() + "." + extra;
                }
                final String path = content.getLocalPath();
                if (path != null && new File(path).exists()) {
                    final String newPath = MyApplication.FILE_DIR + fileName;
                    File file = new File(newPath);
                    if (file.exists() && file.isFile()) {
                                            MyMessage m = null;
                    if (message.getDirect() == MessageDirect.send)//发送方视频
                    {
                        m = new MyMessage(null, IMessage.MessageType.SEND_VIDEO.ordinal());
                    } else//接收方视频
                    {
                        m = new MyMessage(null, IMessage.MessageType.RECEIVE_VIDEO.ordinal());
                    }

                    setMessageStatus(m, message);
                    m.setMediaFilePath(newPath);
                    mPathList.add(newPath);
                    messages.add(m);
                    } else {
                        final String finalFileName = fileName;
                        FileHelper.getInstance().copyFile(fileName, path, getActivity(),
                                new FileHelper.CopyFileCallback() {
                                    @Override
                                    public void copyCallback(Uri uri) {
                                        Log.i("asdf","copyCallback"+newPath);
                                        MyMessage m = null;
                                        if (message.getDirect() == MessageDirect.send)//发送方视频
                                        {
                                            m = new MyMessage(null, IMessage.MessageType.SEND_VIDEO.ordinal());
                                        } else//接收方视频
                                        {
                                            m = new MyMessage(null, IMessage.MessageType.RECEIVE_VIDEO.ordinal());
                                        }

                                        setMessageStatus(m, message);
                                        m.setMediaFilePath(newPath);
                                        mPathList.add(newPath);
                                        messages.add(m);
                                    }
                                });
                    }
                }

            } else if (message.getContentType() == ContentType.voice) {

                final VoiceContent voiceContent = (VoiceContent) message.getContent();
                File voicefile = new File(voiceContent.getLocalPath());
                if (voicefile != null && voicefile.exists())//声音文件存在
                {
                    MyMessage m = null;
                    if (message.getDirect() == MessageDirect.send)//
                    {
                        m = new MyMessage(null, IMessage.MessageType.SEND_VOICE.ordinal());
                    } else//
                    {
                        m = new MyMessage(null, IMessage.MessageType.RECEIVE_VOICE.ordinal());
                    }
                    setMessageStatus(m, message);
                    m.setDuration(voiceContent.getDuration());
                    m.setMediaFilePath(voiceContent.getLocalPath());
                    messages.add(m);
                } else {
                    voiceContent.downloadVoiceFile(message, new DownloadCompletionCallback() {
                        @Override
                        public void onComplete(int i, String s, File file) {
                            if (i == 0) {
                                MyMessage m = null;
                                if (message.getDirect() == MessageDirect.send)//
                                {
                                    m = new MyMessage(null, IMessage.MessageType.SEND_VOICE.ordinal());
                                } else//
                                {
                                    m = new MyMessage(null, IMessage.MessageType.RECEIVE_VOICE.ordinal());
                                }
                                setMessageStatus(m, message);
                                m.setDuration(voiceContent.getDuration());
                                m.setMediaFilePath(file.getAbsolutePath());
                                messages.add(m);
                            }
                        }
                    });
                }

            } else {
                continue;
            }


        }

    }

    private void setMessageStatus(MyMessage m, Message message) {
        if (m != null) {
            m.setTimeString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()).format(message.getCreateTime()));
            Log.i("asdf", "" + message.getFromUser().getAvatar() + "  ");
            if (message.getFromUser().getAvatar() == null) {
                m.setUserInfo(new DefaultUser(message.getFromUser().getUserName() + "", message.getFromUser().getNickname(), "R.drawable.iv_deicon"));
            } else {

                m.setUserInfo(new DefaultUser(message.getFromUser().getUserName() + "", message.getFromUser().getNickname(), message.getFromUser().getAvatarFile().getAbsolutePath()));
            }
            if (message.getDirect() == MessageDirect.send) {
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        returnBtn();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
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

    public void onEvent(MessageEvent event) {
        Log.i("asdf", "onEvent " + event.getMessage().toString());
        addMessageJudge(event.getMessage());
    }

    private void addMessageJudge(final Message message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (message.getTargetType() == ConversationType.single) {
                    addMessage(message);
                } else {
                    addMessage(message);
                }
            }
        });
    }

    private void addMessage(final Message message) {
        if (message.getContentType() == ContentType.text)//文字
        {
            MyMessage m = null;
            TextContent text = (TextContent) message.getContent();
            if (message.getDirect() == MessageDirect.send)//发送方文本
            {
                m = new MyMessage(text.getText(), IMessage.MessageType.SEND_TEXT.ordinal());
            } else//接收方文本
            {
                m = new MyMessage(text.getText(), IMessage.MessageType.RECEIVE_TEXT.ordinal());
            }
            setMessageStatus(m, message);
            messages.add(m);
            if (mAdapter != null) {
                mAdapter.addToStart(m, true);
            }
        } else if (message.getContentType() == ContentType.image) {
            final ImageContent imageContent = (ImageContent) message.getContent();
            if (imageContent.getLocalThumbnailPath()!=null&&!imageContent.getLocalThumbnailPath().equals("")) {
                MyMessage m = null;
                if (message.getDirect() == MessageDirect.send)//发送方图片
                {
                    m = new MyMessage(null, IMessage.MessageType.SEND_IMAGE.ordinal());
                } else//接收方图片
                {
                    m = new MyMessage(null, IMessage.MessageType.RECEIVE_IMAGE.ordinal());
                }
                setMessageStatus(m, message);
                m.setMediaFilePath(imageContent.getLocalThumbnailPath());
                messages.add(m);
                if (mAdapter != null) {
                    mAdapter.addToStart(m, true);
                }
                mPathList.add(imageContent.getLocalThumbnailPath());
            } else//不存在本地
            {

                imageContent.downloadOriginImage(message, new DownloadCompletionCallback() {
                    @Override
                    public void onComplete(int i, String s, File file) {
                        if (i == 0) {
                            MyMessage m = null;
                            if (message.getDirect() == MessageDirect.send)//发送方图片
                            {
                                m = new MyMessage(null, IMessage.MessageType.SEND_IMAGE.ordinal());
                            } else//接收方图片
                            {
                                m = new MyMessage(null, IMessage.MessageType.RECEIVE_IMAGE.ordinal());
                            }
                            setMessageStatus(m, message);
                            m.setMediaFilePath(file.getAbsolutePath());
                            messages.add(m);

                            if (mAdapter != null) {
                                mAdapter.addToStart(m, true);
                            }
                            mPathList.add(file.getAbsolutePath());
                        }
                    }
                });
            }


        } else if (message.getContentType() == ContentType.video || message.getContentType() == ContentType.file) {
            FileContent content = (FileContent) message.getContent();
            String fileName = content.getFileName();
            String extra = content.getStringExtra("video");
            if (extra != null) {
                fileName = message.getServerMessageId() + "." + extra;
            }
            final String path = content.getLocalPath();
            if (path != null && new File(path).exists()) {
                final String newPath = MyApplication.FILE_DIR + fileName;
                File file = new File(newPath);
                if (file.exists() && file.isFile()) {
                    MyMessage m = null;
                    if (message.getDirect() == MessageDirect.send)//发送方视频
                    {
                        m = new MyMessage(null, IMessage.MessageType.SEND_VIDEO.ordinal());
                    } else//接收方视频
                    {
                        m = new MyMessage(null, IMessage.MessageType.RECEIVE_VIDEO.ordinal());
                    }

                    setMessageStatus(m, message);
                    m.setMediaFilePath(newPath);
                    mPathList.add(newPath);
                    messages.add(m);
                } else {
                    final String finalFileName = fileName;
                    FileHelper.getInstance().copyFile(fileName, path, getActivity(),
                            new FileHelper.CopyFileCallback() {
                                @Override
                                public void copyCallback(Uri uri) {
                                    Log.i("asdf","copyCallback"+newPath);
                                    MyMessage m = null;
                                    if (message.getDirect() == MessageDirect.send)//发送方视频
                                    {
                                        m = new MyMessage(null, IMessage.MessageType.SEND_VIDEO.ordinal());
                                    } else//接收方视频
                                    {
                                        m = new MyMessage(null, IMessage.MessageType.RECEIVE_VIDEO.ordinal());
                                    }

                                    setMessageStatus(m, message);
                                    m.setMediaFilePath(newPath);
                                    mPathList.add(newPath);
                                    messages.add(m);
                                }
                            });
                }
            }

        } else if (message.getContentType() == ContentType.voice) {

            final VoiceContent voiceContent = (VoiceContent) message.getContent();
            File voicefile = new File(voiceContent.getLocalPath());
            if (voicefile != null && voicefile.exists())//声音文件存在
            {
                MyMessage m = null;
                if (message.getDirect() == MessageDirect.send)//
                {
                    m = new MyMessage(null, IMessage.MessageType.SEND_VOICE.ordinal());
                } else//
                {
                    m = new MyMessage(null, IMessage.MessageType.RECEIVE_VOICE.ordinal());
                }
                setMessageStatus(m, message);
                m.setDuration(voiceContent.getDuration());
                m.setMediaFilePath(voiceContent.getLocalPath());
             //   mPathList.add(message);
                messages.add(m);
                if (mAdapter != null) {
                    mAdapter.addToStart(m, true);
                }
            } else {
                voiceContent.downloadVoiceFile(message, new DownloadCompletionCallback() {
                    @Override
                    public void onComplete(int i, String s, File file) {
                        if (i == 0) {
                            MyMessage m = null;
                            if (message.getDirect() == MessageDirect.send)//
                            {
                                m = new MyMessage(null, IMessage.MessageType.SEND_VOICE.ordinal());
                            } else//
                            {
                                m = new MyMessage(null, IMessage.MessageType.RECEIVE_VOICE.ordinal());
                            }
                            setMessageStatus(m, message);
                            m.setDuration(voiceContent.getDuration());
                            m.setMediaFilePath(file.getAbsolutePath());
                          //  mPathList.add(message);
                            messages.add(m);
                            if (mAdapter != null) {
                                mAdapter.addToStart(m, true);
                            }
                        }
                    }
                });
            }

        } else {

        }

    }
}
