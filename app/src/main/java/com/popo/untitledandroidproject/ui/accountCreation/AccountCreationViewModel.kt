package com.popo.untitledandroidproject.ui.accountCreation

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.popo.untitledandroidproject.R
import com.popo.untitledandroidproject.database.UserDao
import com.popo.untitledandroidproject.model.User
import com.popo.untitledandroidproject.ui.login.LoginFormState
import kotlinx.coroutines.*
import utils.isMailValid
import utils.isPasswordValid


class AccountCreationViewModel(
    val database: UserDao,
    application: Application,
    private val userID: Long=0L
) : AndroidViewModel(application) {
    private val viewModelJob = Job()
    private val uiScope= CoroutineScope(Dispatchers.Main+viewModelJob)

    private val _user= MutableLiveData<User>()
    val user: LiveData<User>
        get()= _user



    val categoryIdItemPosition = MutableLiveData<Int>()
    var categoryIdValue
        get() =
            categoryIdItemPosition.value?.let {
                categoryList?.get(it)
            }
        set(value) {
            val position = categoryList?.indexOfFirst {
                it == value
            } ?: -1
            if (position != -1) {
                categoryIdItemPosition.value = position + 1
            }
        }
    val categoryIdItem
        get() =
            categoryIdItemPosition.value?.let {
                categoryList?.get(it)
            }

    var categoryList = application.resources.getStringArray(R.array.country_list)


    fun onCountry(){
        _user.value?.country=categoryList[categoryIdItemPosition.value!!]
    }

    fun onGender(gender: String) {
        _user.value?.gender = gender
    }

    init {
        Log.i("AcountCreationViewModel","created")
        initializeUser()
    }

    private fun initializeUser() {
        uiScope.launch {
            _user.value=getUser()
        }
    }

    private suspend fun getUser(): User? {
        return withContext(Dispatchers.IO){
            var user = User()
            if(userID!=0L){
                user= database.get(userID)!!
            }
            user
        }
    }

    private suspend fun insertUser(user: User):Long {
        var id=0L
        uiScope.launch {
            id=insertDb(user)
        }
        return id
    }

    private suspend fun insertDb(user: User):Long {
        return withContext(Dispatchers.IO){
            var id=database.insert(user)
            id
        }
    }

    fun onValidateSignUp(){
        uiScope.launch {
            val user = user.value ?: return@launch
            if(user.email==null){
                return@launch
            }
        }
    }

    private val _navigateToLoginFragment = MutableLiveData<User>()

    val navigateToLoginFragment: LiveData<User>
        get() = _navigateToLoginFragment

    fun doneNavigating() {
        _navigateToLoginFragment.value = null
    }

    fun onValidate() {
        uiScope.launch {
            _user.value?.country=categoryIdValue
            println("user to insert is "+user.value)
            val user = user.value ?: return@launch
            if(user.email.isNullOrEmpty() || !utils.isMailValid(user.email!!))
                return@launch
            if(user.password.isNullOrEmpty() || !utils.isPasswordValid(user.password!!))
                return@launch

            println("****************")
            println("insert "+user.email)
            val idNewUser=insertUser(user)
            user.id=idNewUser
            _navigateToLoginFragment.value = user
        }
    }


    private val _AccountForm=MutableLiveData<AccountFormState>()
    val accountFormState: LiveData<AccountFormState> = _AccountForm

    //TODO: call this function
    fun AccountDataChanged(username: String, password: String) {
        if (!isMailValid(username)) {
            _AccountForm.value =
                AccountFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _AccountForm.value =
                AccountFormState(passwordError = R.string.invalid_password)
        } else {
            _AccountForm.value =
                AccountFormState(isDataValid = true)
        }
    }

}
