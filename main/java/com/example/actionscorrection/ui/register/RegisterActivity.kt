package com.example.actionscorrection.ui.register

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.actionscorrection.MainActivity
import com.example.actionscorrection.R
import com.example.actionscorrection.ui.login.LoggedInUserView
import com.example.actionscorrection.ui.login.LoginActivity
import com.example.actionscorrection.ui.login.afterTextChanged
import okhttp3.*
import org.json.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerViewModel : RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerViewModel = ViewModelProvider(this, RegisterViewModelFactory()).get(RegisterViewModel::class.java)
        val phoneNumber: EditText? = findViewById(R.id.editTextPhone)
        val password = findViewById<EditText>(R.id.editTextTextPassword)
        val register = findViewById<Button>(R.id.Register)
        val loading = findViewById<ProgressBar>(R.id.loading2)

        registerViewModel.registerFormState.observe(this@RegisterActivity, Observer {
            val registerState = it ?: return@Observer

            // disable login button unless both username / password is valid
            register.isEnabled = registerState.isDataValid

            if (registerState.phoneNumberError != null) {
                if (phoneNumber != null) {
                    phoneNumber.error = getString(registerState.phoneNumberError)
                }
            }
            if (registerState.passwordError != null) {
                password.error = getString(registerState.passwordError)
            }
        })

        phoneNumber?.afterTextChanged {
            registerViewModel.registerDataChanged(
                phoneNumber.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                if (phoneNumber != null) {
                    registerViewModel.registerDataChanged(
                        phoneNumber.text.toString(),
                        password.text.toString()
                    )
                }
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        if (phoneNumber != null) {
                            registerViewModel.register(
                                phoneNumber.text.toString(),
                                password.text.toString()
                            )
                        }
                }
                false
            }
        }

        register.setOnClickListener {
            loading.visibility = View.VISIBLE
            if (phoneNumber != null) {
                registerViewModel.register(phoneNumber.text.toString(), password.text.toString())
            }
            else{
                Toast.makeText(
                    applicationContext,
                    "手机号不能为空！",
                    Toast.LENGTH_LONG
                )
            }
        }

        registerViewModel.registerResult.observe(this@RegisterActivity, Observer {
            val registerResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (registerResult.error != null) {
                showRegisterFailed()
            }
            if (registerResult.success != null) {
                updateUi()
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            //finish()
        })

    }
    private fun updateUi() {

        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "注册成功！",
            Toast.LENGTH_LONG
        ).show()
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showRegisterFailed() {
        Toast.makeText(applicationContext, "注册失败！", Toast.LENGTH_SHORT).show()
    }

}