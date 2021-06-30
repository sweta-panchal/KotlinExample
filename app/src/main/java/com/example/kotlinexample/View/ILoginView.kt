package com.example.kotlinexample.View

import com.example.kotlinexample.PageItem

interface ILoginView {
    fun onLoginSuccess(message : String)
    fun onLoginError(message: String)

}