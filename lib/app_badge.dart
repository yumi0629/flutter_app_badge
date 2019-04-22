import 'dart:async';

import 'package:flutter/services.dart';

class AppBadge {
  static const MethodChannel _channel = const MethodChannel('app_badge');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<bool> setAppBadge(int count) async {
    bool result = await _channel.invokeMethod('setAppBadge', {"count": count});
    return result;
  }
}
