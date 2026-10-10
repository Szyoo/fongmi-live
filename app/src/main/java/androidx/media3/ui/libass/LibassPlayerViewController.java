// fongmi-live 垫片：上游 FongMi/TV 依赖作者尚未公开的 media3 改动（FongMi/media 公开分支里没有此类）。
// 这里提供与调用方签名一致的最小实现，使直播专用版可以从公开源码构建。
// 直播用不到 libass 特效字幕、双字幕与弹幕，相关能力在此均为关闭状态。
package androidx.media3.ui.libass;

import androidx.annotation.Nullable;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.libass.LibassPlaybackSession;
import androidx.media3.exoplayer.libass.LibassSubtitleController;
import androidx.media3.ui.CaptionStyleCompat;
import androidx.media3.ui.PlayerView;
import androidx.media3.ui.SubtitleView;

import java.util.function.Consumer;

/**
 * libass 字幕叠加层。垫片：不叠加任何东西。
 * 注意 PlayerView 与播放器的绑定（setPlayer）由 PlaybackActivity 自行完成，不依赖这里。
 */
public final class LibassPlayerViewController {

    public LibassPlayerViewController(ExoPlayer player, LibassPlaybackSession session, LibassSubtitleController controller) {
    }

    public void bind(PlayerView playerView) {
    }

    public void setStyleOverride(@Nullable CaptionStyleCompat style, @Nullable String fontFamily) {
    }

    public void setSecondarySubtitleViewConfigurator(Consumer<SubtitleView> configurator) {
    }

    public void close() {
    }
}
