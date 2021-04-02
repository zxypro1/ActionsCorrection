package com.example.actionscorrection.ui.register

data class RegisterFormState (val phoneNumberError: Int? = null,
                         val passwordError: Int? = null,
                         val isDataValid: Boolean = false)