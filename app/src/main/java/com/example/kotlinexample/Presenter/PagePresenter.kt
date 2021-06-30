package com.example.kotlinexample.Presenter

import com.example.kotlinexample.Service.ApiService
import com.example.kotlinexample.PageItem
import com.example.kotlinexample.View.PageView

class PagePresenter(private var pageview: PageView?, private val apiService: ApiService)
    : ApiService.OnFinishedListener {

    fun getNewsData() {
        pageview?.showProgress()
        apiService.fetchjson(this)
    }

    fun onDestroy() {
        pageview = null
    }

    override fun onResultSuccess(pageItem: List<PageItem>) {
        pageview?.hideProgress()
        pageview?.setNewsData(pageItem)
    }

    override fun onResultFail(strError: String) {
        pageview?.hideProgress()
        pageview?.getDataFailed(strError)
    }

    fun onItemClick(adapterPosition: Int) {
        pageview?.onItemClick(adapterPosition)
    }
}