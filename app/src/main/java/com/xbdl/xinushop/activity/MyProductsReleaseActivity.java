package com.xbdl.xinushop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.MyProductsReleaseAdapter;

import java.util.ArrayList;

/*
我发布的商品
* */
public class MyProductsReleaseActivity extends AppCompatActivity {
    private LRecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products_release);
        initView();
        initData();
    }



    private void initView() {

        mRecyclerView =(LRecyclerView) findViewById(R.id.lr_products_list);

    }
    private void initData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("1");
        strings.add("1");
        MyProductsReleaseAdapter myProductsReleaseAdapter = new MyProductsReleaseAdapter(this);
        myProductsReleaseAdapter.setDataList(strings);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(myProductsReleaseAdapter);
        mRecyclerView.setAdapter(lRecyclerViewAdapter);
       DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.dp_5)
                .setColorResource(R.color.enptyviewbackground)
                .build();
        mRecyclerView.addItemDecoration(divider);
    }
}
