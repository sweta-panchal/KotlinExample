package com.example.kotlinexample.Service

import android.os.StrictMode
import android.util.Log
import com.example.kotlinexample.View.ILoginView
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class LoginApi{

    interface onLoginFinishedListener {
        fun onResultSuccess(messsage : String)
        fun onResultFail(strError: String)
    }

    fun postData(onLoginFinishedListener: onLoginFinishedListener) {

        var iloginview: ILoginView? = null

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://gorest.co.in/public-api/users")
            .build()
        client.newCall(request).execute().use { response ->
            try {
                var res = response.body!!.string()
                val json = JSONObject(res)

                Log.e("JJJJSon", json.toString())
                var code = json.getString("code")
//                var name = json.getString("password")
                Log.e("code", code)
//                Log.e("name", name)


                onLoginFinishedListener.onResultSuccess("Login Success")


            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
}
