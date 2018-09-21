package com.pywl.likegreen.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<T>{
    protected Reference<T> viewRef;  //弱引用,防止内存泄漏
    public  void attachView(T view){
        viewRef=new WeakReference<T>(view);
    }
    public  void detachView(){
        if(viewRef!=null){
            viewRef.clear();
            viewRef=null;
        }
    }

}
