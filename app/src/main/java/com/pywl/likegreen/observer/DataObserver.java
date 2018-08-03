package com.pywl.likegreen.observer;

import android.support.annotation.Nullable;

/**
 * 数据观察者接口
 * Created by Yang on 17/3/28.
 */
public interface DataObserver<DATA> {

    void onChange(@Nullable DATA data);
}
