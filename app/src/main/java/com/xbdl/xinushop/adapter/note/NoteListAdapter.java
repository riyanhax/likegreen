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
import com.xbdl.xinushop.bean.NoteHotBean;
import com.xbdl.xinushop.bean.NoteListBean;

import java.util.List;

public class NoteListAdapter extends BaseQuickAdapter<NoteHotBean.ExtendBean.DiaryRootsBean.ListBean, BaseViewHolder> {
    public NoteListAdapter() {
        super(R.layout.item_notelistlayout);
    }

    @Override
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
      /*  if (item.getAttention() == 1) {
            helper.setText(R.id.tv_attention, "互相关注");
        } else if (item.getAttention() == 2) {
            helper.setText(R.id.tv_attention, "已关注");
        } else {
            helper.setText(R.id.tv_attention, "未关注");
        }*/

        RecyclerView recyclerViewimage = helper.getView(R.id.recycler_image);
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
                imagesAdapter = new NoteImagesAdapter(item.getDiarys().size());
            } else {
                imagesAdapter = new NoteImagesAdapter(0);
            }
            recyclerViewimage.setAdapter(imagesAdapter);

            if (item.getDiarys() != null && item.getDiarys().size() > 0) {
                imagesAdapter.addData(item);
            }
        }



    }
}
