package com.example.actionscorrection.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.actionscorrection.R
import com.example.actionscorrection.ui.devices.DevicesActivity
import com.example.actionscorrection.ui.rate.RateActivity
import com.example.actionscorrection.ui.report.ReportActivity

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val device: ImageButton = root.findViewById(R.id.ConnectionButton)


        device.setOnClickListener {
            val intent = Intent(activity, DevicesActivity::class.java)
            startActivity(intent)
        }

        return root
    }
}