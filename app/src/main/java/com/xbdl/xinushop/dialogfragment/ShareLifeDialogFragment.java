package com.xbdl.xinushop.dialogfragment;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.baseadapter.BaseViewHolder;
import com.xbdl.xinushop.adapter.baseadapter.SimpleAdapter;
import com.xbdl.xinushop.bean.ShareLifeListBean;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class ShareLifeDialogFragment extends MyDialogFragment {

    public ShareLifeDialogFragment() {
        // Required empty public constructor
    }

    /**
     */
    // TODO: Rename and change types and number of parameters
    public static ShareLifeDialogFragment newInstance(int type, ShareLifeListBean shareLifeListBean) {
        ShareLifeDialogFragment fragment = new ShareLifeDialogFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putParcelable("shareLifeListBean", shareLifeListBean);
        fragment.setArguments(args);
        return fragment;
    }

    int type = 0;
    ShareLifeListBean shareLifeListBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type", 0);
            shareLifeListBean =  getArguments().getParcelable("shareLifeListBean");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0xffffff0));
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share_life_dialog, container, false);
        getDialog().setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog不消失
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER_VERTICAL;
        window.setAttributes(wlp);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_share);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(getActivity());
        recyclerView.setAdapter(gridViewAdapter);
        List<Integer> integers = new ArrayList<>();
        integers.add(R.drawable.pengyouquan);
        integers.add(R.drawable.weixin_share);
        integers.add(R.drawable.qq_share);
        integers.add(R.drawable.weibo_share);
        gridViewAdapter.refreshData(integers);

        LinearLayout llshare=view.findViewById(R.id.ll_share);
        LinearLayout lledit=view.findViewById(R.id.ll_edit);
        LinearLayout lldele=view.findViewById(R.id.ll_dele);
        if (type==1)
        {
            llshare.setVisibility(View.VISIBLE);
            lledit.setVisibility(View.VISIBLE);
            lldele.setVisibility(View.VISIBLE);
        }else {
            llshare.setVisibility(View.VISIBLE);
            lledit.setVisibility(View.GONE);
            lldele.setVisibility(View.GONE);
        }

        return view;
    }

    private class GridViewAdapter extends SimpleAdapter<Integer> {

        public GridViewAdapter(Context context) {
            super(context, R.layout.item_sharelayout);
        }

        @Override
        protected void convert(BaseViewHolder viewHoder, Integer item) {
            AppCompatImageView imageView = viewHoder.getAppCompatImageView(R.id.iv_shareicon);
            imageView.setImageResource(item);
        }
    }
}
