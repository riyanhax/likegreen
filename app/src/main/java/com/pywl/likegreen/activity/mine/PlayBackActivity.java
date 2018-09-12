package com.pywl.likegreen.activity.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.pywl.likegreen.R;

/*
* 回放
* */
public class PlayBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_back);
        initView();
        initDate();
    }


    private void initView() {
       //mXrPlayBack=(XRecyclerView)findViewById(R.id.xr_playback_list);
    }
    private void initDate() {
      /*  List<String> arrayList = new ArrayList<>();
        PlayBackAdapter playBackAdapter = new PlayBackAdapter(this, arrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXrPlayBack.setLayoutManager(layoutManager);
        mXrPlayBack.setAdapter(playBackAdapter);*/

    }

}
