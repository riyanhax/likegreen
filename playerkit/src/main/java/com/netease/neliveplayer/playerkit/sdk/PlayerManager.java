package com.netease.neliveplayer.playerkit.sdk;

import android.content.Context;

import com.netease.neliveplayer.playerkit.core.player.PlayerManagerImpl;
import com.netease.neliveplayer.playerkit.sdk.model.SDKOptions;
import com.netease.neliveplayer.playerkit.sdk.model.VideoOptions;
import com.netease.neliveplayer.sdk.NEMediaDataSource;

import java.util.ArrayList;
import java.util.Map;

/**
 * 网易云信视频播放器管理类
 * 基于播放器SDK封装的全局操作接口
 * <p>
 * @author netease
 */
public class PlayerManager {
    /**
     * 初始化SDK,使用播放器时必须先进行初始化才能进行后续操作。
     *
     * @param context 调用上下文
     * @param config sdk配置信息
     */
    public static void init(Context context, SDKOptions config) {
        PlayerManagerImpl.init(context,config);
    }

    /**
     * 获取是否已经准备好so库文件
     * 仅在初始化 init 接口中配置动态加载才能使用该接口查询
     * @return 是否准备好
     */
    public static boolean isDynamicLoadReady(){
        return PlayerManagerImpl.isDynamicLoadReady();
    }


    /**
     * 构造播放器实例对象
     *
     * @param context   上下文
     * @param videoPath 视频资源路径
     * @param options   播放选项
     * @return 播放器实例对象
     */
    public static LivePlayer buildLivePlayer(Context context, String videoPath, VideoOptions options) {
        return PlayerManagerImpl.buildLivePlayer(context, videoPath, options);
    }

    /**
     * 构造播放器实例对象
     *
     * @param context   上下文
     * @param videoPath 视频资源路径
     * @param options   播放选项
     * @return 播放器实例对象
     */
    public static VodPlayer buildVodPlayer(Context context, String videoPath, VideoOptions options) {
        return PlayerManagerImpl.buildVodPlayer(context, videoPath, options);
    }

    /**
     * 构造播放器实例对象
     *
     * @param context   上下文
     * @param mediaDataSource 自定义视频资源
     * @param options   播放选项
     * @return 播放器实例对象
     */
    public static VodPlayer buildVodPlayer(Context context, NEMediaDataSource mediaDataSource, VideoOptions options) {
        return PlayerManagerImpl.buildVodPlayer(context, mediaDataSource, options);
    }

    /**
     * 添加预加载拉流链接地址
     * @param urls 拉流链接地址
     */
    public static void addPreloadUrls(ArrayList<String> urls){
        PlayerManagerImpl.addPreloadUrls(urls);
    }

    /**
     * 移除预加载拉流链接地址
     * @param urls 拉流链接地址
     */
    public static void removePreloadUrls(ArrayList<String> urls){
        PlayerManagerImpl.removePreloadUrls(urls);

    }

    /**
     * 查询预加载拉流链接地址的结果信息
     * @return Map<String,Integer> String是链接地址，Integer是状态,状态码参考 {@link com.netease.neliveplayer.sdk.constant.NEPreloadStatusType}
     */
    public static Map<String,Integer> queryPreloadUrls(){
        return  PlayerManagerImpl.queryPreloadUrls();
    }

    /**
     * 是否注册播放器释放的回调
     * @param context
     * @param observer
     * @param register
     */
    public static void registerPlayerReceiver(Context context, PlayerReleaseObserver observer, boolean register) {
        PlayerManagerImpl.registerPlayerReceiver(context,observer,register);
    }
}
