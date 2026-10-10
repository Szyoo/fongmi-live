package com.fongmi.android.tv.utils;

public class Github {

    // fongmi-live：自动更新改为读取本分支在 GitHub Releases 上发布的版本，不再连接上游的 FongMi/Release。
    public static final String URL = "https://github.com/Szyoo/fongmi-live/releases/latest/download";

    private static String getUrl(String name) {
        return URL + "/" + name;
    }

    public static String getJson(String name) {
        return getUrl(name + ".json");
    }

    public static String getApk(String name) {
        return getUrl(name + ".apk");
    }
}
