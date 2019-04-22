package com.yumi.app_badge

import android.app.Notification
import android.content.Context
import android.content.Intent


/**
 * Created by pwy on 2019/4/22.
 */
class SamsungModelImpl : IconBadgeNumModel {
    override fun setIconBadgeNum(context: Context, notification: Notification, count: Int): Notification {
        val intent = Intent("android.intent.action.BADGE_COUNT_UPDATE")
        intent.putExtra("badge_count", count)
        intent.putExtra("badge_count_package_name", context.packageName)
        intent.putExtra("badge_count_class_name", context.getLaunchIntentForPackage())

        if (context.canResolveBroadcast(intent)) {
            context.sendBroadcast(intent)
        } else {
            throw Exception("not support : samsung $intent")
        }
        return notification
    }
}