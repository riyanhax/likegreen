package com.xbdl.xinushop.adapter.note;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.UserDetailActivity;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.NoteHotBean;
import com.xbdl.xinushop.bean.PlantCommentBean;
import com.xbdl.xinushop.dialogfragment.KeyMapDialog;
import com.xbdl.xinushop.utils.HttpUtils2;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
/*
* 日记评论
* */
public class PlantCommentListAdapter extends ListBaseAdapter<PlantCommentBean.ExtendBean.DiaryCommentLayersBean> {
    private LayoutInflater mLayoutInflater;

    public PlantCommentListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_plant_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (mDataList!=null){
            ViewHolder viewHolder= (ViewHolder) holder;
            final PlantCommentBean.ExtendBean.DiaryCommentLayersBean bean = mDataList.get(position);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String format = df.format(new Date());// new Date()为获取当前系统时间
            String nowTiem = format.substring(0, 10);
            String time = bean.getDiaryCommentLayerCreateTime();
            if (nowTiem.equals(time)){
                //今天  未完成
                String tempStr = time.substring(0, 10);
                viewHolder.tv_date.setText(tempStr);
            }else {
                String tempStr = time.substring(0, 10);
                viewHolder.tv_date.setText(tempStr);
            }

            viewHolder.tv_username.setText(bean.getUserName()+"：");
            viewHolder.tv_msg.setText(bean.getDiaryCommentLayerContent());
            //评论中评论
            viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            CommentAdapter commentAdapter = new CommentAdapter(mContext);
            commentAdapter.setDataList(bean.getDiaryLayerComments());
            viewHolder.recyclerView.setAdapter(commentAdapter);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentKey.key(bean);
                }
            });
        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civ_head_icon;
        TextView tv_username,tv_date,tv_msg;
        RecyclerView recyclerView;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
             civ_head_icon = itemView.findViewById(R.id.civ_head_icon);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_msg = itemView.findViewById(R.id.tv_msg);
            recyclerView = itemView.findViewById(R.id.recyclerView);

        }
    }
    public class CommentAdapter extends ListBaseAdapter<PlantCommentBean.ExtendBean.DiaryCommentLayersBean.DiaryLayerCommentsBean> {
        private LayoutInflater mLayoutInflater;

        public CommentAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
            mContext = context;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.item_comment_comment, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (mDataList!=null) {
                final PlantCommentBean.ExtendBean.DiaryCommentLayersBean.DiaryLayerCommentsBean bean = mDataList.get(position);
                ViewHolder viewHolder= (ViewHolder) holder;
                if (bean.getDiaryLayerCommentBeUserName()!=null){

                    if (bean.getDiaryLayerCommentUserName().equals(bean.getDiaryLayerCommentBeUserName())){
                        viewHolder.tv_username.setText(bean.getDiaryLayerCommentUserName()+"：");
                    }else {
                        viewHolder.ll_top.setOrientation(LinearLayout.VERTICAL);
                        String str=bean.getDiaryLayerCommentUserName()+"<font color='#FF0000'>回复： </font>" +bean.getDiaryLayerCommentBeUserName();
                        viewHolder.tv_username.setText(Html.fromHtml(str));
                    }
                   // viewHolder.tv_username.setText(bean.getDiaryLayerCommentUserName()+" 回复："+bean.getDiaryLayerCommentBeUserName());
                }else {
                    viewHolder.tv_username.setText(bean.getDiaryLayerCommentUserName()+"：");
                }

                viewHolder.tv_msg.setText(bean.getDiaryLayerCommentContent());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
        private class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv_username,tv_msg;
            View itemView;
            LinearLayout ll_top;
            public ViewHolder(View itemView) {
                super(itemView);
                this.itemView=itemView;
                tv_username = itemView.findViewById(R.id.tv_username);
                tv_msg = itemView.findViewById(R.id.tv_msg);
                ll_top = itemView.findViewById(R.id.ll_top);
            }
        }
    }
    public CommentKey commentKey;
    public void setClick(CommentKey commentKey){
        this.commentKey=commentKey;
    }
    public interface CommentKey{
        void key(PlantCommentBean.ExtendBean.DiaryCommentLayersBean bean);
    }
}


