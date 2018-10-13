package com.xbdl.xinushop.adapter.mine;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.DiscountCouponBean;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by theWind on 2018/8/10.
 * 优惠券adapter
 */

public class CouponAdapter extends ListBaseAdapter<DiscountCouponBean.PageInfoBean.ListBean> {
    private LayoutInflater mLayoutInflater;

    public CouponAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_discountcouponlayout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            DiscountCouponBean.PageInfoBean.ListBean bean = mDataList.get(position);
            ViewHolder viewHolder= (ViewHolder) holder;

            viewHolder.data.setText("有效期："+bean.getStartTime()+"至"+bean.getEndTime());
            viewHolder.type.setText(bean.getCouponsName());
            if (bean.getCouponsType()==1){

                viewHolder.cash.setText(String.valueOf(bean.getReduceMoney()));
            }else {
                viewHolder.renmingbi.setVisibility(View.GONE);
                viewHolder.cash.setText(bean.getDiscount()+"折");
            }
            //使用状态
            if (bean.getStatus()==0){
                viewHolder.isuser.setText("未使用");
            }else {
                viewHolder.isuser.setText("已使用");
            }
            viewHolder.detail.setText(bean.getCouponsExplain());
        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView cash,type,detail,data,isuser;
        View renmingbi;
        public ViewHolder(View itemView) {
            super(itemView);
            cash= itemView.findViewById(R.id.tv_coupon_cash);
            type= itemView.findViewById(R.id.tv_coupon_type);
            detail= itemView.findViewById(R.id.tv_coupon_detail);
            data= itemView.findViewById(R.id.tv_coupon_data);
            isuser= itemView.findViewById(R.id.tv_coupon_isuser);
            renmingbi = itemView.findViewById(R.id.tv_renmingbi);
        }
    }
}
