package com.example.kotlinexample.interfaces

import com.example.kotlinexample.Model.DataModel

interface MyInterface{

    interface DataView{
        fun getDataFromPresenter(value : ArrayList<DataModel>)

    }

    interface Presenter {

        fun getData()

    }



    interface ModelInterface{

        fun addValue()
        fun getValue() : ArrayList<DataModel>

    }

}