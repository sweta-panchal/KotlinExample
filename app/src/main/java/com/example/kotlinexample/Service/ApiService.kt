package com.example.kotlinexample.Service

import android.os.StrictMode
import android.util.Log
import com.example.kotlinexample.PageItem
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class ApiService {

    interface OnFinishedListener {
        fun onResultSuccess(pageview: List<PageItem>)
        fun onResultFail(strError: String)
    }

    fun fetchjson(onFinishedListener: OnFinishedListener) {

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://reqres.in/api/users?page=2")
            .build()
        client.newCall(request).execute().use { response ->
            try {
                var res = response.body!!.string()
                val json = JSONObject(res)
                var data = json.getJSONArray("data")
                val arraylist : ArrayList<PageItem> = ArrayList()
                for (i in 0..data.length()-1){
                    var jsondata : JSONObject = data.getJSONObject(i)

                    Log.e("jsondata",jsondata.toString())
                    var model : PageItem = PageItem(jsondata.getInt("id"), jsondata.getString("email"),
                        jsondata.getString("first_name"), jsondata.getString("avatar"))
                    arraylist.add(model)
                }

                onFinishedListener.onResultSuccess(arraylist)


            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}