package com.example.happycollection.view.adapter

import com.example.happycollection.model.Product
import com.example.happycollection.model.Rating
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import java.lang.Exception

class MainAdapterTest {
    private val mMainAdapter: MainAdapter = MainAdapter()
    private lateinit var product1: Product
    private lateinit var product2: Product

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val productList: ArrayList<Product> = ArrayList()
        product1 = Product(
            "product1",
            1010.0,
            "description1",
            "catogory1",
            "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            Rating(4.0F, 120)
        )
        product2 = Product(
            "product2",
            2200.0,
            "description2",
            "catogory2",
            "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg",
            Rating(5.0F, 150)
        )
        productList.add(product1)
        productList.add(product2)

        mMainAdapter.setList(productList)
    }

    @Test
    fun testProduct() {
        assertNotEquals(0, mMainAdapter.getItemId(2))
    }

    @Test
    fun testProductCount() {
        assertEquals(2, mMainAdapter.itemCount)
    }

    @Test
    fun testAdapterView() {
        val view = mMainAdapter.getItemViewType(0)
        assertEquals(0, view)
    }

}