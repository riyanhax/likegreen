package com.pywl.likegreen.chat.controller;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.pywl.likegreen.MyApplication;
import com.pywl.likegreen.R;
import com.pywl.likegreen.chat.ChatActivity;
import com.pywl.likegreen.chat.adapter.ConversationListAdapter;
import com.pywl.likegreen.fragment.mine.messagedirect.MyDirectFragment;
import com.pywl.likegreen.fragment.mine.messagedirect.widget.ConversationListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;
import jiguang.chat.utils.DialogCreator;
import jiguang.chat.utils.SortConvList;
import jiguang.chat.utils.SortTopConvList;


/**
 * Created by ${chenyn} on 2017/2/20.
 */

public class ConversationListController implements
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {

    private ConversationListView mConvListView;
    private MyDirectFragment mContext;
    private int mWidth;
    private ConversationListAdapter mListAdapter;
    private List<Conversation> mDatas = new ArrayList<Conversation>();
    private Dialog mDialog;

    public ConversationListController(ConversationListView listView, MyDirectFragment context,
                                      int width) {
        this.mConvListView = listView;
        this.mContext = context;
        this.mWidth = width;
        initConvListAdapter();
    }

    List<Conversation> topConv = new ArrayList<>();
    List<Conversation> forCurrent = new ArrayList<>();
    List<Conversation> delFeedBack = new ArrayList<>();

    private void initConvListAdapter() {
        forCurrent.clear();
        topConv.clear();
        delFeedBack.clear();
        int i = 0;
        mDatas = JMessageClient.getConversationList();
        if (mDatas != null && mDatas.size() > 0) {
            mConvListView.setNullConversation(true);
            SortConvList sortConvList = new SortConvList();
            Collections.sort(mDatas, sortConvList);
            for (Conversation con : mDatas) {
                if (con.getTargetId().equals("feedback_Android")) {
                    delFeedBack.add(con);
                }
                if (!TextUtils.isEmpty(con.getExtra())) {
                    forCurrent.add(con);
                }
            }
            topConv.addAll(forCurrent);
            mDatas.removeAll(forCurrent);
            mDatas.removeAll(delFeedBack);

        } else {
            mConvListView.setNullConversation(false);
        }
        if (topConv != null && topConv.size() > 0) {
            SortTopConvList top = new SortTopConvList();
            Collections.sort(topConv, top);
            for (Conversation conv : topConv) {
                mDatas.add(i, conv);
                i++;
            }
        }
        mListAdapter = new ConversationListAdapter(mContext.getActivity(), mDatas, mConvListView);
        mConvListView.setConvListAdapter(mListAdapter);
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.create_group_btn:
//                mContext.showPopWindow();
//                break;
//            case R.id.search_title:
//                Intent intent = new Intent();
//                intent.setClass(mContext.getActivity(), SearchContactsActivity.class);
//                mContext.startActivity(intent);
//                break;
//        }
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        JMessageClient.getUserInfo(view.getContext().getString(R.string.appidjiguang), new GetUserInfoCallback() {

            @Override
            public void gotResult(int i, String s, UserInfo userInfo) {
                if (i==0)
                {
                    //点击会话条目
                    Intent intent = new Intent();
                    if (position > 0) {
                        //这里-3是减掉添加的三个headView
                        Conversation conv = mDatas.get(position - 3);
                        intent.putExtra(MyApplication.CONV_TITLE, conv.getTitle());
                        //群聊
                        if (conv.getType() == ConversationType.group) {
                            if (mListAdapter.includeAtMsg(conv)) {
                                intent.putExtra("atMsgId", mListAdapter.getAtMsgId(conv));
                            }

                            if (mListAdapter.includeAtAllMsg(conv)) {
                                intent.putExtra("atAllMsgId", mListAdapter.getatAllMsgId(conv));
                            }
                            long groupId = ((GroupInfo) conv.getTargetInfo()).getGroupID();
                            intent.putExtra(MyApplication.GROUP_ID, groupId);
                            String type= conv.getType().name();
                            intent.putExtra(MyApplication.message_tyoe, type);
                            intent.putExtra(MyApplication.DRAFT, getAdapter().getDraft(conv.getId()));
                            intent.putExtra(MyApplication.myusername, userInfo.getUserName());
                            intent.putExtra(MyApplication.myusernickername,userInfo.getNickname());
                            intent.putExtra(MyApplication.myuseravater,userInfo.getAvatar());
                            intent.setClass(mContext.getActivity(), ChatActivity.class);
                            mContext.getActivity().startActivity(intent);
                            return;
                            //单聊
                        } else {
                            String targetId = ((UserInfo) conv.getTargetInfo()).getUserName();//
                            String type= conv.getType().name();
                            intent.putExtra(MyApplication.message_tyoe, type);
                            intent.putExtra(MyApplication.TARGET_ID, targetId);
                            intent.putExtra(MyApplication.TARGET_APP_KEY, conv.getTargetAppKey());
                            intent.putExtra(MyApplication.DRAFT, getAdapter().getDraft(conv.getId()));
                            intent.putExtra(MyApplication.myusername, userInfo.getUserName());
                            intent.putExtra(MyApplication.myusernickername,userInfo.getNickname());
                            intent.putExtra(MyApplication.myuseravater,userInfo.getAvatar());
                        }
                        intent.setClass(mContext.getActivity(), ChatActivity.class);
                        mContext.getContext().startActivity(intent);

                    }
                }
            }
        });

    }

    public ConversationListAdapter getAdapter() {
        return mListAdapter;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        final Conversation conv = mDatas.get(position - 3);
        if (conv != null) {
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        //会话置顶
                        case R.id.jmui_top_conv_ll:
                            //已经置顶,去取消
                            if (!TextUtils.isEmpty(conv.getExtra())) {
                                mListAdapter.setCancelConvTop(conv);
                                //没有置顶,去置顶
                            } else {
                                mListAdapter.setConvTop(conv);
                            }
                            mDialog.dismiss();
                            break;
                        //删除会话
                        case R.id.jmui_delete_conv_ll:
                            if (conv.getType() == ConversationType.group) {
                                JMessageClient.deleteGroupConversation(((GroupInfo) conv.getTargetInfo()).getGroupID());
                            } else {
                                JMessageClient.deleteSingleConversation(((UserInfo) conv.getTargetInfo()).getUserName());
                            }
                            mDatas.remove(position - 3);
                            if (mDatas.size() > 0) {
                                mConvListView.setNullConversation(true);
                            } else {
                                mConvListView.setNullConversation(false);
                            }
                            mListAdapter.notifyDataSetChanged();
                            mDialog.dismiss();
                            break;
                        default:
                            break;
                    }

                }
            };
            mDialog = DialogCreator.createDelConversationDialog(mContext.getActivity(), listener, TextUtils.isEmpty(conv.getExtra()));
            mDialog.show();
            mDialog.getWindow().setLayout((int) (0.8 * mWidth), WindowManager.LayoutParams.WRAP_CONTENT);
        }
        return true;
    }

    @Override
    public void onClick(View view) {

    }
}
