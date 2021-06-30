package com.example.kotlinexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kotlinexample.Presenter.ILoginPresenter
import com.example.kotlinexample.Presenter.LoginPresenter
import com.example.kotlinexample.Service.LoginApi
import com.example.kotlinexample.View.ILoginView

import kotlin.math.pow

class MainActivity : AppCompatActivity(),ILoginView{

    internal lateinit var loginPresenter: ILoginPresenter

     var btn_LoginSubmit : Button? =null
     var edt_LoginEmailId : EditText? = null
     var edt_LoginPassword :EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_LoginSubmit = findViewById(R.id.btn_LoginSubmit)
        edt_LoginEmailId = findViewById(R.id.edt_LoginEmailId)
        edt_LoginPassword = findViewById(R.id.edt_LoginPassword)

        //Init
        loginPresenter = LoginPresenter(this, LoginApi())

        //Event
        btn_LoginSubmit?.setOnClickListener {

            loginPresenter.onLogin(edt_LoginEmailId?.text.toString(),edt_LoginPassword?.text.toString())
        }

    }


    override fun onLoginSuccess(message: String) {

        val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)

        Toast.makeText(this,message,Toast.LENGTH_LONG).show()

    }

    override fun onLoginError(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }




}

class Car(val name:String , val type:String, var kmRan : Int) // properties
{
    fun driveCars() // Method
    {
        println("$name car is driving")

    }

    fun applyBreaks()
    {
        println("Applied Breaks")
    }
}

fun main() {


    var aa = 10
    val bb = "Hello Kotlin"

    /* print value */
    println(bb)
    println(aa) //result = 10

    funArithmeticOperator()

    funRelationalOperator()

    funIncrementOperator()

    var funname: (a: Double, b: Double) -> Double = ::addition // add function in variable
    println(funname(1.2,1.3)) // 2.5

    funname = :: power
    println(funname(2.0,3.0))

    funArray()

    val mustang = Car("mustang","petrol",100)
    val beetle = Car("beetle","diesle",200)

    println(mustang.name)
    println(beetle.type)

    println(mustang.driveCars())
    println(beetle.applyBreaks())

    funRange()

    funWhen()

}

fun funArithmeticOperator() {

    var i = 13
    var j = 2

    println(i + j) //addition = 15
    println(i - j) //minus = 12
    println(i * j) // multiplication = 26
    println(i.toFloat() / j) // division = 6.5
    println(i % j) // Modulus = 1

}

fun funRelationalOperator(){
    var i = 13
    var j = 2

    println(i < j) // 13 < 2 = false
    println(i > j) // 13 > 2 = true
    println(i <= j) // 13 <= 2 = false
    println(i >= j) // 13 >= 2 = true
    println(i == j) // 13 == 2 = false
    println(i != j) // 13 != 2 = true

}

fun funIncrementOperator(){

    var i = 10

    println(i++) // Post Incremnt = 10
    println(i)  // 11

    println(++i) // Pre Increment = 12

    println(i++ + ++i) // 12 + 14 = 26

}

fun addition(a : Double, b : Double): Double {
    return a + b
}

fun power(a : Double , b :Double) : Double {
    return a.pow(b)
}

fun funArray()
{
    var arr = arrayOf("A","B","C")

    for(i in arr)
    {
        println(i) // print value
    }

    for ((i,e) in arr.withIndex())
    {
        println("$i - $e") // print index with element
    }

    println(arr[0]) // get value of perticular index
    println(arr.get(1))

    arr.set(2,"Hello") // set value "Hello" on 3rd index
    println(arr[2])
}

fun funRange() {

    var number = 5
    var result = number in 1..5
    var result1 = number in 1 until 6
    println(result)
    println(result1)

}

fun funWhen(){

    val animal = "Dog"

    when(animal){

        "Dog" -> println("Animal is Dog")
        "Horse" -> println("Animal is Horse")
        "Cat" -> println("Animal is Cat")
        else -> println("Animal is Not Found")
    }
}
