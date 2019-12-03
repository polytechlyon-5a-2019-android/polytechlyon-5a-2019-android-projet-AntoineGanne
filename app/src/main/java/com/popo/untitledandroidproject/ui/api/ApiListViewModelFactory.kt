package com.popo.untitledandroidproject.ui.api

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ApiListViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiListViewModel::class.java)) {
            return ApiListViewModel() as T
        }
            throw IllegalArgumentException("Unknown ViewModel class")
    }

}