package com.popo.untitledandroidproject.ui.accountCreation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.popo.untitledandroidproject.database.UserDao
import com.popo.untitledandroidproject.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

import kotlinx.coroutines.launch


class AccountCreationViewModel(
    val database: UserDao,
    application: Application
) : AndroidViewModel(application) {
    private val viewModelJob = Job()
    private val uiscope= CoroutineScope(Dispatchers.Main+viewModelJob)

    private val _user= MutableLiveData<User>()
    val user: LiveData<User>
        get()= _user

    init {
        Log.i("AcountCreationViewModel","created")
    }

    fun onValidateSignUp(){
        uiscope.launch {
            val user = user.value ?: return@launch
            if(user.email==null){
                return@launch
            }
        }

    }
}
