package com.popo.untitledandroidproject.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel

import com.popo.untitledandroidproject.R
import com.popo.untitledandroidproject.database.UserDao
import com.popo.untitledandroidproject.model.User
import kotlinx.coroutines.*

import utils.isPasswordValid
import utils.isMailValid
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

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
        uiScope.launch {
            // can be launched in a separate asynchronous job
//            val result = awaitAll(database.login(username, password))
//            var user=result.get(0)


            // had an error about database.login method
            // I use these 3 lines to help not crash the app if the query fails.
            val callable = Callable {database.login(username,password)}
            val future = Executors.newSingleThreadExecutor().submit(callable)
            val user = future[10000, TimeUnit.MILLISECONDS]

            if (user != null) {
                //TODO: navigation vers fragment apiListe
                _loginResult.value =
                    LoginResult(
                        success = LoggedInUserView(
                            displayName = user.lastname as String
                        )
                    )
            } else {
                _loginResult.value =
                    LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isMailValid(username)) {
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


    fun onValidateLogin() {
        uiScope.launch {
            val user = user.value ?:return@launch
            if(user.email==null || !isMailValid(user.email as String)){
                return@launch
            }
            if(user.password==null || isPasswordValid(user.password as String)){
                return@launch
            }
//            view.findNavController().navigate()
        }
    }


}
