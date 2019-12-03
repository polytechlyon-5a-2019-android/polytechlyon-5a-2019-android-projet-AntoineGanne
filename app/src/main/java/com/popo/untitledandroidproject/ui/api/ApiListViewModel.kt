package com.popo.untitledandroidproject.ui.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.popo.untitledandroidproject.service.MyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await

class ApiListViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {

        coroutineScope.launch {
            val getPropertiesDeferred = MyApi.retrofitService.getPropertiesAsync()
            try {
                val listResult = getPropertiesDeferred.await()
                _response.value = "Success: ${listResult.nhits} Mars properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ListViewModel", "destroyed")
        viewModelJob.cancel()
    }
}