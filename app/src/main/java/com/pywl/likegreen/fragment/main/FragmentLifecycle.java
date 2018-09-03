package com.pywl.likegreen.fragment.main;


public interface FragmentLifecycle {
    void onFragmentPause();
    void onFragmentResume();
    void onBackPressed();
    void onActivityPause();
    void onActivityResume();
    void onActivityDestroy();
}
