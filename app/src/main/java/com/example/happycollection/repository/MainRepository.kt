package com.example.happycollection.repository

import com.example.happycollection.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
   suspend fun getAllList() = retrofitService.getAllList()
}