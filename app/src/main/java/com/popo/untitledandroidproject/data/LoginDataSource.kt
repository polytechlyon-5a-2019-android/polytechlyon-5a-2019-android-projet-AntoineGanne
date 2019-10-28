package com.popo.untitledandroidproject.data

import com.popo.untitledandroidproject.model.User
import java.io.IOException
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<User> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = User(
                UUID.randomUUID().toString(),
                "Jane Doe"
            )
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

