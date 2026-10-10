// fongmi-live 垫片：上游 FongMi/TV 依赖作者尚未公开的 media3 改动（FongMi/media 公开分支里没有此类）。
// 这里提供与调用方签名一致的最小实现，使直播专用版可以从公开源码构建。
// 直播用不到 libass 特效字幕、双字幕与弹幕，相关能力在此均为关闭状态。
package androidx.media3.exoplayer.libass;

import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.text.SubtitleParser;

/**
 * libass 播放会话。垫片：isAvailable() 恒为 false。
 * 调用方据此走 ExoPlayer 默认路径——不挂载 libass 渲染器（ExoUtil）、
 * 用默认 MediaSourceFactory 创建媒体源（ExoMediaSourceFactory），
 * 所以 createClockRenderer / createMediaComponents 在本版本中不可达。
 */
public final class LibassPlaybackSession {

    public LibassPlaybackSession(LibassConfiguration configuration, boolean enabled) {
    }

    public boolean isAvailable() {
        return false;
    }

    public void setPreloadMediaItem(@Nullable MediaItem mediaItem) {
    }

    public void setBottomPositionFraction(float fraction) {
    }

    public void setSecondaryBottomPositionFraction(float fraction) {
    }

    public void setFontScale(float scale, boolean applyToAss) {
    }

    public Renderer createClockRenderer() {
        throw new IllegalStateException("libass is unavailable in fongmi-live");
    }

    public MediaComponents createMediaComponents(MediaItem mediaItem, ExtractorsFactory extractorsFactory) {
        throw new IllegalStateException("libass is unavailable in fongmi-live");
    }

    public void close() {
    }

    public static final class MediaComponents {

        public final ExtractorsFactory extractorsFactory;
        public final SubtitleParser.Factory subtitleParserFactory;

        public MediaComponents(ExtractorsFactory extractorsFactory, SubtitleParser.Factory subtitleParserFactory) {
            this.extractorsFactory = extractorsFactory;
            this.subtitleParserFactory = subtitleParserFactory;
        }
    }
}
