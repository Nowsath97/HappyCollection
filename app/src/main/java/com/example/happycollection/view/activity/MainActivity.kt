package com.example.happycollection.view.activity

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.happycollection.*
import com.example.happycollection.databinding.ActivityMainBinding
import com.example.happycollection.repository.MainRepository
import com.example.happycollection.view.adapter.MainAdapter
import com.example.happycollection.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mRetrofitService = RetrofitService.getInstance()
    lateinit var mMainViewModel: MainViewModel

    private lateinit var mActivityMainBinding: ActivityMainBinding


    private lateinit var mMainAdapter: MainAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActivityMainBinding.root)

        initProgressBar()
        initRecyclerview()
        initViewModel()
        loadData()
        mActivityMainBinding.swipeRefreshLayout.setOnRefreshListener {
            mActivityMainBinding.swipeRefreshLayout.isRefreshing = true
            initViewModel()
            loadData()
        }
    }

    private fun initProgressBar() {
        progressBar = ProgressBar(this)

        progressBar.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        mActivityMainBinding.root.addView(progressBar)
    }

    private fun loadData() {
        if (NetworkUtil().isInternetAvailable(this)) {
            progressBar.visibility = View.VISIBLE
            mMainViewModel.getAllList()
        } else {
            mActivityMainBinding.swipeRefreshLayout.isRefreshing = false
            Utils().showSnackbar(mActivityMainBinding.root, getString(R.string.no_internet))
        }
    }

    private fun initRecyclerview() {
        mMainAdapter = MainAdapter()
        mActivityMainBinding.recyclerview.adapter = mMainAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        mMainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(MainRepository(mRetrofitService))
        ).get(
            MainViewModel::class.java
        )

        mMainViewModel.allList.observe(this, Observer {
            mActivityMainBinding.swipeRefreshLayout.isRefreshing = false
            mMainAdapter.setList(it)
            mMainAdapter.notifyDataSetChanged()
            progressBar.visibility = View.GONE
            Utils().dismissSnackbar()
            Log.d("MainActivity", "create: $it")
        })

        mMainViewModel.errorMessage.observe(this, Observer {
            mActivityMainBinding.swipeRefreshLayout.isRefreshing = false
            progressBar.visibility = View.GONE
            Utils().showSnackbar(mActivityMainBinding.root, it)
            Log.d("MainActivity", it)
        })
    }
}