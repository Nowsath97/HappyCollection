package com.example.happycollection.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atilsamancioglu.artbookhilttesting.getOrAwaitValueTest
import com.example.happycollection.model.Product
import com.example.happycollection.model.Rating
import com.example.happycollection.repository.MainRepository
import com.example.happycollection.retrofit.RetrofitService
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    // Set only one, main thread
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(MainRepository(RetrofitService.getInstance()))
    }

    @Test
    fun testValidData() {
        val product1 = Product(
            "product1",
            1010.0,
            "description1",
            "catogory1",
            "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            Rating(4.0F, 120)
        )
        val productSampleList = ArrayList<Product>()
        productSampleList.add(product1)
        viewModel.allList.postValue(productSampleList)

        val productList = viewModel.allList.getOrAwaitValueTest()
        assertThat(productList).isEqualTo(productSampleList)
        assertThat(productList.size).isEqualTo(1)
    }

    @Test
    fun testInValidData() {
        val product1 = Product(
            "product1",
            1010.0,
            "description1",
            "catogory1",
            "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            Rating(4.0F, 120)
        )
        val productSampleList = ArrayList<Product>()
        productSampleList.add(product1)
        viewModel.allList.postValue(productSampleList)

        val productList = viewModel.allList.getOrAwaitValueTest()
        assertThat(productList.size).isNotEqualTo(2)
    }
}