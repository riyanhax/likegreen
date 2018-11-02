package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.baseadapter.BaseViewHolder;
import com.xbdl.xinushop.adapter.baseadapter.SimpleAdapter;
import com.xbdl.xinushop.bean.ReCommentListBean;
import com.xbdl.xinushop.bean.TheNewVideoBean;
import com.xbdl.xinushop.bean.VideoCommentBean;
import com.xbdl.xinushop.utils.HttpUtils2;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 视频评论适配器
 */
public class VideoCommentAdapter extends SimpleAdapter<VideoCommentBean.ExtendBean.PageBean.ListBean> {
    public VideoCommentAdapter(Context context) {
        super(context, R.layout.item_recommentlistlayout);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, final VideoCommentBean.ExtendBean.PageBean.ListBean item) {
        ImageView ivUserheard = viewHoder.getImageView(R.id.iv_usericon);
        AppCompatTextView tvUserName = viewHoder.getAppCompatTextView(R.id.tv_username);
        AppCompatTextView tvMessage = viewHoder.getAppCompatTextView(R.id.tv_message);
        AppCompatTextView tvTime = viewHoder.getAppCompatTextView(R.id.tv_time);
        AppCompatTextView tv_likenumber = viewHoder.getAppCompatTextView(R.id.tv_likenumber);
        ImageView deleteView = viewHoder.getImageView(R.id.iv_delete);//删除按钮
        ImageView iv_like = viewHoder.getImageView(R.id.iv_like);//删除按钮
        //设置圆形图片
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        if (item.getUser().getHeadPortrait()!=null){
            Glide.with(ivUserheard.getContext()).load(item.getUser().getHeadPortrait()).apply(requestOptions).into(ivUserheard);
        }
        tv_likenumber.setText(String.valueOf(item.getCount()));
        tvUserName.setText(item.getUser().getUserName());
        tvMessage.setText(item.getCommentsContent());
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");

        //tvTime.setText(simpleFormatter.format(item.getCommentsCreatedTime()));
        tvTime.setText(item.getCommentsCreatedTime());
        if (MyApplication.user.getUserId()==item.getUser().getUserId()){
            //自己评论的
            deleteView.setVisibility(View.VISIBLE);
        }else {
            deleteView.setVisibility(View.GONE);
        }
        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               click.myClick(item.getCommentsId());
            }
        });
        isLike(iv_like,item.getCommentsId());
    }

    //判断是否点赞
    private void isLike(final ImageView iv_islike, final int commentsId ) {
        HttpUtils2.appCheckClickToPraise( 2,commentsId , MyApplication.user.getUserId(),MyApplication.user.getLoginToken(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma","是否点赞"+response.body());
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    int code = jsonObject.getInt("code");
                    if (code==100){
                        String extend = jsonObject.getString("extend");
                        JSONObject jsonextend= new JSONObject(extend);
                        int isClickToPraise = jsonextend.getInt("isClickToPraise");
                        if (isClickToPraise==1){
                            //没有点赞
                            iv_islike.setImageResource(R.drawable.heart_pinglun);
                            iv_islike.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    appAddClickToPraise( iv_islike,  commentsId);
                                }
                            });
                        }else {
                            //点赞了
                            iv_islike.setImageResource(R.drawable.heart_xuanzhong_luntan);
                            iv_islike.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    appCancelClickToPraise(iv_islike,  commentsId);
                                }
                            });
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.v("nihaoma","获取是否点赞onError");
            }
        });
    }
    //点赞
    private void appAddClickToPraise(final ImageView iv_islike, final int commentsId ) {
        HttpUtils2.appAddClickToPraise( 2, commentsId, MyApplication.user.getUserId(),MyApplication.user.getLoginToken(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma","点赞"+response.body());
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    int code = jsonObject.getInt("code");
                    if (code==100){
                        isLike(iv_islike,commentsId);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.v("nihaoma","点赞onError");
            }
        });
    }
    //取消点赞
    private void appCancelClickToPraise(final ImageView iv_islike, final int commentsId ) {
        HttpUtils2.appCancelClickToPraise( 2, commentsId, MyApplication.user.getUserId(),MyApplication.user.getLoginToken(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma","取消点赞"+response.body());

                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    int code = jsonObject.getInt("code");
                    if (code==100){
                        isLike(iv_islike,commentsId);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.v("nihaoma","取消点赞onError");
            }
        });
    }
    private DeleteClick click;
    public void setDeleteClick(DeleteClick click){
        this.click=click;
    }
    public interface DeleteClick{
        void myClick(int id);
    }
}
