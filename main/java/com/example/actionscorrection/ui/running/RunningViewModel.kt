package com.example.actionscorrection.ui.running

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RunningViewModel: ViewModel() {
    private val _runningReport = MutableLiveData<RunningData>()
    val runningReport: LiveData<RunningData> = _runningReport

    fun setData(time: Int, heat: Int, speed: Double, step: Int) {
        _runningReport.value = RunningData(
            time,
            heat,
            speed,
            step
        )
    }
}