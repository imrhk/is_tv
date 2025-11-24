package com.litedevs.is_tv

import android.content.pm.PackageManager
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** IsTVPlugin */
class IsTVPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  private var pm : PackageManager? = null

  private val isTV : Boolean by lazy {
    pm?.hasSystemFeature(PackageManager.FEATURE_LEANBACK) ?: false
  }

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "is_tv")
    channel.setMethodCallHandler(this)

    pm = flutterPluginBinding.applicationContext.packageManager;
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "check") {
      result.success(isTV)
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
    pm = null;
  }
}
