package com.yumi.app_badge

import android.content.Context
import android.content.Intent
/**
 * Created by pwy on 2019/4/22.
 */
fun Context.getLaunchIntentForPackage(): String {
    return packageManager?.getLaunchIntentForPackage(packageName)?.component?.className ?: ""
}

fun Context.canResolveBroadcast(intent: Intent): Boolean {
    val packageManager = packageManager
    val receivers = packageManager.queryBroadcastReceivers(intent, 0)
    return receivers != null && receivers.size > 0
}