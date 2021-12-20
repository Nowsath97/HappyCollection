package com.example.happycollection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.happycollection.repository.MainRepository
import com.example.happycollection.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.mainRepository) as T
        } else {
        throw IllegalArgumentException("ViewModel not found")
        }
    }
}