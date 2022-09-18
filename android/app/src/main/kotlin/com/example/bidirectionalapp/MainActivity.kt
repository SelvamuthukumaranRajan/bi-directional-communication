package com.example.bidirectionalapp

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.ar.core.ArCoreApk
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val messanger: BinaryMessenger = flutterEngine.dartExecutor.binaryMessenger
        Messanger.flutterChannel = MethodChannel(messanger, Messanger.CHANNEL)

        Messanger.flutterChannel.setMethodCallHandler { call, result ->
            if (call.method == "getMessage") {
                result.success("Ack from android")

                //From local
//                Messanger.imagePath = call.arguments as ByteArray
                //From URL
                Messanger.imagePath = call.arguments as String

                Log.e("ImagePath",Messanger.imagePath)

                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)


//                //TODO: Method handling
//                if (isARSupported()) {
//                    val intent = Intent(this, SecondActivity::class.java)
//                    startActivity(intent)
//                } else {
//                    Messanger.sendIsSupported("Sorry! This AR feature is not supported yet on this device!")
////                    Messanger.flutterChannel.invokeMethod("isSupported", "Sorry! This AR feature is not supported yet on this device!")
//                }
            }
        }
    }

    //TODO: Need to add this function
    private fun isARSupported() : Boolean{
        val availability = ArCoreApk.getInstance().checkAvailability(this)
        return availability.isSupported
    }

}

