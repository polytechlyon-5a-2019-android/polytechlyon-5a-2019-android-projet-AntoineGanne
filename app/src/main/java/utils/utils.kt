package utils

import android.util.Patterns

fun isUserNameValid(username: String): Boolean {
    return if (username.contains('@')) {
        Patterns.EMAIL_ADDRESS.matcher(username).matches()
    } else {
        username.isNotBlank()
    }
}

// A placeholder password validation check
fun isPasswordValid(password: String): Boolean {
    return password.length > 5
}


