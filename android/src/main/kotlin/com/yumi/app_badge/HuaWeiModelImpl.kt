package com.yumi.app_badge

import android.app.Notification
import android.content.Context
import android.net.Uri
import android.os.Bundle

/**
 * Created by pwy on 2019/4/22.
 */
class HuaWeiModelImpl : IconBadgeNumModel {
    override fun setIconBadgeNum(context: Context, notification: Notification, count: Int): Notification {
        val bunlde = Bundle()
        bunlde.putString("package", context.packageName) // com.test.badge is your package name
        bunlde.putString("class", context.getLaunchIntentForPackage()) // com.test. badge.MainActivity is your apk main activity
        bunlde.putInt("badgenumber", count)
        context.contentResolver.call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bunlde)
        return notification
    }
}