package com.xbdl.xinushop.base;

import android.os.Bundle;


public abstract class BaseActivityMvp<V, T extends BasePresenter<V>> extends BaseActivity {

        public T presenter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            presenter = initPresenter();
        }

        @Override
        public void onResume() {
            super.onResume();
            presenter.attachView((V) this);
        }

        @Override
        public void onDestroy() {
            presenter.detachView();
            super.onDestroy();
        }

        // 实例化presenter
        public abstract T initPresenter();
    }


