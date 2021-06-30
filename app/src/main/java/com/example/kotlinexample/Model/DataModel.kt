package com.example.kotlinexample.Model

import com.example.kotlinexample.interfaces.MyInterface

class DataModel(var title:String, var des: String, var image : Int) : MyInterface.ModelInterface{

    var list = ArrayList<DataModel>()

    override fun addValue() {
        list.add(DataModel(title,des,image))
    }

    override fun getValue(): ArrayList<DataModel> {
      return list
    }
}