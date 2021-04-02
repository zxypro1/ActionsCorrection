package com.example.actionscorrection.ui.edit

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.actionscorrection.R


class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val picture = findViewById<ImageView>(R.id.Picture)
        var select = findViewById<Button>(R.id.button6)
        //RxPictureTool.getCameraIntent()
        picture.setOnClickListener{
            val m2: Int = 1
            val intent = Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(intent,m2)
        }
        select.setOnClickListener {
            
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 66 && resultCode == RESULT_OK && null != data) {
            val selectedVideo: Uri? = data.data
            val filePathColumn = arrayOf(MediaStore.Video.Media.DATA)
        }
        if (resultCode != RESULT_OK) {
            return
        }
    }


}