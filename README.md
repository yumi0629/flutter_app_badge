# app_badge

Add unread count badges to Android & iOS devices.  
为APP桌面图标添加角标

![](https://raw.githubusercontent.com/yumi0629/PreImages/master/app_badge.gif)

## Usage:
```
AppBadge.setAppBadge(count);
```

## Support Devices
- [x] APPLE
- [x] HUAWEI
- [x] SAMSUNG
- [x] XIOAMI
- [x] GOOGLE
- [ ] MEIZU
- [ ] VIVO
- [ ] OPPO

## Add Custom Devices
* First, create a custom ModelImpl ```xxxModelImpl``` extends ```IconBadgeNumModel``` and override the function ```setIconBadgeNum()```:
```
class xxxModelImpl:IconBadgeNumModel {
    override fun setIconBadgeNum(context: Context, notification: Notification, count: Int): Notification {
        // return something...
    }
}
```
* Then, create a new custom launcher type in enum ```launchType``` in file ```LauncherHelper.kt```, add put the type mapping to file ```IconBadgeNumManger.kt```:
```
enum class LauncherType {
    GOOGLE,
    HUAWEI,
    MEIZU,
    XIAOMI,
    OPPO,
    VIVO,
    SAMSUNG,
    XXX
}
```
```
@Throws(Exception::class)
    private fun getIconBadgeNumModelByLauncher(launcherType: LauncherType): IconBadgeNumModel {
        when (launcherType) {
            ······
            LauncherType.XXX -> return xxxModelImpl()
            else -> throw Exception("not support your phone [ Build.MANUFACTURER is null ] $launcherType")
        }
    }

```

#### Welcome to PR if you can fit other Android devices !!!
