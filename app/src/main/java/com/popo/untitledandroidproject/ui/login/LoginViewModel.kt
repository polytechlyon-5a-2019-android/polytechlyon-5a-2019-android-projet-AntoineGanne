package com.popo.untitledandroidproject.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.popo.untitledandroidproject.data.LoginRepository
import com.popo.untitledandroidproject.data.Result

import com.popo.untitledandroidproject.R
import com.popo.untitledandroidproject.database.UserDao
import com.popo.untitledandroidproject.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import utils.isPasswordValid
import utils.isUserNameValid

class LoginViewModel(
    val database: UserDao,
    application: Application,
    private val UserID: Long=0L
    ) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope= CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        Log.i("IdentityViewModel", "created")
    }

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = database.login(username, password)

        if (result != null) {
            _loginResult.value =
                LoginResult(
                    success = LoggedInUserView(
                        displayName = result.lastname as String
                    )
                )
        } else {
            _loginResult.value =
                LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value =
                LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value =
                LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value =
                LoginFormState(isDataValid = true)
        }
    }


    fun onValidateLogin(){
        uiScope.launch {
            val user = user.value ?:return@launch
            if(user.email==null || !isUserNameValid(user.email as String)){
                return@launch
            }
            if(user.password==null || isPasswordValid(user.password as String)){
                return@launch
            }

        }
    }

    private val _navigateToPersonalDataFragment = MutableLiveData<User>()

    val navigateToAccountCreationFragment: LiveData<User>
        get() = _navigateToPersonalDataFragment

    fun doneNavigating() {
        _navigateToPersonalDataFragment.value = null
    }

    fun onClickToAccountCreation(){
        uiScope.launch {
            val user=_user.value
            _navigateToPersonalDataFragment.value=user
        }
    }
}
