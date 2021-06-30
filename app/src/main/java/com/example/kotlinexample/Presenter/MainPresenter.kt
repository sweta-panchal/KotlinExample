package com.example.kotlinexample.Presenter

import com.example.kotlinexample.Model.DataModel
import com.example.kotlinexample.R
import com.example.kotlinexample.interfaces.MyInterface

class MainPresenter(view : MyInterface.DataView) : MyInterface.Presenter {

    private var view : MyInterface.DataView = view
    private var model : DataModel = DataModel("Bangkok Post","this is bangkok post", R.mipmap.ic_launcher)


    init {
        model.addValue()
    }
    override fun getData() {
        view.getDataFromPresenter(model.getValue())
    }
}