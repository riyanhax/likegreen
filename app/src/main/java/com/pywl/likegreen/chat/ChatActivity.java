package com.pywl.likegreen.chat;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.pywl.likegreen.MyApplication;
import com.pywl.likegreen.R;
import com.pywl.likegreen.base.BaseActivity;
import com.pywl.likegreen.chat.bean.DefaultUser;
import com.pywl.likegreen.chat.bean.MyMessage;
import com.pywl.likegreen.chat.constant.ChatConstant;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.imui.commons.ImageLoader;
import cn.jiguang.imui.messages.MessageList;
import cn.jiguang.imui.messages.MsgListAdapter;
import cn.jiguang.imui.messages.ptr.PtrDefaultHeader;
import cn.jiguang.imui.messages.ptr.PtrHandler;
import cn.jiguang.imui.messages.ptr.PullToRefreshLayout;
import cn.jiguang.imui.utils.DisplayUtil;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Conversation;

public class ChatActivity extends BaseActivity {
    Conversation conversation;
    MsgListAdapter adapter = null;

    List<MyMessage> messages = new ArrayList<>();//适配器message

    Activity getActivity() {
        return ChatActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String targetId = getIntent().getStringExtra(MyApplication.TARGET_ID); //获取用户的username
        String type = getIntent().getStringExtra(MyApplication.message_tyoe);//回话类别
        String appkey = getIntent().getStringExtra(MyApplication.TARGET_APP_KEY);//获取会话target appkey,只有单聊会话中会有target appkey这个概念，群聊和聊天室类型会话直接返回空字符串
        String id = getIntent().getStringExtra(MyApplication.DRAFT);//获取Conversation的ID
        long groupid = getIntent().getLongExtra(MyApplication.GROUP_ID, 0);
        final PullToRefreshLayout ptrLayout = (PullToRefreshLayout) findViewById(R.id.pull_to_refresh_layout);
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

        MessageList messageList = (MessageList) findViewById(R.id.msg_list);
        messageList.setShowSenderDisplayName(true);
        messageList.setShowReceiverDisplayName(true);
        //MsgListAdapter adapter = new MsgListAdapter<>("0", holdersConfig, imageLoader);
        //     messageList.setAdapter(adapter);
        MsgListAdapter.HoldersConfig holdersConfig = new MsgListAdapter.HoldersConfig();
        final float density = getResources().getDisplayMetrics().density;
        final float MIN_WIDTH = 60 * density;
        final float MAX_WIDTH = 200 * density;
        final float MIN_HEIGHT = 60 * density;
        final float MAX_HEIGHT = 200 * density;
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadAvatarImage(ImageView avatarImageView, String string) {
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
                                Log.d("asdf", "Image width " + imageWidth + " height: " + imageHeight);

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
        if (!TextUtils.isEmpty(targetId)) {
            //单聊
            conversation = JMessageClient.getSingleConversation(targetId, appkey);
            adapter = new MsgListAdapter<>(targetId, holdersConfig, imageLoader);
        } else//群聊
        {
            conversation = JMessageClient.getGroupConversation(groupid);
            adapter = new MsgListAdapter<>(groupid + "", holdersConfig, imageLoader);
        }
        messageList.setAdapter(adapter);
        List<cn.jpush.im.android.api.model.Message> allMessage = conversation.getAllMessage();//会话message
        getAllMessage(allMessage);
        adapter.addToEnd(messages);
    }

    /**
     * 遍历
     *
     * @param allMessage
     */
    private void getAllMessage(List<cn.jpush.im.android.api.model.Message> allMessage) {
        for (cn.jpush.im.android.api.model.Message message : allMessage) {
            Log.i("asdf", "message " + message.toString());
            MyMessage m = null;
            if (message.getContentType() == ContentType.text)//文字
            {
                TextContent text = (TextContent) message.getContent();
                m = new MyMessage(text.getText(), ChatConstant.TYPE_TEXT);

            } else if (message.getContentType() == ContentType.image) {
                ImageContent imageContent = (ImageContent) message.getContent();
                m = new MyMessage(null, ChatConstant.TYPE_IMAGE);
                m.setMediaFilePath(imageContent.getLocalThumbnailPath());


            } else if (message.getContentType() == ContentType.video) {
                m = new MyMessage(null, ChatConstant.TYPE_VIDEO);

            } else if (message.getContentType() == ContentType.voice) {
                m = new MyMessage(null, ChatConstant.TYPE_VOICE);
            }
            if (m != null) {
                m.setTimeString(message.getCreateTime() + "");
                m.setUserInfo(new DefaultUser(message.getFromUser().getUserID() + "", message.getFromUser().getUserName(), message.getFromUser().getAvatar()));
            }
            messages.add(m);
        }

    }
}
