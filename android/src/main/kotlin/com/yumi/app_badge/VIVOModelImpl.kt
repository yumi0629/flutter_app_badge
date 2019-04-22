package com.yumi.app_badge

import android.app.Notification
import android.content.Context

/**
 * Created by pwy on 2019/4/22.
 */
class VIVOModelImpl:IconBadgeNumModel {
    override fun setIconBadgeNum(context: Context, notification: Notification, count: Int): Notification {
        throw  Exception("not support : vivo")
    }
}