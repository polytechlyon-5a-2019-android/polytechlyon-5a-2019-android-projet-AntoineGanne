package com.popo.untitledandroidproject.ui.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.popo.untitledandroidproject.database.UserDao
import java.lang.IllegalArgumentException

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory (
    private val dataSource: UserDao,
    private val application: Application,
    private val userID: Long = 0L // userID
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                dataSource,
                application,
                userID
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
