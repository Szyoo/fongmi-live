package com.fongmi.android.tv.api.loader;

import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderNull;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * fongmi-live：插件加载器的空壳实现。
 *
 * 上游的 BaseLoader 会按订阅源的配置下载 jar / js / py 插件并在本机执行
 * （JarLoader 经 DexClassLoader、JsLoader 经 QuickJS、PyLoader 经 Chaquopy）。
 * 直播专用版只解析 m3u / txt 频道列表，不需要任何插件，因此删除了三个真正的加载器，
 * 并把这里改成保留原有方法签名、但从不下载或执行外部代码的空壳：
 *   - 所有 getSpider 一律返回 SpiderNull；
 *   - 代理、解析扩展请求一律拒绝。
 * 保留签名是为了让调用方（VodConfig、LiveConfig、Site、Live、Proxy、ParseJob）无需改动，
 * 把与上游的差异集中在这一个文件里。
 * 刻意不提供原版的 dex(jar)：若上游新增对它的调用，编译会失败，提醒我们审查。
 */
public class BaseLoader {

    private BaseLoader() {
    }

    public static BaseLoader get() {
        return Loader.INSTANCE;
    }

    public void clear() {
    }

    public Spider getSpider(String key, String api, String ext, String jar) {
        return new SpiderNull();
    }

    public Spider getSpider(String key) {
        return new SpiderNull();
    }

    public void setRecent(String key, String api, String jar) {
    }

    public Object[] proxy(Map<String, String> params) {
        return null;
    }

    public void parseJar(String jar, boolean recent) {
    }

    public JSONObject jsonExt(String key, LinkedHashMap<String, String> jxs, String url) {
        throw new UnsupportedOperationException("plugins are disabled in fongmi-live");
    }

    public JSONObject jsonExtMix(String flag, String key, String name, LinkedHashMap<String, HashMap<String, String>> jxs, String url) {
        throw new UnsupportedOperationException("plugins are disabled in fongmi-live");
    }

    private static class Loader {
        static volatile BaseLoader INSTANCE = new BaseLoader();
    }
}
