package com.xbdl.xinushop.adapter.note;

import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.UserDetailActivity;
import com.xbdl.xinushop.bean.NoteListBean;

public class NoteListAdapter extends BaseQuickAdapter<NoteListBean, BaseViewHolder> {
    public NoteListAdapter() {
        super(R.layout.item_notelistlayout);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoteListBean item) {
        helper.setText(R.id.tv_username, item.getUsername())
                .setText(R.id.tv_location, item.getLocation() + " " + item.getTemp() + " " + item.getWeather())
                .setText(R.id.tv_topic, item.getTopic())
                .setText(R.id.tv_viewcount, "浏览"+item.getViewcount() + "次");

        AppCompatImageView ivUsericon = helper.getView(R.id.iv_usericon);

        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(ivUsericon.getContext()).load(item.getUsericon()).apply(requestOptions).into(ivUsericon);
        ivUsericon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserDetailActivity.class);
               // intent.putExtra("id",bean.getUser().getUserId());
                v.getContext().startActivity(intent);
            }
        });
        if (item.getAttention() == 1) {
            helper.setText(R.id.tv_attention, "互相关注");
        } else if (item.getAttention() == 2) {
            helper.setText(R.id.tv_attention, "已关注");
        } else {
            helper.setText(R.id.tv_attention, "未关注");
        }

        RecyclerView recyclerViewimage = helper.getView(R.id.recycler_image);
        if (item.getImages() != null) {
            if (item.getImages().size() == 1) {
                recyclerViewimage.setLayoutManager(new GridLayoutManager(recyclerViewimage.getContext(), 1));
            } else if (item.getImages().size() == 2) {
                recyclerViewimage.setLayoutManager(new GridLayoutManager(recyclerViewimage.getContext(), 2));
            } else {
                recyclerViewimage.setLayoutManager(new GridLayoutManager(recyclerViewimage.getContext(), 3));
            }
        }

        NoteImagesAdapter imagesAdapter = null;
        if (item.getImages() != null && item.getImages().size() > 0) {
            imagesAdapter = new NoteImagesAdapter(item.getImages().size());
        } else {
            imagesAdapter = new NoteImagesAdapter(0);
        }
        recyclerViewimage.setAdapter(imagesAdapter);

        if (item.getImages() != null && item.getImages().size() > 0) {
            imagesAdapter.addData(item.getImages());
        }


    }
}
