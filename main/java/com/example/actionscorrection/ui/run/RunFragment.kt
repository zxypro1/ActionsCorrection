package com.example.actionscorrection.ui.run

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.baidu.location.LocationClient
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapView
import com.example.actionscorrection.R
import com.example.actionscorrection.ui.running.RunningActivity
import com.makeramen.roundedimageview.RoundedImageView


class RunFragment : Fragment() {


    var mMapView: MapView? = null

    companion object {
        fun newInstance() = RunFragment()
    }

    private lateinit var viewModel: RunViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.run_fragment, container, false)
        mMapView = root.findViewById(R.id.mapview)

        val running = root.findViewById<RoundedImageView>(R.id.head)

        running.setOnClickListener {
            val intent = Intent(activity, RunningActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RunViewModel::class.java)
        // TODO: Use the ViewModel
    }
    @Override
    override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView?.onResume()
    }
    @Override
    override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView?.onPause()
    }
    @Override
    override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView?.onDestroy()
    }
}