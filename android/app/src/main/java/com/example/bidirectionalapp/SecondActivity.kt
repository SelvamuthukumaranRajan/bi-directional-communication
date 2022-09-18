package com.example.bidirectionalapp

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.IOException
import java.io.InputStream
import java.net.URL


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val back = findViewById<Button>(R.id.bt_back)

        back.setOnClickListener {
            // Do some work here
            Log.e("Tag", "Clicked")
            Messanger.sendData()
            finish()
        }

        val myImage = findViewById<ImageView>(R.id.iv_imageFromFlutter)

        //From Local
//        val image = Messanger.byteArrayToBitmap(Messanger.imagePath)
//        myImage.setImageBitmap(image)

        //From URL
//        Thread {
//            try {
//                //download the drawable
//                val drawable = Drawable.createFromStream(
//                    URL(Messanger.imagePath).content as InputStream,
//                    "ImageFile"
//                )
//                //edit the view in the UI thread
//                myImage.post(Runnable { myImage.setImageDrawable(drawable) })
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }.start()

        //Using glide
        Glide.with(this).load(Messanger.imagePath).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(myImage);

//        Messanger.flutterChannel.invokeMethod("fromAndroid2", "Value2 from Android")
    }
}