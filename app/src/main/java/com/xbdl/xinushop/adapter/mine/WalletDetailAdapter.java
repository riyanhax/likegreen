package com.xbdl.xinushop.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.WalletDetailBean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by theWind on 2018/8/14.
 */

public class WalletDetailAdapter extends ListBaseAdapter<WalletDetailBean.ListBean> {
    private LayoutInflater mLayoutInflater;
    public WalletDetailAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(mLayoutInflater.inflate(R.layout.item_wallet_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (mDataList!=null){
            WalletDetailBean.ListBean bean = mDataList.get(position);
            MyHolder viewHolder = (MyHolder) holder;
            viewHolder.type.setText(bean.getDetailContent());
            viewHolder.date.setText(bean.getCreatedDate());
            if (bean.getDetailType()==1){
                //支出
                viewHolder.cash.setText("-"+bean.getDetailPrice());
                viewHolder.cash.setTextColor(mContext.getResources().getColor(R.color.actionbar_sel_color));
            }else if (bean.getDetailType()==2){
                //收入
                viewHolder.cash.setText("+"+bean.getDetailPrice());
                viewHolder.cash.setTextColor(mContext.getResources().getColor(R.color.red));
            }else {
                //退款
                viewHolder.cash.setText(String.valueOf(bean.getDetailPrice()));
                viewHolder.cash.setTextColor(mContext.getResources().getColor(R.color.actionbar_sel_color));
            }

        }
    }
    public class MyHolder extends RecyclerView.ViewHolder{
        TextView type,date,cash;
        public MyHolder(View itemView) {
            super(itemView);
            type= (TextView)itemView.findViewById(R.id.tv_wallet_type);
            date= (TextView)itemView.findViewById(R.id.tv_wallet_date);
            cash= (TextView)itemView.findViewById(R.id.tv_wallet_cash);
        }
    }
}
