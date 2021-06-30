package com.example.kotlinexample.Model

interface IUser {

    val email : String
    val password : String
    fun isValidate() : Int
}