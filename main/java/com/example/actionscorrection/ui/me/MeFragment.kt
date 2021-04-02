package com.example.actionscorrection.ui.me

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.example.actionscorrection.R
import com.example.actionscorrection.data.model.LoggedInUser
import com.example.actionscorrection.ui.edit.EditActivity
import com.example.actionscorrection.ui.feedback.FeedbackActivity
import com.example.actionscorrection.ui.history.HistoryActivity
import com.example.actionscorrection.ui.login.LoggedInUserView
import com.example.actionscorrection.ui.login.LoginViewModel
import com.example.actionscorrection.ui.rate.RateActivity
import com.example.actionscorrection.ui.report.ReportActivity
import com.example.actionscorrection.ui.setaim.SetAimActivity
import com.makeramen.roundedimageview.RoundedImageView
import com.tamsiree.rxkit.RxImageTool


class MeFragment : Fragment() {


    private lateinit var viewModel: MeViewModel
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(MeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_me, container, false)
        val mesetting = root.findViewById<Button>(R.id.MeSetting)
        val report = root.findViewById<ConstraintLayout>(R.id.Report)
        val head = root.findViewById<ImageView>(R.id.head2)
        val feedback = root.findViewById<LinearLayout>(R.id.FeedBack)
        val history = root.findViewById<ConstraintLayout>(R.id.History)
        val changeaim = root.findViewById<LinearLayout>(R.id.SignInAim)
        val name = root.findViewById<TextView>(R.id.name)

        fun updateUI(model: LoggedInUserView){
            name.text = model.displayName
            
        }

        activity?.let { it ->
            loginViewModel.loginResult.observe(it, Observer {
                val loginResult = it ?: return@Observer

                if (loginResult.success != null) {
                    updateUI(loginResult.success)
                }

                //Complete and destroy login activity once successful
                //finish()f
            })
        }





        //head.setImageBitmap()



        changeaim.setOnClickListener {
            val intent = Intent(activity, SetAimActivity::class.java)
            startActivity(intent)
        }

        mesetting.setOnClickListener {
            val intent = Intent(activity, EditActivity::class.java)
            startActivity(intent)
        }

        feedback.setOnClickListener {
            val intent = Intent(activity, FeedbackActivity::class.java)
            startActivity(intent)
        }


        report.setOnClickListener {
            val intent = Intent(activity, ReportActivity::class.java)
            startActivity(intent)
        }

        history.setOnClickListener {
            val intent = Intent(activity, HistoryActivity::class.java)
            startActivity(intent)
        }


        return root
    }




}