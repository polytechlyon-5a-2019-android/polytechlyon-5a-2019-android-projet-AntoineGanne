package com.popo.untitledandroidproject.ui.accountCreation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.popo.untitledandroidproject.database.UserDao
import java.lang.IllegalArgumentException

class AccountCreationViewModelFactory(
    private val dataSource: UserDao,
    private val application: Application
) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun<T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AccountCreationViewModel::class.java)){
            return AccountCreationViewModel(
                dataSource,
                application
            )as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}