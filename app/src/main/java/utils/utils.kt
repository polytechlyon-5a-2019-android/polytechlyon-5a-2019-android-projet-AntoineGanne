package utils

import android.util.Patterns
import androidx.databinding.InverseMethod
import java.text.SimpleDateFormat
import java.util.*

fun isMailValid(mail: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(mail).matches()
}

fun isUsernameValid(username: String): Boolean {
    return if (username.contains('@')) {
        Patterns.EMAIL_ADDRESS.matcher(username).matches()
    } else {
        username.isNotBlank()
    }
}

// A placeholder password validation check
fun isPasswordValid(password: String): Boolean {
    return password.length >= 5
}


object LongConverter {
    @JvmStatic
    @InverseMethod("stringToDate")
    fun dateToString(
        value: Long
    ): String {
        val date = Date(value)
        val f = SimpleDateFormat("dd/MM/yy")
        val dateText = f.format(date)
        return dateText
    }
    @JvmStatic
    fun stringToDate( value: String
    ): Long {
        val f = SimpleDateFormat("dd/MM/yy")
        val d = f.parse(value)
        return d.time
    }
}