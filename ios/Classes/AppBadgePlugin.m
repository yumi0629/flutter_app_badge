#import "AppBadgePlugin.h"
#import <app_badge/app_badge-Swift.h>

@implementation AppBadgePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
    //  [SwiftAppBadgePlugin registerWithRegistrar:registrar];
    FlutterMethodChannel* channel = [FlutterMethodChannel
                                     methodChannelWithName:@"app_badge"
                                     binaryMessenger:[registrar messenger]];
    AppBadgePlugin* instance = [[AppBadgePlugin alloc] init];
    [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
    if ([@"getPlatformVersion" isEqualToString:call.method]) {
        result([@"iOS " stringByAppendingString:[[UIDevice currentDevice] systemVersion]]);
    }if([@"setAppBadge" isEqualToString:call.method]){
        NSDictionary *args = call.arguments;
        int count = [[args valueForKey:@"count"] intValue];
        UIApplication *app = [UIApplication sharedApplication];
        // 应用程序右上角数字
        app.applicationIconBadgeNumber = count;
        // 创建通知对象
        UIUserNotificationSettings *setting = [UIUserNotificationSettings settingsForTypes:UIUserNotificationTypeBadge categories:nil];
        // 注册用户通知
        [app registerUserNotificationSettings:setting];
        result([NSNumber numberWithBool:true]);
    } else {
        result(FlutterMethodNotImplemented);
    }
}

@end
