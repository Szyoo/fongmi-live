// fongmi-live 垫片：上游 FongMi/TV 依赖作者尚未公开的 media3 改动（FongMi/media 公开分支里没有此类）。
// 这里提供与调用方签名一致的最小实现，使直播专用版可以从公开源码构建。
// 直播用不到 libass 特效字幕、双字幕与弹幕，相关能力在此均为关闭状态。
package androidx.media3.exoplayer.libass;

/** libass 配置。垫片：只接收参数，不产生任何效果。 */
public final class LibassConfiguration {

    private LibassConfiguration() {
    }

    public static final class Builder {

        public Builder setFontConfig(String path) {
            return this;
        }

        public Builder setFontsDirectory(String path) {
            return this;
        }

        public Builder setDefaultFontFamily(String family) {
            return this;
        }

        public Builder setMaximumRenderPixels(long pixels) {
            return this;
        }

        public Builder setMaximumGlyphCount(int count) {
            return this;
        }

        public Builder setMaximumBitmapCacheSizeMb(int megabytes) {
            return this;
        }

        public LibassConfiguration build() {
            return new LibassConfiguration();
        }
    }
}
