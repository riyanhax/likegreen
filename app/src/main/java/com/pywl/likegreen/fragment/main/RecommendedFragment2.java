package com.pywl.likegreen.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.pywl.likegreen.R;

import java.util.ArrayList;
import java.util.List;

import cn.qssq666.videoplayer.playermanager.manager.PlayerItemChangeListener;
import cn.qssq666.videoplayer.playermanager.manager.SingleVideoPlayerManager;
import cn.qssq666.videoplayer.playermanager.meta.MetaData;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

public class RecommendedFragment2 extends Fragment {
    private static final String TAG = "MainActivity";
   private  RecyclerView recyclerView;
   private HomeCustomAdapter adapter;
    private SingleVideoPlayerManager<VideoModel> playerManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended2, container, false);
        initDate();
        initView(view);
        return view;
    }

    private void initView(View view) {



        playerManager = new SingleVideoPlayerManager<VideoModel>(new PlayerItemChangeListener() {
            @Override
            public void onPlayerItemChanged(final MetaData currentItemMetaData) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                Toast.makeText(MainActivity.this, "current:" + currentItemMetaData, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }) {

            @Override
            public void onVideoPlayTimeChanged(int positionInMilliseconds) {

                //horizontalProgressBar.setProgress((int) (playerManager.getCurrentPlayer().getDuration() / positionInMilliseconds * 100f));
            }

            @Override

            public void onProgressUpdate(int percent) {
                super.onProgressUpdate(percent);
            }
        };
        recyclerView = (RecyclerView) view.findViewById(R.id.video_list);
        adapter = new HomeCustomAdapter(getActivity());
        List<VideoModel> list = new ArrayList<>();
        list.add(new VideoModel().setImage("http://m9pic.mm999.com/topic/201804/20180427092549414.jpg").setPath("http://m9pic.mm999.com/video/201804/20180427092550022.mp4").setName("测试1"));
        list.add(new VideoModel().setImage("http://image.so.com/v?q=%E9%A3%8E%E6%99%AF%E7%BC%A9&src=srp&correct=%E9%A3%8E%E6%99%AF%E7%BC%A9&cmsid=40fdc799be0c989382e64df91dd94916&cmran=0&cmras=0&cn=0&gn=0&kn=0#multiple=0&gsrc=1&dataindex=6&id=040657b966c686607f29b0770877758c&currsn=0&jdx=6&fsn=60").setPath("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/1BrIAtvV_1818587477_shd.mp4").setName("测试2"));
        list.add(new VideoModel().setImage("http://image.so.com/v?q=%E9%A3%8E%E6%99%AF%E7%BC%A9&src=srp&correct=%E9%A3%8E%E6%99%AF%E7%BC%A9&cmsid=40fdc799be0c989382e64df91dd94916&cmran=0&cmras=0&cn=0&gn=0&kn=0#multiple=0&gsrc=1&dataindex=33&id=03e2f792ddf64cc77c8b3d5c8d31a845&currsn=0&jdx=33&fsn=60").setPath("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/FmdVOTqd_1818586962_shd.mp4").setName("测试3"));
        list.add(new VideoModel().setImage("http://image.so.com/v?q=%E9%A3%8E%E6%99%AF%E7%BC%A9&src=srp&correct=%E9%A3%8E%E6%99%AF%E7%BC%A9&cmsid=40fdc799be0c989382e64df91dd94916&cmran=0&cmras=0&cn=0&gn=0&kn=0#multiple=0&gsrc=1&dataindex=37&id=78735e1239adbb1e16d886ce781e0ba7&currsn=0&jdx=37&fsn=60").setPath("http://vodhj5bqn44.vod.126.net/vodhj5bqn44/wq1e35cQ_1818588221_shd.mp4").setName("测试4"));
        list.add(new VideoModel().setImage("http://m9pic.mm999.com/topic/201804/20180427092549414.jpg").setPath("rtmp://vee0d5105.live.126.net/live/697e719fa8314e329d27421b7dad681e").setName("测试5"));

        adapter.setList(list);

        adapter.setPlayMnager(playerManager);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int actualCurrentPosition = 0;
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //第一次没法解决

                    autoPlayVideo(recyclerView);

                }

            }
        });
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                autoPlayVideo(recyclerView);
                Log.w(TAG, "V" + left + ",top:" + top + ",right:" + right + ",bottom:" + bottom + ",oldLeft:" + oldLeft + ",oldTop:" + oldTop + ",oldRight:" + oldRight);
                recyclerView.removeOnLayoutChangeListener(this);

            }
        });


    }


    private void autoPlayVideo(RecyclerView view) {


        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        int position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();//完全可见


        if (position < 0 || position >= adapter.getList().size()) {
            Log.w(TAG, "position 无法寻找:" + position);
            return;
        } else {
            Log.w(TAG, "position 正常:" + position);
        }


        MetaData metaData = new MetaData() {

        };

        VideoModel model1 = adapter.getList().get(position);

        VideoViewHolder viewHolder = (VideoViewHolder) view.findViewHolderForLayoutPosition(position);
        if (viewHolder == null) {
            return;
        }

        if (playerManager.isCurrent(model1.getPath())) {
            if (playerManager.isPause()) {
                playerManager.continuePlay();

            } else if (playerManager.isPlay()) {
                playerManager.pause();

            } else {

                HomeCustomAdapter.play(playerManager, viewHolder.itemView, model1, viewHolder);

            }
        } else {

            HomeCustomAdapter.play(playerManager, viewHolder.itemView, model1, viewHolder);

        }

    }

/*
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_continue:
                playerManager.onResume();

                break;
            case R.id.btn_pause:
                playerManager.onPause();
                break;

            case R.id.btn_swtich:
                Intent intent = new Intent(this, FullScreenPageActivity.class);
                startActivity(intent);
                break;
        }
    }*/

//        loginActivityBinding.videoView.setVideoURI(Uri.parse(Constants.VIDEO_PATH));
    // String VIDEO_PATH = "android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.raw.login;


    @Override
    public void onResume() {
        super.onResume();
        if (playerManager!=null)
        playerManager.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        playerManager.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        playerManager.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        playerManager.destory();
    }
    private void initDate() {

    }
}
