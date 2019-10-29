package com.popo.untitledandroidproject.data

import com.popo.untitledandroidproject.database.LocalDatabase
import com.popo.untitledandroidproject.database.UserDao
import com.popo.untitledandroidproject.model.User
import java.io.IOException
import java.lang.Exception
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    abstract val dataSource : UserDao

    fun login(username: String, password: String): Result<User> {
        try {
            // TODO: handle loggedInUser authentication
            val result=dataSource.login(username,password)
            if(result==null){
                throw Exception  ("invalide login")
            }
            val user = User(
                UUID.randomUUID().toString(),
                "Jane Doe"
            )
            return Result.Success(result)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

