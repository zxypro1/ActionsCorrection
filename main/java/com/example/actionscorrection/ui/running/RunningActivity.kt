package com.example.actionscorrection.ui.running

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapView
import com.baidu.mapapi.map.MyLocationData
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.utils.DistanceUtil
import com.example.actionscorrection.R
import com.example.actionscorrection.ui.runreport.RunReportActivity
import kotlinx.coroutines.delay


class RunningActivity : AppCompatActivity() {
    var mLocationClient: LocationClient? = null
    var positionText: TextView? = null
    var mapView: MapView? = null
    var baiduMap: BaiduMap? = null
    var totaltime: TextView? = null
    var isFirstLocate = true
    private val LOCATION_REQUEST_CODE = 103
    var totaldistance: Double = 0.0
    var totalDistance: TextView? = null
    var Speed : TextView? = null
    var onstart = 1
    var seconds = 0
    var minutes = 0
    var hours = 0
    var mHandler: Handler = Handler()
    private lateinit var runningViewModel: RunningViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_running)

        SDKInitializer.initialize(applicationContext)

        mapView = findViewById<MapView>(R.id.mapview)
        totalDistance = findViewById<TextView>(R.id.totalDistance)
        totaltime = findViewById<TextView>(R.id.TotalTime)
        Speed = findViewById<TextView>(R.id.Speed)
        val stop = findViewById<Button>(R.id.StopButton)

        baiduMap?.isMyLocationEnabled = true

        mLocationClient = LocationClient(applicationContext)
        mLocationClient?.registerLocationListener(MyLocationListener())


        //val mLocationConfiguration= MyLocationConfiguration(
        //    MyLocationConfiguration.LocationMode.COMPASS,
        //    true,
        //    null
        //)

        val option: LocationClientOption? = null
        option?.isOpenGps = true
        option?.setCoorType("bd09ll") // 设置坐标类型
        option?.setScanSpan(1000)
        //baiduMap?.setMyLocationConfiguration(mLocationConfiguration)
        mLocationClient!!.locOption = option
        val myLocationListener = MyLocationListener()

        mLocationClient!!.registerLocationListener(myLocationListener)
        mLocationClient!!.start()
        //mLocationClient!!.requestLocation()

        stop.setOnClickListener{
            stopAll()
            val intent = Intent(this,RunReportActivity::class.java)
            runningViewModel.setData(seconds+60*minutes+60*60*hours,
                133,
                (seconds+60*minutes+60*60*hours)/totaldistance,
                69)
            startActivity(intent)
            finish()
        }
    }


    override fun onResume() {
        mapView?.onResume()
        super.onResume()
    }

    override fun onPause() {
        mapView?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mLocationClient!!.stop()
        baiduMap?.isMyLocationEnabled = false
        mapView?.onDestroy()
        mapView = null
        super.onDestroy()
    }

    inner class MyLocationListener : BDAbstractLocationListener() {
        var lastPoint: LatLng? = null
        override fun onReceiveLocation(p0: BDLocation?) {
            if (p0 == null || mapView == null) {
                return
            }
            val locData = MyLocationData.Builder()
                .accuracy(p0.radius) // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(p0.direction).latitude(p0.latitude)
                .longitude(p0.longitude).build()
            baiduMap?.setMyLocationData(locData)
            if (onstart == 1){
                val thisPoint = LatLng(p0.latitude, p0.longitude)
                totaldistance += DistanceUtil.getDistance(thisPoint, lastPoint)

                lastPoint = thisPoint
                totalDistance?.text = "$totaldistance"
                Speed?.text = p0.speed.toString()
            }
        }
    }

    inner class timeRunnable: Runnable {
        override fun run() {
            if (onstart == 1){
                seconds++
                if (seconds >= 60){
                    minutes ++
                    seconds = 0
                }

                if (minutes >= 60){
                    hours ++
                    minutes = 0
                }
                totaltime?.text = "$hours:$minutes:$seconds"
                mHandler.postDelayed(this,1_000L)
            }
        }
    }

    fun stopAll(){
        onstart = 0
    }
}

