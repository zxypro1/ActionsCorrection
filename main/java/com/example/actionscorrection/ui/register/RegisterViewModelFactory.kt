package com.example.actionscorrection.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.actionscorrection.data.LoginDataSource
import com.example.actionscorrection.data.LoginRepository
import com.example.actionscorrection.data.RegisterDataSource
import com.example.actionscorrection.data.RegisterRepository
import com.example.actionscorrection.ui.login.LoginViewModel

class RegisterViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(
                registerRepository = RegisterRepository(
                    dataSource = RegisterDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}