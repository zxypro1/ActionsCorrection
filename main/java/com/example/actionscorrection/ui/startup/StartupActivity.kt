package com.example.actionscorrection.ui.startup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import com.example.actionscorrection.MainActivity
import com.example.actionscorrection.R
import com.example.actionscorrection.ui.login.LoginActivity
import java.util.*

class StartupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)
        var mTime = 5
        var mHandler: Handler = Handler()
        val skipbutton = findViewById<Button>(R.id.Skip)
        var onclick = false



        class mRunnable : Runnable {
            private var isStop=false
            override fun run() {
                //TODO
                if (!isStop){
                    if (mTime == 1||onclick) {
                        val intent = Intent(this@StartupActivity, LoginActivity::class.java)
                        startActivity(intent)
                        onclick = false
                        this@mRunnable.isStopp()
                    }
                    mTime--
                    skipbutton.text = "跳过(${mTime})"
                    mHandler.postDelayed(this,1_000L)
                }
            }
            fun isStopp() {
                isStop = true
                finish()
            }
        }


        skipbutton.setOnClickListener{
            onclick = true
        }

        mHandler.post(mRunnable())
    }
}