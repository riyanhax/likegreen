package com.xbdl.xinushop.adapter.note;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.UserDetailActivity;
import com.xbdl.xinushop.activity.note.NoteDetailActivity;
import com.xbdl.xinushop.adapter.ListBaseAdapter;

import com.xbdl.xinushop.bean.NoteHotBean;


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

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (mDataList!=null){
            final NoteHotBean.ExtendBean.DiaryRootsBean.ListBean item = mDataList.get(position);
            List<NoteHotBean.ExtendBean.DiaryRootsBean.ListBean.DiarysBean> diarys = item.getDiarys();
            ViewHolder viewHolder= (ViewHolder) holder;
            viewHolder.tv_username.setText(item.getUserName());
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
                    imagesAdapter = new NoteImagesAdapter(mContext,item.getDiaryRootUserId());
                } else {
                    imagesAdapter = new NoteImagesAdapter(mContext,item.getDiaryRootUserId());
                }
                recyclerViewimage.setAdapter(imagesAdapter);

                if (item.getDiarys() != null && item.getDiarys().size() > 0) {
                    imagesAdapter.setDataList(diarys);
                }
            }

        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_username,tv_location,tv_topic,tv_viewcount;
        CircleImageView iv_usericon;
        RecyclerView recycler_image;
        public ViewHolder(View itemView) {
            super(itemView);
             tv_username = itemView.findViewById(R.id.tv_username);
             tv_location = itemView.findViewById(R.id.tv_location);
             tv_topic = itemView.findViewById(R.id.tv_topic);
            tv_viewcount = itemView.findViewById(R.id.tv_viewcount);

            iv_usericon = itemView.findViewById(R.id.iv_usericon);

            recycler_image = itemView.findViewById(R.id.recycler_image);

        }
    }
/*    @Override
    protected void convert(BaseViewHolder helper, final NoteHotBean.ExtendBean.DiaryRootsBean.ListBean item) {

        helper.setText(R.id.tv_username, item.getUserName())
                .setText(R.id.tv_location, item.getDiarys().get(0).getDiaryAddressTemperatureWeather()+"℃")
                .setText(R.id.tv_topic, item.getDiaryRootTitle())
                .setText(R.id.tv_viewcount, "浏览"+item.getDiaryRootNumberOfViews() + "次");

        AppCompatImageView ivUsericon = helper.getView(R.id.iv_usericon);

        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(ivUsericon.getContext()).load(item.getAvatar()).apply(requestOptions).into(ivUsericon);
        ivUsericon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserDetailActivity.class);
                intent.putExtra("id",item.getDiaryRootUserId());
                v.getContext().startActivity(intent);
            }
        });
      *//*  if (item.getAttention() == 1) {
            helper.setText(R.id.tv_attention, "互相关注");
        } else if (item.getAttention() == 2) {
            helper.setText(R.id.tv_attention, "已关注");
        } else {
            helper.setText(R.id.tv_attention, "未关注");
        }*//*





    }*/
}
