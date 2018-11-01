package com.xbdl.xinushop.adapter.note;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.UserDetailActivity;
import com.xbdl.xinushop.adapter.ListBaseAdapter;

import com.xbdl.xinushop.bean.NoteDetailBean;
import com.xbdl.xinushop.bean.NoteHotBean;
import com.xbdl.xinushop.utils.HttpUtils2;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoteListAdapter extends ListBaseAdapter<NoteHotBean.ExtendBean.DiaryRootsBean.ListBean> {
    private LayoutInflater mLayoutInflater;

    public NoteListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_notelistlayout, parent, false));
    }
    int concernState;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (mDataList!=null){
            final NoteHotBean.ExtendBean.DiaryRootsBean.ListBean item = mDataList.get(position);
            List<NoteHotBean.ExtendBean.DiaryRootsBean.ListBean.DiarysBean> diarys = item.getDiarys();
            final ViewHolder viewHolder= (ViewHolder) holder;
            Log.v("nihaoma",item.getDiaryRootUserId()+" wode id  "+MyApplication.user.getUserId());
            if (item.getDiaryRootUserId()== MyApplication.user.getUserId()){
                //自己的
                viewHolder.tv_username.setText("我的");
                viewHolder.tv_attention.setVisibility(View.GONE);
            }else {
                viewHolder.tv_username.setText(item.getUserName());
                viewHolder.tv_attention.setVisibility(View.VISIBLE);
                //0 未关注 1 关注 2相互关注
                concernState= item.getConcernState();
                if (item.getConcernState()==0){
                    viewHolder.tv_attention.setText(mContext.getResources().getString(R.string.focus));
                    viewHolder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.white));
                    viewHolder.tv_attention.setBackground(mContext.getDrawable(R.drawable.my_focuse));
                }else if (item.getConcernState()==1){
                    viewHolder.tv_attention.setText(mContext.getResources().getString(R.string.focused));
                    viewHolder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.cblack));
                    viewHolder.tv_attention.setBackground(mContext.getDrawable(R.drawable.my_focuse_together));
                }else {
                    viewHolder.tv_attention.setText(mContext.getResources().getString(R.string.focustogether));
                    viewHolder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.cblack));
                    viewHolder.tv_attention.setBackground(mContext.getDrawable(R.drawable.my_focuse_together));
                }
                viewHolder.tv_attention.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeState(item,viewHolder);
                    }
                });
            }

            viewHolder.tv_topic.setText("植物日记·"+item.getDiaryRootTitle());
            viewHolder.tv_viewcount.setText("浏览"+item.getDiaryRootNumberOfViews() + "次");
            viewHolder.tv_location.setText(item.getDiarys().get(0).getDiaryAddressTemperatureWeather()+"℃");
            viewHolder.iv_usericon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(v.getContext(), UserDetailActivity.class);
                    intent.putExtra("id",item.getDiaryRootUserId());
                    v.getContext().startActivity(intent);

                }
            });


            RecyclerView recyclerViewimage = viewHolder.recycler_image;
            if (item.getDiarys().get(0).getDirayIamge().size()!=0){
                if (item.getDiarys().size() != 0) {
                    if (item.getDiarys().size() == 1) {
                        recyclerViewimage.setLayoutManager(new GridLayoutManager(recyclerViewimage.getContext(), 1));
                    } else if (item.getDiarys().size() == 2) {
                        recyclerViewimage.setLayoutManager(new GridLayoutManager(recyclerViewimage.getContext(), 2));
                    } else {
                        recyclerViewimage.setLayoutManager(new GridLayoutManager(recyclerViewimage.getContext(), 3));
                    }
                }
                NoteImagesAdapter imagesAdapter = null;
                if (item.getDiarys() != null && item.getDiarys().size() > 0) {
                    imagesAdapter = new NoteImagesAdapter(mContext,item.getDiaryRootUserId(),item.getNumberOfFans(),item.getFewDays(),item.getConcernState());
                } else {
                    imagesAdapter = new NoteImagesAdapter(mContext,item.getDiaryRootUserId(),item.getNumberOfFans(),item.getFewDays(),item.getConcernState());
                }
                recyclerViewimage.setAdapter(imagesAdapter);

                if (item.getDiarys() != null && item.getDiarys().size() > 0) {
                    imagesAdapter.setDataList(diarys);

                }
            }

        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_username,tv_location,tv_topic,tv_viewcount,tv_attention;
        CircleImageView iv_usericon;
        RecyclerView recycler_image;
        public ViewHolder(View itemView) {
            super(itemView);
             tv_username = itemView.findViewById(R.id.tv_username);
             tv_location = itemView.findViewById(R.id.tv_location);
             tv_topic = itemView.findViewById(R.id.tv_topic);
            tv_viewcount = itemView.findViewById(R.id.tv_viewcount);
            tv_attention = itemView.findViewById(R.id.tv_attention);

            iv_usericon = itemView.findViewById(R.id.iv_usericon);

            recycler_image = itemView.findViewById(R.id.recycler_image);

        }
    }
    //改变状态
    private void changeState(NoteHotBean.ExtendBean.DiaryRootsBean.ListBean bean, final NoteListAdapter.ViewHolder viewHolder) {
        switch (concernState){
            case 2:
                //相互关注
                HttpUtils2.appCancelYourAttention(MyApplication.user.getLoginToken(),
                        MyApplication.user.getUserId(), bean.getDiaryRootUserId(), new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.v("nihaoma","点取消关注"+response.body());
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    int code = jsonObject.getInt("code");
                                    if (code==100){
                                        //成功
                                        viewHolder.tv_attention.setText(mContext.getResources().getString(R.string.focus));
                                        viewHolder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.white));
                                        viewHolder.tv_attention.setBackground(mContext.getDrawable(R.drawable.my_focuse));
                                        concernState=0;
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
                        MyApplication.user.getUserId(), bean.getDiaryRootUserId(), new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.v("nihaoma","点取消关注"+response.body());
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    int code = jsonObject.getInt("code");
                                    if (code==100){
                                        //成功
                                        viewHolder.tv_attention.setText(mContext.getResources().getString(R.string.focus));
                                        viewHolder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.white));
                                        viewHolder.tv_attention.setBackground(mContext.getDrawable(R.drawable.my_focuse));
                                        concernState=0;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
            case 0:
                //未关注
                HttpUtils2.appAddConcern(MyApplication.user.getLoginToken(),
                        MyApplication.user.getUserId(), bean.getDiaryRootUserId(), new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.v("nihaoma","点添加关注  "+response.body());
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    int code = jsonObject.getInt("code");
                                    if (code==100){
                                        //成功
                                        viewHolder.tv_attention.setText(mContext.getResources().getString(R.string.focused));
                                        viewHolder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.cblack));
                                        viewHolder.tv_attention.setBackground(mContext.getDrawable(R.drawable.my_focuse_together));
                                        concernState=1;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;

        }
    }

}
