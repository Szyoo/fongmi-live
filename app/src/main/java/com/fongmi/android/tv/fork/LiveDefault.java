package com.fongmi.android.tv.fork;

import com.fongmi.android.tv.bean.Config;

/**
 * fongmi-live：直播专用版的默认频道源。
 *
 * 首次启动（数据库里还没有任何直播源）时，写入指向本仓库 channels 分支的频道列表，
 * 使电视打开即可播放，无需用遥控器输入地址。之后换源走控制栏「配置」按钮，
 * 或直接修改 channels 分支里的 live.m3u（电视下次加载时生效，无需重新编译）。
 */
public final class LiveDefault {

    public static final String NAME = "直播";
    public static final String URL = "https://raw.githubusercontent.com/Szyoo/fongmi-live/channels/live.m3u";

    private LiveDefault() {
    }

    public static void ensure() {
        if (!Config.live().isEmpty()) return;
        Config.find(URL, NAME, 1);
    }
}
