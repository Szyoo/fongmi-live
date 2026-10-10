// fongmi-live 垫片：上游 FongMi/TV 依赖作者尚未公开的 media3 改动（FongMi/media 公开分支里没有此类）。
// 这里提供与调用方签名一致的最小实现，使直播专用版可以从公开源码构建。
// 直播用不到 libass 特效字幕、双字幕与弹幕，相关能力在此均为关闭状态。
package androidx.media3.ui.danmaku;

import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.media3.ui.PlayerView;

import okhttp3.OkHttpClient;

/** 弹幕叠加层。垫片：不显示弹幕。 */
public final class DanmakuPlayerViewController {

    public void bind(PlayerView playerView) {
    }

    public void setOkHttpClient(OkHttpClient client) {
    }

    public void setEnabled(boolean enabled) {
    }

    public void setConfig(DanmakuConfig config) {
    }

    public void setDataSource(@Nullable Uri uri) {
    }

    public void sendNow(String text) {
    }

    public void close() {
    }
}
