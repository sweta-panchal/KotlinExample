package com.example.kotlinexample.Presenter

import com.example.kotlinexample.Model.User
import com.example.kotlinexample.Service.LoginApi
import com.example.kotlinexample.View.ILoginView

class LoginPresenter (var iLoginView: ILoginView,private val loginApi: LoginApi) : ILoginPresenter,LoginApi.onLoginFinishedListener{

    override fun onLogin(email: String, password: String) {

        val user = User(email,password)
        val logincode = user.isValidate()

        if (logincode == 0)
            iLoginView.onLoginError("Please Enter EmailId")
        else if(logincode == 1)
            iLoginView.onLoginError("Incorrect EmailId")
        else if(logincode == 2)
            iLoginView.onLoginError("Please Enter Password")
        else if(logincode == 3)
            iLoginView.onLoginError("Password must be greater than 6")
        else
            loginApi.postData(this)

    }

    override fun onResultSuccess(messsage: String) {
        iLoginView.onLoginSuccess(messsage)
    }

    override fun onResultFail(strError: String) {

        iLoginView.onLoginError(strError)

    }
}