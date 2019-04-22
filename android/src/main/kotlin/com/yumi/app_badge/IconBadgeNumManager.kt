package com.yumi.app_badge

import android.app.Notification
import android.content.Context
import android.support.annotation.NonNull

/**
 * Created by pwy on 2019/4/22.
 */
class IconBadgeNumManager : IconBadgeNumModel {
    private val iconBadgeNumModelMap: HashMap<LauncherType, IconBadgeNumModel> = HashMap()

    override fun setIconBadgeNum(context: Context, notification: Notification, count: Int): Notification {
        val iconBadgeNumModel = getSetIconBadgeNumModel(context)
        return iconBadgeNumModel!!.setIconBadgeNum(context, notification, count)
    }

    @Throws(Exception::class)
    private fun getSetIconBadgeNumModel(@NonNull context: Context): IconBadgeNumModel? {
        val launcherName = context.getLauncherPackageName()
        if (launcherName.isBlank()) {
            throw Exception("not support your launcher [ default launcher is null ]")
        }
        val launcherType = launcherName.getLauncherTypeByName() ?: throw Exception("not support launcher $launcherName")
        if (iconBadgeNumModelMap.containsKey(launcherType)) {
            return iconBadgeNumModelMap[launcherType]
        }
        val iconBadgeNumModel = getIconBadgeNumModelByLauncher(launcherType)
        iconBadgeNumModelMap[launcherType] = iconBadgeNumModel
        return iconBadgeNumModel
    }

    /**
     * 根据手机当前launcher获取相应Model
     */
    @Throws(Exception::class)
    private fun getIconBadgeNumModelByLauncher(launcherType: LauncherType): IconBadgeNumModel {
        when (launcherType) {
            LauncherType.HUAWEI -> return HuaWeiModelImpl()
            LauncherType.XIAOMI -> return XiaoMiModelImpl()
            LauncherType.VIVO -> return VIVOModelImpl()
            LauncherType.OPPO -> return OPPOModelImpl()
            LauncherType.SAMSUNG -> return SamsungModelImpl()
            LauncherType.MEIZU -> return MeizuModelImpl()
            LauncherType.GOOGLE -> return GoogleModelImpl()
            else -> throw Exception("not support your phone [ Build.MANUFACTURER is null ] $launcherType")
        }
    }

}