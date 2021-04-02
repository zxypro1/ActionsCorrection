package com.example.actionscorrection.data


import android.os.SystemClock.sleep
import com.example.actionscorrection.data.model.LoggedInUser
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.internal.wait
import org.json.JSONObject
import java.io.IOException
import kotlin.concurrent.thread
import kotlin.properties.Delegates
import com.example.actionscorrection.ui.login.LoginActivity

var result = -1

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */


class LoginDataSource {




    fun login(username: String, password: String): Result<LoggedInUser> {

        try {
            val mRu = MyRunnable(username, password)
            val thread = Thread(mRu)
            thread.isDaemon = true
            thread.start()
            println(result)

        } catch (e: Throwable) {
            result = -1
            return Result.Error(IOException("登陆失败", e))
        }

        //sleep(2000)

        var i = 0
        while(result == -1){
            sleep(100)
            i+=100
            if (i==6000)
                break
        }
        println(result)
        if (result==1){
            val fakeUser = LoggedInUser(
                java.util.UUID.randomUUID().toString(),
                "Jane Doe",
                true,
                7,
                12,
                4,
                678,
                67,
                34,
                870,
                13,
                23.4,
                false
            )
            result = -1
            println(result)
            return Result.Success(fakeUser)// TODO: handle loggedInUser authentication
        }else{
            result = -1
            println(result)
            return Result.Error(IOException("账户或密码错误"))
        }


    }

    fun logout() {
        // TODO: revoke authentication
    }

    inner class MyRunnable(username: String, password: String) : Runnable{

        val usr = username
        val pas = password


        override fun run(){
            try{
                val url = "https://goldenpigeon.top:8080/app/user/login?userName=$usr&password=$pas"
                val client = OkHttpClient()

                val requestBody = RequestBody.create("application/json".toMediaType(), "")

                val request = Request.Builder()
                request.url(url)
                    .post(requestBody)

                client.newCall(request.build()).enqueue(object: Callback{
                    override fun onFailure(call: Call, e: IOException) {
                        result = 0
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val json = response.body?.string()

                        println(json)
                        val jsonO = JSONObject(json)
                        println(jsonO)
                        val res = jsonO.getInt("code")
                        println(res)
                        if (res==200){
                            result = 1
                        } else{
                            result = 0
                        }
                    }
                })

                //val response = client.newCall(request.build()).execute()

                //val json = response.body?.string()

                //println(json)
                //val jsonO = JSONObject(json)
                //println(jsonO)
                //val res = jsonO.getInt("code")
                //println(res)
                //result = if (res==200){
                //    1
                //} else{
                //    0
                //}
            }catch(e:Throwable){
                result = 0
            }
        }
    }

}
