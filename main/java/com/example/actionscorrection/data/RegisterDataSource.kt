package com.example.actionscorrection.data


import android.os.SystemClock.sleep
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException



class RegisterRunnable(username: String, password: String) : Runnable{

    val usr = username
    val pas = password


    override fun run(){
        try{
            val url = "https://goldenpigeon.top:8080/app/user/register?userName=$usr&password=$pas"
            val client = OkHttpClient()

            val requestBody = RequestBody.create("application/json".toMediaType(), "")

            val request = Request.Builder()
            request.url(url)
                .post(requestBody)

            val response = client.newCall(request.build()).execute()

            val json = response.body?.string()

            println(json)
            val jsonO = JSONObject(json)
            println(jsonO)
            val res = jsonO.getInt("code")
            println(res)
            result = if (res==200){
                1
            } else{
                0
            }
        }catch(e:Throwable){
            result = 0
        }
    }
}

class RegisterDataSource {
    fun register(phoneNumber: String, password: String): Int{
        try{
            val mRu = RegisterRunnable(phoneNumber, password)
            val thread = Thread(mRu)
            thread.isDaemon = true
            thread.start()
            println(result)
        }catch (e: Exception){
            result = -1
            return 0
        }
        var i = 0
        while(result == -1){
            sleep(100)
            i+=100
            if (i==6000)
                break
        }
        //sleep(2000)
        if (result == 1){
            result = -1
            return 1
        }

        else{
            result = -1
            return 0
        }

    }

    fun run(phoneNumber: String, password: String): Int {

        var result = 0

        val url = "https://goldenpigeon.top:8080/app/user/register?userName=$phoneNumber&password=$password"
        val client = OkHttpClient()

        val requestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), "")

        val request = Request.Builder()
        request.url(url)
            .post(requestBody)

        client.newCall(request.build()).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                println("Error")
                result=0
            }
            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                println(json)
                val jsonO = JSONObject(json)
                val res = jsonO.getInt("code")
                result = if (res==20007){
                    0
                } else{
                    1
                }
            }
        })
        return result
    }
}