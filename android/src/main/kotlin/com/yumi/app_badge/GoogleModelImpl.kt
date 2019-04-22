package com.yumi.app_badge

import android.app.Notification
import android.content.Context
import android.content.Intent

/**
 * Created by pwy on 2019/4/22.
 */
class GoogleModelImpl : IconBadgeNumModel {
    companion object {

        private val NOTIFICATION_ERROR = "google not support before API O"
    }

    override fun setIconBadgeNum(context: Context, notification: Notification, count: Int): Notification {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {
            throw Exception(NOTIFICATION_ERROR)
        }
        val intent = Intent("android.intent.action.BADGE_COUNT_UPDATE")
        intent.putExtra("badge_count", count)
        intent.putExtra("badge_count_package_name", context.packageName)
        intent.putExtra("badge_count_class_name", context.getLaunchIntentForPackage()) // com.test. badge.MainActivity is your apk main activity

//        if (Utils.getInstance().canResolveBroadcast(context, intent)) {
        context.sendBroadcast(intent)
//        } else {
//            throw new Exception(UNABLE_TO_RESOLVE_INTENT_ERROR_ + intent.toString());
//        }

        return notification
    }
}