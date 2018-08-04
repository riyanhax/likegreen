package com.pywl.likegreen.utils;

import android.support.v4.app.Fragment;

import com.pywl.likegreen.fragment.mine.MyGardenFragment;
import com.pywl.likegreen.fragment.mine.MyLiveFragment;
import com.pywl.likegreen.fragment.mine.MyVideoFragment;
import com.pywl.likegreen.fragment.mine.MyWeddingCardFragment;

/**
 * Created by theWind on 2018/8/4.
 */
// fragments.add(new MyVideoFragment());
//fragments.add(new MyLiveFragment());
// fragments.add(new MyGardenFragment());
// fragments.add(new MyWeddingCardFragment());
public class HomeGetFragmentUtil {
    public static Fragment getInstance(int title) {
        switch (title){
            case 0:
                return new MyVideoFragment();
            case 1:
                return new MyLiveFragment();
            case 2:
                return new MyGardenFragment();
            case 3:
                return new MyWeddingCardFragment();
                default:
                    break;
        }
     return null;
    }
}
