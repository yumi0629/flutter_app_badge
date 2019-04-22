import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:app_badge/app_badge.dart';

void main() {
  const MethodChannel channel = MethodChannel('app_badge');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await AppBadge.platformVersion, '42');
  });
}
