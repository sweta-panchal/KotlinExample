package com.example.kotlinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinexample.Presenter.PagePresenter
import com.example.kotlinexample.Service.ApiService
import com.example.kotlinexample.View.PageView
import com.example.kotlinexample.adapter.MyAdapter

class SecondActivity : AppCompatActivity(), PageView{

    private lateinit var pagePresenter: PagePresenter

    var recyclerview : RecyclerView? = null
    var progressBar : ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        recyclerview = findViewById(R.id.recyclerview)
        progressBar = findViewById(R.id.progressBar)

        pagePresenter = PagePresenter(this, ApiService())
        progressBar?.visibility = View.GONE
        recyclerview?.setHasFixedSize(true)


    }


    override fun onResume() {
        super.onResume()
        pagePresenter.getNewsData()
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

    override fun setNewsData(pageItem: List<PageItem>) {
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.adapter = MyAdapter(pageItem) {
            pagePresenter.onItemClick(it)
        }
    }

    override fun getDataFailed(strError: String) {
        showToast(this, strError)
    }

    override fun onItemClick(adapterPosition: Int) {
        showToast(this, "You clicked $adapterPosition")
    }

    override fun onDestroy() {
        pagePresenter.onDestroy()
        super.onDestroy()
    }



}