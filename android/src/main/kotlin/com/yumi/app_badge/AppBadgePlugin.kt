package com.yumi.app_badge

import android.app.Notification
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat


class AppBadgePlugin(val appContext: Context) : MethodCallHandler {

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "app_badge")
            channel.setMethodCallHandler(AppBadgePlugin(registrar.context().applicationContext))
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when (call.method) {
            "getPlatformVersion" -> {
                result.success("Android ${android.os.Build.VERSION.RELEASE}")
            }
            "setAppBadge" -> {
                setAppBadge(call, result)
            }
            else -> {
                result.notImplemented()
            }
        }
    }

    private val iconBadgeNumManager by lazy(mode = LazyThreadSafetyMode.NONE) {
        IconBadgeNumManager()
    }

    private fun setAppBadge(call: MethodCall, result: Result) {
        val count = call.argument<Int>("count")
        if (count == null) {
            result.success(false)
        } else {
            sendIconNumNotification(count)
            result.success(true)
        }
    }

    private fun sendIconNumNotification(count: Int) {
        val nm = appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var notificationChannelId = ""
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = createNotificationChannel()
            nm.createNotificationChannel(notificationChannel)
            notificationChannelId = notificationChannel.id
        }
        var notification: Notification?
        try {
            notification = NotificationCompat.Builder(appContext, notificationChannelId)
                .setSmallIcon(appContext.applicationInfo.icon)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("title")
                .setContentText("content num: $count")
                .setTicker("ticker")
                .setAutoCancel(true)
                .setNumber(count)
                .build()
            notification = iconBadgeNumManager.setIconBadgeNum(appContext, notification, count)
            nm.notify(32154, notification)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun createNotificationChannel(): NotificationChannel {
        val channelId = "test"
        val channel = NotificationChannel(channelId,
            "Channel1", NotificationManager.IMPORTANCE_DEFAULT)
        channel.enableLights(true) //是否在桌面icon右上角展示小红点
        channel.lightColor = Color.RED //小红点颜色
        channel.setShowBadge(true) //是否在久按桌面图标时显示此渠道的通知
        return channel
    }

}
