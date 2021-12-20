package com.example.happycollection.repository

import com.example.happycollection.retrofit.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun getAllList() = retrofitService.getAllList()
}