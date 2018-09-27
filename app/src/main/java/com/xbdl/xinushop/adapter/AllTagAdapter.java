package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.baseadapter.BaseViewHolder;
import com.xbdl.xinushop.adapter.baseadapter.SimpleAdapter;
import com.xbdl.xinushop.bean.AllTagBean;

import java.util.List;

public class AllTagAdapter extends SimpleAdapter<AllTagBean> {
    public AllTagAdapter(Context context) {
        super(context, R.layout.item_alltaglayout);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, AllTagBean item) {
        AppCompatTextView tvTag = viewHoder.getAppCompatTextView(R.id.tv_tag);
        if (item.isIscheck()) {
            tvTag.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            tvTag.setBackgroundColor(context.getResources().getColor(R.color.color_e0e0e0));
        }
        tvTag.setText(item.getName());
    }

    public void setItemCheck(AllTagBean allTagBean) {
        List<AllTagBean> list = getDatas();
        if (list != null && list.size() > 0) {
            int position = list.indexOf(allTagBean);
            if (position != -1) {
                clearCheck();
//java8版本
                for (int i = 0; i < list.size(); i++) {
                    if (i == position) {
                        if (allTagBean.isIscheck()) {
                            getDatas().get(position).setIscheck(false);
                        } else {
                            getDatas().get(position).setIscheck(true);
                        }
                    }
                }
//   getDatas().get(position).setIscheck(false);

                notifyDataSetChanged();
            }
        }
    }

    public void clearCheck() {
        List<AllTagBean> list = getDatas();
        for (int i = 0; i < list.size(); i++) {
            getDatas().get(i).setIscheck(false);
        }
    }


    public int getCheckListSize() {
        int j = 0;
        List<AllTagBean> list = getDatas();
        for (int i = 0; i < list.size(); i++) {
            AllTagBean allTagBean = list.get(i);
            if (allTagBean.isIscheck()) {
                j++;
            }
        }
        return j;
    }
}
