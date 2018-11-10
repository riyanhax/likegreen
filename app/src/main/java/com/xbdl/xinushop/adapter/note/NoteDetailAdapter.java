package com.xbdl.xinushop.adapter.note;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.PhtotViewActivity;
import com.xbdl.xinushop.adapter.FocusVideoAdapter;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.FocusVideoBean;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.NoteDetailBean;
import com.xbdl.xinushop.constant.UrlConstant;
import com.xbdl.xinushop.dialogfragment.RecommentDialogFragment;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.TimeUtil;
import com.xbdl.xinushop.utils.ToastUtil;
import com.xbdl.xinushop.view.MultiImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NoteDetailAdapter extends ListBaseAdapter<NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean> {
    private LayoutInflater mLayoutInflater;

    public NoteDetailAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteDetailAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.item_note_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            final ViewHolder viewHolder= (ViewHolder) holder;
            final NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean bean = mDataList.get(position);
            viewHolder.tv_first.setText("第"+bean.getDiaryDay()+"天");

            String time = bean.getDirayCreateTime();
            String tempStr = time.substring(0, 10);
            String s = TimeUtil.formatDisplayTime(time, null);
            viewHolder.tv_day.setText(s);
            viewHolder.tv_islike_count.setText(String.valueOf(bean.getDirayToClickTheNumberOfLikes()));
            viewHolder.tv_comment_count.setText(String.valueOf(bean.getDirayNumberOfComments()));
            viewHolder.tv_work.setText("："+bean.getDiaryDynamic());
           viewHolder.tv_location.setText(bean.getDiaryAddressTemperatureWeather()+"℃");

           if (bean.isIsLike()){
               viewHolder.iv_islike.setImageResource(R.drawable.heart_xuanzhong_luntan);
           }else {
               viewHolder.iv_islike.setImageResource(R.drawable.heart_luntan);
           }
            viewHolder.iv_islike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    islike(bean,viewHolder);
                }
            });
           //是否关注

            final ArrayList<String> urls = new ArrayList<>();
            List<NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean.DirayIamgeBean> dirayIamge = bean.getDirayIamge();
            for (NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean.DirayIamgeBean iamgeBean:dirayIamge){
                urls.add(UrlConstant.baseimgUrl+iamgeBean.getDiaryImageUrl());
            }
            viewHolder.mu_img.setList(urls);
            viewHolder.mu_img.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(view.getContext(), PhtotViewActivity.class);
                    intent.putExtra("url",urls.get(position));
                    view.getContext().startActivity(intent);
                }
            });
            //评论点击事件
            viewHolder.iv_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   myclick.showCommentPop(v,bean);
                }
            });
        }


    }

    private void islike(final NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean bean, final ViewHolder viewHolder) {
        HttpUtils2.appDiaryLikes(MyApplication.user.getLoginToken(), MyApplication.user.getUserId(), bean.getDiaryId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma","点赞"+response.body());
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    if (jsonObject.getInt("code")==100){
                        String extend = jsonObject.getString("extend");
                        JSONObject extendObject = new JSONObject(extend);
                        boolean isLike = extendObject.getBoolean("isLike");

                        if (isLike){
                            viewHolder.tv_comment_count.setText(String.valueOf(bean.getDirayNumberOfComments()+1));
                            viewHolder.iv_islike.setImageResource(R.drawable.heart_xuanzhong_luntan);
                        }else {
                            viewHolder.tv_comment_count.setText(String.valueOf(bean.getDirayNumberOfComments()-1));
                            viewHolder.iv_islike.setImageResource(R.drawable.heart_luntan);
                        }
                    }else {
                        ToastUtil.shortToast(mContext,"点赞失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private class ViewHolder extends RecyclerView.ViewHolder {
        private MultiImageView mu_img;
        TextView tv_first,tv_day,tv_work,tv_islike_count,tv_comment_count,tv_location;
        ImageView iv_islike,iv_comment;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_first = itemView.findViewById(R.id.tv_first);
            tv_day = itemView.findViewById(R.id.tv_day);
            mu_img = itemView.findViewById(R.id.mu_img);
            tv_work = itemView.findViewById(R.id.tv_work);
            iv_islike = itemView.findViewById(R.id.iv_islike);
            tv_islike_count = itemView.findViewById(R.id.tv_islike_count);
            tv_comment_count = itemView.findViewById(R.id.tv_comment_count);
            tv_location = itemView.findViewById(R.id.tv_location);
            iv_comment = itemView.findViewById(R.id.iv_comment);
        }
    }
    public interface MyViewClick{
        public void showCommentPop(View view,NoteDetailBean.ExtendBean.DiaryRootBean.DiarysBean bean);

    }
    private MyViewClick myclick;
    public void setMyViewClick(MyViewClick click){
        myclick=click;
    }

}
