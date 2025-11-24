import Flutter
import UIKit

public class IsTVPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "is_tv", binaryMessenger: registrar.messenger())
    let instance = IsTVPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
      if(call.method == "check") {
        #if os(tvOS)
            result(true)
        #else
            result(false)
        #endif
      }
  }
}
