package com.example.actionscorrection.ui.runreport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.actionscorrection.R
import com.example.actionscorrection.ui.running.RunningViewModel

class RunReportActivity : AppCompatActivity() {
    private lateinit var runningViewModel: RunningViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_report)
    }
}