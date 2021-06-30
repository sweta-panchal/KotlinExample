package com.example.kotlinexample.View

import com.example.kotlinexample.PageItem

interface PageView {
    fun showProgress()
    fun hideProgress()
    fun setNewsData(pageItem: List<PageItem>)
    fun getDataFailed(strError: String)
    fun onItemClick(adapterPosition: Int)
}