package com.example.happycollection.model

data class Product(val title: String,
                   val price: Double,
                   val description: String,
                   val category: String,
                   val image: String,
                   val rating: Rating
                   )