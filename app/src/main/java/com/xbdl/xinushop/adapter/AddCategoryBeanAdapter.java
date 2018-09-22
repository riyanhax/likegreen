package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.baseadapter.BaseViewHolder;
import com.xbdl.xinushop.adapter.baseadapter.SimpleAdapter;
import com.xbdl.xinushop.bean.CategoryBean;

public class AddCategoryBeanAdapter extends SimpleAdapter<CategoryBean> {

    public AddCategoryBeanAdapter(Context context) {
        super(context, R.layout.item_addcategorylayout);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, CategoryBean item) {
        AppCompatTextView tvTitle=viewHoder.getAppCompatTextView(R.id.tv_title);
        tvTitle.setText(item.getCategory()+" "+item.getPrice()+" "+item.getStock());
    }
}
