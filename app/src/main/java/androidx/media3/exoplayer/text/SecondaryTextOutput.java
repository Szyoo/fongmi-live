// fongmi-live 垫片：上游 FongMi/TV 依赖作者尚未公开的 media3 改动（FongMi/media 公开分支里没有此类）。
// 这里提供与调用方签名一致的最小实现，使直播专用版可以从公开源码构建。
// 直播用不到 libass 特效字幕、双字幕与弹幕，相关能力在此均为关闭状态。
package androidx.media3.exoplayer.text;

import androidx.annotation.NonNull;
import androidx.media3.common.text.CueGroup;

/** 第二字幕输出。垫片：丢弃所有字幕（主字幕仍由 ExoPlayer 自带的渲染路径显示）。 */
public final class SecondaryTextOutput implements TextOutput {

    @Override
    public void onCues(@NonNull CueGroup cueGroup) {
    }
}
