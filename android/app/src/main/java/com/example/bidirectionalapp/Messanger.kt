package com.example.bidirectionalapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

object Messanger : FlutterActivity() {
    val CHANNEL = "bidirectional"
    lateinit var flutterChannel: MethodChannel

    //TODO: To display image from flutter assets
//    var imagePath = byteArrayOf()
    //TODO: To display image from URL
    var imagePath = ""

//    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
//        super.configureFlutterEngine(flutterEngine)
//        val messanger : BinaryMessenger = flutterEngine.dartExecutor.binaryMessenger
//        flutterChannel = MethodChannel(messanger, CHANNEL)
//
//        flutterChannel.setMethodCallHandler{ call, result ->
//            if(call.method == "getMessage"){
//                result.success("Ack from android")
//            }
//        }
//    }

    fun sendData() {
        flutterChannel.invokeMethod("fromAndroid", "Values from Android")
    }

    fun sendIsSupported(message : String) {
        flutterChannel.invokeMethod("isSupported", message)
    }

    fun byteArrayToBitmap(data: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(data, 0, data.size)
    }

}