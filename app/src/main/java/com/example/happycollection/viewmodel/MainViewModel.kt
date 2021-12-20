package com.example.happycollection.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.happycollection.model.Product
import com.example.happycollection.repository.MainRepository
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val allList = MutableLiveData<List<Product>>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    private fun onError(s: String) {
        errorMessage.postValue(s)
    }

    fun getAllList() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    allList.postValue(response.body())
                } else {
                    onError(response.message())
                }
            }
        }
    }
}