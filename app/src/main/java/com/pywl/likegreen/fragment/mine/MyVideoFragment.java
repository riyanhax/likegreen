package com.pywl.likegreen.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.ItemDecoration.GridItemDecoration;
import com.github.jdsjlzx.ItemDecoration.SpacesItemDecoration;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.pywl.likegreen.R;
import com.pywl.likegreen.adapter.InitMyVideoAdatper;
import com.pywl.likegreen.adapter.MyVideoAdapter;
import com.pywl.likegreen.bean.MyVideoBean;

import java.util.ArrayList;

/**
 * Created by theWind on 2018/8/1.
 * 我的页面视频
 */

public class MyVideoFragment extends Fragment {
    private MyVideoAdapter mDataAdapter;
    private LRecyclerView mRecyclerView;
    private int pageSize;//一页刷新的数量
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_video, container, false);
        initView(view);
      //  initData();
        return view;
    }
   private void initView(View view ){
      // mRecyclerView = (LRecyclerView)view.findViewById(R.id.my_video_lrc);
   }
    private void initData(){
        //setRecyclerView();
    }
    private void setRecyclerView(){
        InitMyVideoAdatper initMyVideoAdatper = new InitMyVideoAdatper(getContext());
        //设置数据
        ArrayList<MyVideoBean> myVideoBeans = new ArrayList<>();
        //模拟
        MyVideoBean myVideoBean = new MyVideoBean();
        myVideoBean.setCount(60);
        //myVideoBean.setImgUrl("http://image.baidu.com/search/detail?z=0&word=L%20VISION&hs=0&pn=2&spn=0&di=0&pi=55588312587&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cs=54681040%2C3715509519&os=&simid=&adpicid=0&lpn=0&fm=&sme=&cg=&bdtype=-1&oriquery=&objurl=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb17eca8065380cd79a75c52cad44ad3458828183.jpg&fromurl=&gsm=0&catename=pcindexhot&islist=&querylist=");
        myVideoBeans.add(myVideoBean);
        initMyVideoAdatper.setDataList(myVideoBeans);
        mDataAdapter = new MyVideoAdapter(initMyVideoAdatper);
        mRecyclerView.setAdapter(mDataAdapter);
        //remove a HeaderView
        mDataAdapter.removeHeaderView();

       //remove a FooterView下拉刷新
        mDataAdapter.removeFooterView();
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        //下拉刷新后使用更新
        mRecyclerView.refreshComplete(pageSize);
        mDataAdapter.notifyDataSetChanged();
        int spacing = getResources().getDimensionPixelSize(R.dimen.dp_4);
        mRecyclerView.addItemDecoration(SpacesItemDecoration.newInstance(spacing, spacing, 1, Color.GRAY));

//根据需要选择使用GridItemDecoration还是SpacesItemDecoration
        GridItemDecoration divider = new GridItemDecoration.Builder(getContext())
                .setHorizontal(R.dimen.default_divider_padding)
                .setVertical(R.dimen.default_divider_padding)
                .setColorResource(R.color.white)
                .build();
     mRecyclerView.addItemDecoration(divider);
    }

}
