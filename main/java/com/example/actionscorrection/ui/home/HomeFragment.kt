package com.example.actionscorrection.ui.home

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.actionscorrection.R
import com.example.actionscorrection.ui.CircyleBar.CircleProgressBar
import com.example.actionscorrection.ui.body.BodyActivity
import com.example.actionscorrection.ui.simul.SimulActivity
import com.example.actionscorrection.ui.uploadvideo.UploadVideoActivity
import com.example.actionscorrection.ui.work.WorkActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val binding = root.findViewById<CircleProgressBar>(R.id.circleProgressBar)


        binding.startAnim(75)
        with(binding) {
            rindColorArray = intArrayOf(
                Color.parseColor("#b8e986"),
                Color.parseColor("#24DB95")
            )
            max = 89
            startAnim(12)
        }

        val ball = root.findViewById<LinearLayout>(R.id.ThrowingBall)
        val jump = root.findViewById<LinearLayout>(R.id.Jump)

        ball.setOnClickListener{
            val intent = Intent(activity,UploadVideoActivity::class.java)
            startActivity(intent)
        }

        ball.setOnClickListener{
            val intent = Intent(activity,UploadVideoActivity::class.java)
            startActivity(intent)
        }
        

        //val body = root.findViewById<ImageView>(R.id.BodyButton)
        //val work = root.findViewById<ImageView>(R.id.WorkButton)
        //val simul = root.findViewById<ImageView>(R.id.SimulationButton)


        //body.setOnClickListener{
        //    val intent = Intent(activity,UploadVideoActivity::class.java)
        //    startActivity(intent)
        //}
        //work.setOnClickListener{
        //    val intent = Intent(activity,WorkActivity::class.java)
        //    startActivity(intent)
        //}
        //simul.setOnClickListener{
        //    val intent = Intent(activity,SimulActivity::class.java)
        //    startActivity(intent)
        //}


        return root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 66 && resultCode == AppCompatActivity.RESULT_OK && null != data) {
            val selectedVideo: Uri? = data.data
            val filePathColumn = arrayOf(MediaStore.Video.Media.DATA)
        }
        if (resultCode != AppCompatActivity.RESULT_OK) {
            return
        }
    }
}