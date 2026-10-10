package com.fongmi.android.tv.fork;

import androidx.fragment.app.FragmentActivity;

import com.fongmi.android.tv.Updater;
import com.fongmi.android.tv.setting.Setting;

/**
 * fongmi-live：本分支对上游代码的唯一挂载点。
 *
 * 上游的首页（HomeActivity）在直播专用版里被摘除，原本由首页承担的启动工作挪到这里，
 * 由 LiveActivity 在创建时调用一次。把分支逻辑集中在独立文件里，可以让上游文件里的
 * 改动保持在最少的几行，降低每次 rebase 的冲突概率。
 */
public final class Fork {

    private Fork() {
    }

    public static void onLiveCreate(FragmentActivity activity) {
        LiveDefault.ensure();
        // 上游在更新对话框点「取消」后会永久关闭检查更新，重新开启的开关在设置页里——
        // 而设置页在直播版中已被摘除。这里每次启动都重新开启，让「取消」只作用于本次。
        Setting.putUpdate(true);
        Updater.create().start(activity);
    }
}
