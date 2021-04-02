package com.example.actionscorrection.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.actionscorrection.R
import com.example.actionscorrection.data.RegisterRepository
import com.example.actionscorrection.ui.login.LoginResult

class RegisterViewModel(private val registerRepository: RegisterRepository): ViewModel() {

    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult


    fun register(phoneNumber: String, password: String) {
        val result = registerRepository.register(phoneNumber, password)
        if (result == 1){
            _registerResult.value = RegisterResult(success = R.string.Register_Success)
        }else{
            _registerResult.value = RegisterResult(error = R.string.Register_failed)
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun registerDataChanged(phoneNumber: String, password: String) {
        if (!isPhoneNumberValid(phoneNumber)) {
            _registerForm.value = RegisterFormState(phoneNumberError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else {
            _registerForm.value = RegisterFormState(isDataValid = true)
        }
    }

    private fun isPhoneNumberValid(phoneNumber: String): Boolean {
        return phoneNumber.length == 11
    }

}