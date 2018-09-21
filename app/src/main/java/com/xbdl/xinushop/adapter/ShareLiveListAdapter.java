package com.xbdl.xinushop.adapter;

import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.bean.Images;
import com.xbdl.xinushop.bean.ShareLifeListBean;
import com.xbdl.xinushop.listener.OnShareLiveItemListener;
import com.xbdl.xinushop.utils.Judge;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * 分享生活
 */
public class ShareLiveListAdapter extends BaseQuickAdapter<ShareLifeListBean, BaseViewHolder> {

    public ShareLiveListAdapter() {
        super(R.layout.item_sharelivelistlayout);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShareLifeListBean item) {
        helper.setText(R.id.tv_username, item.getUsername())
                .setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_message, item.getMessage())
                .setText(R.id.tv_likenumber, "" + item.getLikenumber())
                .setText(R.id.tv_sharenumber, "" + item.getSharenumber())
                .setText(R.id.tv_viewcount, "浏览" + item.getViewcount() + "次");


        AppCompatTextView tvTopic = helper.getView(R.id.tv_topic);//时间和话题
        String topic = item.getCreatetime() + " " + "来自" + item.getTopic();
        String topicstring = item.getTopic();
        SpannableString spannableString = new SpannableString(topic);
        ForegroundColorSpan f1 = new ForegroundColorSpan(Color.parseColor("#f8819c"));
        spannableString.setSpan(f1, topic.indexOf(topicstring), topic.indexOf(topicstring) + topicstring.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTopic.setText(spannableString);

        RecyclerView recyclerViewimage = helper.getView(R.id.recycler_image);
        if (item.getImages()!=null)
        {
            if (item.getImages().size()==1)
            {
                recyclerViewimage.setLayoutManager(new GridLayoutManager(recyclerViewimage.getContext(),1));
            }else if (item.getImages().size()==2)
            {
                recyclerViewimage.setLayoutManager(new GridLayoutManager(recyclerViewimage.getContext(),2));
            }else {
                recyclerViewimage.setLayoutManager(new GridLayoutManager(recyclerViewimage.getContext(),3));
            }
        }

       ImagesAdapter imagesAdapter=null;
       if (item.getImages()!=null&&item.getImages().size()>0)
       {
           imagesAdapter=new ImagesAdapter(item.getImages().size());
       }else {
           imagesAdapter=new ImagesAdapter(0);
       }
       recyclerViewimage.setAdapter(imagesAdapter);

      if (item.getImages()!=null&&item.getImages().size()>0)
      {
          imagesAdapter.addData(item.getImages());
      }




        AppCompatImageView ivUsericon = helper.getView(R.id.iv_usericon);
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(ivUsericon.getContext()).load(item.getUsericon()).apply(requestOptions).into(ivUsericon);

        helper.getView(R.id.ll_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShareLiveItemListener.onShareLiveItemListener(1, item);
            }
        });
        helper.getView(R.id.ll_sharedialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onShareLiveItemListener != null) {
                    onShareLiveItemListener.onShareLiveItemListener(2, item);
                }
            }
        });
    }

    private List<Images> getImageData(String images) {
        if (Judge.getBoolean_isNull(images)) {
            return null;
        }
        try {
            JSONArray array = new JSONArray(images);
            List<Images> datas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                Images ima = new Gson().fromJson(array.getString(i), Images.class);
                datas.add(ima);
            }
            return datas;
        } catch (JSONException e) {
            return null;
        }
    }

    public void setOnShareLiveItemListener(OnShareLiveItemListener onShareLiveItemListener) {
        this.onShareLiveItemListener = onShareLiveItemListener;
    }

    OnShareLiveItemListener onShareLiveItemListener;
}
