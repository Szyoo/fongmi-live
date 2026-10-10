// fongmi-live 垫片：上游 FongMi/TV 依赖作者尚未公开的 media3 改动（FongMi/media 公开分支里没有此类）。
// 这里提供与调用方签名一致的最小实现，使直播专用版可以从公开源码构建。
// 直播用不到 libass 特效字幕、双字幕与弹幕，相关能力在此均为关闭状态。
package androidx.media3.exoplayer.libass;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;

/** 字体族名读取。垫片：不解析字体文件，返回 null（调用方已按「读取失败」处理）。 */
public final class LibassFontFile {

    private LibassFontFile() {
    }

    @Nullable
    public static String getFamilyName(File file) throws IOException {
        return null;
    }
}
