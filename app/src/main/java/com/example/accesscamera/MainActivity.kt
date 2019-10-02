package com.example.accesscamera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val camera_request_code=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraButton.setOnClickListener {
            val callCameraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (callCameraIntent.resolveActivity(packageManager) != null){
                startActivityForResult(callCameraIntent,camera_request_code)
            }
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            camera_request_code->{
                if(resultCode == Activity.RESULT_OK && data !=null){
                    imageView.setImageBitmap(data.extras.get("data") as Bitmap)
                }
            }
            else -> {
                Toast.makeText(this,"unrecognizedrequest code",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
