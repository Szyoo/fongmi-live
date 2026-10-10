// fongmi-live 垫片：上游 FongMi/TV 依赖作者尚未公开的 media3 改动（FongMi/media 公开分支里没有此类）。
// 这里提供与调用方签名一致的最小实现，使直播专用版可以从公开源码构建。
// 直播用不到 libass 特效字幕、双字幕与弹幕，相关能力在此均为关闭状态。
package androidx.media3.exoplayer.libass;

import androidx.annotation.Nullable;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.text.SecondaryTextOutput;
import androidx.media3.exoplayer.trackselection.SecondaryTextTrackSelector;

import java.util.Collections;
import java.util.List;

/** libass / 双字幕控制器。垫片：没有第二字幕，所有选择均为空。主字幕仍由 ExoPlayer 正常选择。 */
public final class LibassSubtitleController {

    public LibassSubtitleController(ExoPlayer player, LibassPlaybackSession session, SecondaryTextTrackSelector.Factory trackSelectorFactory, SecondaryTextOutput output) {
    }

    @Nullable
    public TrackSelectionOverride getPrimaryTextTrackSelectionOverride() {
        return null;
    }

    @Nullable
    public TrackSelectionOverride getSecondaryTextTrackSelectionOverride() {
        return null;
    }

    public List<TrackSelectionOverride> getSecondaryTextTrackSelectionOverrides() {
        return Collections.emptyList();
    }

    public boolean isSecondaryTextTrackSuppressed() {
        return false;
    }

    public void setSecondaryTextTrackSelectionOverride(@Nullable TrackSelectionOverride override) {
    }

    public void setSecondaryTextTrackAutoSelectionEnabled(boolean enabled) {
    }

    public void close() {
    }
}
