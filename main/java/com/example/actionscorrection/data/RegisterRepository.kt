package com.example.actionscorrection.data

class RegisterRepository (val dataSource: RegisterDataSource){

    fun register(phoneNumber: String, password: String): Int{
        return dataSource.register(phoneNumber, password)
    }


}