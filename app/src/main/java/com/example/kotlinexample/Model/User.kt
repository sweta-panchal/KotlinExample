package com.example.kotlinexample.Model

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

class User(override val email : String, override val password : String) : IUser{

    override fun isValidate(): Int {

        if (TextUtils.isEmpty(email))
             return 0 // 0 error code is email empty
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return 1
        else if (TextUtils.isEmpty(password))
            return 2
        else if (password.length <= 6)
            return 3
        else
            return -1 // -1 success code
    }

}