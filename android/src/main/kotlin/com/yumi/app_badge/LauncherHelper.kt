package com.yumi.app_badge

import android.content.Context
import android.content.Intent

/**
 * Created by pwy on 2019/4/22.
 */
enum class LauncherType {
    GOOGLE,
    HUAWEI,
    MEIZU,
    XIAOMI,
    OPPO,
    VIVO,
    SAMSUNG
}

private val launcherMap by lazy(mode = LazyThreadSafetyMode.NONE) {
    val launcherMap = HashMap<String, LauncherType>()
    launcherMap.put("com.huawei.android.launcher", LauncherType.HUAWEI)
    launcherMap.put("com.miui.home", LauncherType.XIAOMI)
    launcherMap.put("com.sec.android.app.launcher", LauncherType.SAMSUNG)
    launcherMap.put("com.google.android.apps.nexuslauncher", LauncherType.GOOGLE)
    return@lazy launcherMap
}

fun String.getLauncherTypeByName(): LauncherType? {
    return launcherMap.get(this)
}

fun Context.getLauncherPackageName(): String {
    val intent = Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_HOME);
    val res = packageManager.resolveActivity(intent, 0);
    if (res.activityInfo == null) {
        // should not happen. A home is always installed, isn't it?
        return ""
    }
    if (res.activityInfo.packageName.equals("android")) {
        return ""
    } else {
        return res.activityInfo.packageName;
    }
}