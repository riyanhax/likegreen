package com.pywl.likegreen.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pywl.likegreen.R;
import com.pywl.likegreen.activity.mine.JoinThemeActivity;
import com.pywl.likegreen.activity.mine.LongPostActivity;
import com.pywl.likegreen.activity.mine.ShareLiftActivity;

/**
 * Created by theWind on 2018/8/1.
 * 喜帖
 */

public class MyWeddingCardFragment extends Fragment implements View.OnClickListener {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_weddingcard, container, false);
        initView(view);
        return view;
    }

    private void initView(View v) {
        v.findViewById(R.id.rl_my_trailer).setOnClickListener(this);
        v.findViewById(R.id.rl_my_join_theme).setOnClickListener(this);
        v.findViewById(R.id.rl_my_longpost).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_my_trailer:
                Intent intentShareLiftActivity = new Intent(getActivity(), ShareLiftActivity.class);
                startActivity(intentShareLiftActivity);
                break;
            case R.id.rl_my_join_theme:
                Intent intentJoinThemeActivity = new Intent(getActivity(), JoinThemeActivity.class);
                startActivity(intentJoinThemeActivity);
                break;
            case R.id.rl_my_longpost:
                Intent intentLongPostActivity = new Intent(getActivity(), LongPostActivity.class);
                startActivity(intentLongPostActivity);
                break;
        }
    }
}
