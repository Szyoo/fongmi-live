// fongmi-live 垫片：上游 FongMi/TV 依赖作者尚未公开的 media3 改动（FongMi/media 公开分支里没有此类）。
// 这里提供与调用方签名一致的最小实现，使直播专用版可以从公开源码构建。
// 直播用不到 libass 特效字幕、双字幕与弹幕，相关能力在此均为关闭状态。
package androidx.media3.exoplayer.trackselection;

import android.content.Context;

import androidx.annotation.NonNull;

/** 双字幕轨道选择器。垫片：工厂原样透传给被包装的选择器工厂——这是播放器选轨的必经之路，不能为空实现。 */
public final class SecondaryTextTrackSelector {

    private SecondaryTextTrackSelector() {
    }

    public static final class Factory implements TrackSelector.Factory {

        private final TrackSelector.Factory delegate;

        public Factory(TrackSelector.Factory delegate) {
            this.delegate = delegate;
        }

        @NonNull
        @Override
        public TrackSelector createTrackSelector(@NonNull Context context) {
            return delegate.createTrackSelector(context);
        }
    }
}
