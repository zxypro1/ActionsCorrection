package com.example.actionscorrection

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tamsiree.rxkit.RxTool


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        RxTool.init(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //val appBarConfiguration = AppBarConfiguration(setOf(
            //R.id.navigation_home, R.id.navigation_notifications, R.id.navigation_me))
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        iniPermission()

    }



    private fun iniPermission() {
        val permissionList: MutableList<String> = ArrayList()
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE)
        }
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!permissionList.isEmpty()) {
            val permissions = permissionList.toTypedArray()
            ActivityCompat.requestPermissions(this, permissions, 1)
        }
    }

    public class DemoApplication: Application() {
        override fun onCreate() {
            super.onCreate()
            //在使用SDK各组件之前初始化context信息，传入ApplicationContext
            SDKInitializer.initialize(this)
            //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
            //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
            SDKInitializer.setCoordType(CoordType.BD09LL)
        }
    }


}