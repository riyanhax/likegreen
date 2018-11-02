package com.xbdl.xinushop.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.MyFansBean;
import com.xbdl.xinushop.utils.HttpUtils2;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的关注和粉丝adapter
 */

public class MyFocuseAdapter extends ListBaseAdapter<MyFansBean.ExtendBean.ConcernListBean.ListBean> {
    private LayoutInflater mLayoutInflater;
    public MyFocuseAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(mLayoutInflater.inflate(R.layout.item_my_focuse_and_fans,parent,false));
    }
    int whetherToBeConcerned;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (mDataList!=null){
            final MyFansBean.ExtendBean.ConcernListBean.ListBean bean = mDataList.get(position);
            Log.v("nihaoma","我的关注"+bean.toString());
            final MyHolder viewHolder = (MyHolder) holder;
            Log.v("nihaoma","我的关注user"+bean.getUser().toString());
            viewHolder.username.setText(bean.getUser().getUserName());

            whetherToBeConcerned= bean.getWhetherToBeConcerned();
            if (whetherToBeConcerned==0){
                //未关注
                viewHolder.focuseBtn.setText(mContext.getResources().getString(R.string.focus));
                viewHolder.focuseBtn.setTextColor(mContext.getResources().getColor(R.color.white));
                viewHolder.focuseBtn.setBackground(mContext.getDrawable(R.drawable.my_focuse));

            }else if (whetherToBeConcerned==1){
                //已关注
                viewHolder.focuseBtn.setText(mContext.getResources().getString(R.string.focused));
                viewHolder.focuseBtn.setTextColor(mContext.getResources().getColor(R.color.cblack));
                viewHolder.focuseBtn.setBackground(mContext.getDrawable(R.drawable.my_focuse_together));
            }else {
                //相互关注
                viewHolder.focuseBtn.setText(mContext.getResources().getString(R.string.focustogether));
                viewHolder.focuseBtn.setTextColor(mContext.getResources().getColor(R.color.cblack));
                viewHolder.focuseBtn.setBackground(mContext.getDrawable(R.drawable.my_focuse_together));
            }
            if (TextUtils.isEmpty(bean.getUser().getSignature())){
                viewHolder.word.setText(mContext.getResources().getString(R.string.textsign));
            }else {
                viewHolder.word.setText("个性签名："+bean.getUser().getSignature());
            }
            if (!TextUtils.isEmpty(bean.getUser().getHeadPortrait())){
                Glide.with(mContext).load(bean.getUser().getHeadPortrait()).into(viewHolder.focuseHead);
            }
            viewHolder.focuseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myClick.focuseClick(bean,viewHolder);
                    //改变状态
                    changeState(bean, viewHolder);

                }
            });


            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myClick.itemClick(bean);
                }
            });



        }

    }
    //改变状态
    private void changeState(MyFansBean.ExtendBean.ConcernListBean.ListBean bean, final MyHolder viewHolder) {
        switch (whetherToBeConcerned){
            case 0:
                //未关注
                HttpUtils2.appAddConcern(MyApplication.user.getLoginToken(),
                        MyApplication.user.getUserId(), bean.getUser().getUserId(), new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.v("nihaoma","点添加关注  "+response.body());
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    int code = jsonObject.getInt("code");
                                    if (code==100){
                                        //成功
                                        viewHolder.focuseBtn.setText(mContext.getResources().getString(R.string.focused));
                                        viewHolder.focuseBtn.setTextColor(mContext.getResources().getColor(R.color.cblack));
                                        viewHolder.focuseBtn.setBackground(mContext.getDrawable(R.drawable.my_focuse_together));
                                        whetherToBeConcerned=1;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                break;
            case 1:
                //已关注
                HttpUtils2.appCancelYourAttention(MyApplication.user.getLoginToken(),
                        MyApplication.user.getUserId(), bean.getUser().getUserId(), new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.v("nihaoma","点取消关注"+response.body());
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    int code = jsonObject.getInt("code");
                                    if (code==100){
                                        //成功
                                        viewHolder.focuseBtn.setText(mContext.getResources().getString(R.string.focus));
                                        viewHolder.focuseBtn.setTextColor(mContext.getResources().getColor(R.color.white));
                                        viewHolder.focuseBtn.setBackground(mContext.getDrawable(R.drawable.my_focuse));

                                        whetherToBeConcerned=2;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
            case 2:
                //相互关注
                HttpUtils2.appCancelYourAttention(MyApplication.user.getLoginToken(),
                        MyApplication.user.getUserId(), bean.getUser().getUserId(), new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.v("nihaoma","点取消关注"+response.body());
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    int code = jsonObject.getInt("code");
                                    if (code==100){
                                        //成功
                                        viewHolder.focuseBtn.setText(mContext.getResources().getString(R.string.focus));
                                        viewHolder.focuseBtn.setTextColor(mContext.getResources().getColor(R.color.white));
                                        viewHolder.focuseBtn.setBackground(mContext.getDrawable(R.drawable.my_focuse));
                                        whetherToBeConcerned=2;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;

        }
    }

    public class MyHolder extends RecyclerView.ViewHolder{
         CircleImageView focuseHead;
         TextView username,word,focuseBtn;
         View view;
        public MyHolder(View itemView) {
            super(itemView);
            view=itemView;
            focuseHead = (CircleImageView)itemView.findViewById(R.id.ci_focuse_head);
            username=(TextView)itemView.findViewById(R.id.tv_focuse_username);
            word=(TextView)itemView.findViewById(R.id.tv_focuse_word);
            focuseBtn=(TextView)itemView.findViewById(R.id.my_home_focuse_btn);//关注按钮
        }
    }
    private MyClick myClick;
    public void setClick(MyClick myClick){
        this.myClick=myClick;
    }
    public interface MyClick{
        void itemClick(  MyFansBean.ExtendBean.ConcernListBean.ListBean bean);
        void focuseClick( MyFansBean.ExtendBean.ConcernListBean.ListBean bean,MyHolder holder);
    }
}
