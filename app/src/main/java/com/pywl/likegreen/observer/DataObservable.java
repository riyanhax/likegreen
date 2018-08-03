package com.pywl.likegreen.observer;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * 数据观察者.
 * <p/>
 * Created by Yang on 17/3/28.
 */
public class DataObservable<DATA> {

    private DATA mData;

    private final Set<DataObserver<DATA>> mObserverSet = new HashSet<>();

    @MainThread
    public void update(DATA data) {
        mData = data;
        notifyChanged();
    }

    public DATA get() {
        return mData;
    }

    public final void notifyChanged() {
        for (DataObserver<DATA> observer : mObserverSet) {
            if (observer != null) {
                observer.onChange(mData);
            }
        }
    }

    @MainThread
    public void registerDataObserver(@NonNull DataObserver<DATA> observer) {
        mObserverSet.add(observer);
        observer.onChange(mData);
    }

    @MainThread
    public void unregisterDataObserver(@NonNull DataObserver<DATA> observer) {
        mObserverSet.remove(observer);
    }
}
