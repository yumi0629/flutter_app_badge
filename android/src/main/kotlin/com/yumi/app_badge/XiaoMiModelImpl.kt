package com.yumi.app_badge

import android.app.Notification
import android.content.Context

/**
 * Created by pwy on 2019/4/22.
 */
class XiaoMiModelImpl:IconBadgeNumModel {
    override fun setIconBadgeNum(context: Context, notification: Notification, count: Int): Notification {
        val field = notification.javaClass.getDeclaredField("extraNotification")
        val extraNotification = field.get(notification)
        val method = extraNotification.javaClass.getDeclaredMethod("setMessageCount", Int::class.javaPrimitiveType)
        method.invoke(extraNotification, count)
        return notification
    }
}