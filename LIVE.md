# fongmi-live：直播专用版

基于 [FongMi/TV](https://github.com/FongMi/TV) 的 Android TV 直播专用分支。打开即直播，只保留看电视直播所需的功能。

- 分支 `live`：本分支的全部改动，定期 rebase 到上游 `fongmi` 分支。
- 分支 `fongmi`：上游原样，仅作参照。
- 发布：GitHub Releases（`latest`），App 内自动更新从这里拉取。

## 与上游的差异

| 方面 | 上游 | 本分支 |
|---|---|---|
| 入口 | 首页（点播聚合） | 直播页，开机即播 |
| 界面 | 点播、搜索、收藏、历史、投屏、文件、推送、设置… | 仅直播页（其余经清单覆盖层摘除，R8 从 APK 中剥离） |
| 插件 | 按订阅源下载 jar / js / py 插件并执行 | **完全禁用**：三个加载器已删除，`BaseLoader` 为不执行外部代码的空壳 |
| Python / JS 引擎 | Chaquopy、QuickJS 模块 | 移除 |
| 闭源组件 | 迅雷、荐片、Forcetech、TVBus、包名伪装 hook（预编译 aar） | 移除 |
| 播放引擎 | ExoPlayer + mpv | 仅 ExoPlayer（保留 FFmpeg 软解兜底） |
| 权限 | 含录音、全部存储、开机自启、组播 | 仅联网、播放服务、通知、安装更新 |
| 自动更新 | `FongMi/Release` | 本仓库 Releases |
| 包名 / 桌面名 | `com.fongmi.android.tv` / 影视 | `com.fongmi.android.tv.live` / 直播（可与官方版共存） |

## 频道源

首次启动时写入默认源（`fork/LiveDefault.java` 中的 `URL`），支持 m3u / txt 频道列表。

换源：直播页控制栏「配置」按钮 → 输入地址，或用手机扫描对话框里的二维码在手机上输入后推送。

## 构建

上游的播放器依赖作者魔改的 media3（[FongMi/media](https://github.com/FongMi/media) 的 `release-*-fongmi` 分支），其 AAR 不在本仓库中，需先编译：

```bash
git clone --depth 1 --branch release-1.11.0-fongmi https://github.com/FongMi/media.git ../fongmi-media
cd ../fongmi-media && echo "sdk.dir=$ANDROID_HOME" > local.properties
# 编译 move.txt 中列出的 20 个模块，再把 lib-*-release.aar 拷到本仓库 app/libs/
```

然后在本仓库根目录写 `local.properties`（`sdk.dir`、`storeFile`、`keyAlias`、`storePassword`），执行：

```bash
bash ./gradlew :app:assembleLeanbackRelease
```

产物：`app/build/outputs/apk/leanback/release/leanback-{armeabi_v7a,arm64_v8a}.apk`。

需要 JDK 21、Android SDK（platform 37、build-tools 37.0.0）；首次编译 media3 会自动安装 NDK。CI 的完整流程见 `.github/workflows/live.yml`。

## 维护

**同步上游**：`.github/workflows/live.yml` 每周一 03:00 JST 运行 `.github/scripts/sync-upstream.sh`，把 `live` rebase 到上游最新代码。

- 上游修改了本分支删除的文件 → 自动保持删除并继续。
- 其他任何冲突 → 任务失败（GitHub 邮件通知），需人工 rebase。

**改动设计**：为降低 rebase 冲突，本分支尽量用新文件实现功能，少改上游文件：

- `app/live.gradle`：全部构建定制（包名后缀、版本号、原生库剔除、垫片退场）。`app/build.gradle` 只追加一行 `apply from`。
- `app/src/leanbackRelease/AndroidManifest.xml`：清单覆盖层（入口、摘除组件、摘除权限）。
- `app/src/main/java/com/fongmi/android/tv/fork/`：本分支逻辑的挂载点。
- `app/src/main/java/androidx/media3/`：**media3 垫片**，见下。

**media3 垫片**：上游从 2026-08-27 起依赖作者尚未公开的 media3 改动（libass 字幕、双字幕、弹幕控制器），公开的 FongMi/media 里没有这些类。垫片提供签名一致的最小实现（能力均为关闭）。其中 `SecondaryTextTrackSelector.Factory` 是播放器选轨的必经之路，**必须透传**，不可写成空实现。作者公开同名类后，`live.gradle` 会自动跳过对应垫片改用真实实现。

**已知局限**：上游公开源码通常落后于其发布的二进制几个版本（例如源码 5.6.3、官方 APK 5.7.0），本分支只能跟进公开源码。
