package com.popo.untitledandroidproject.ui.movieInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavArgs
import com.popo.untitledandroidproject.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.Job

class MovieInfosViewModel(movieParam: Movie) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope= CoroutineScope(Dispatchers.Main+viewModelJob )

    private var _movie : Movie = movieParam
    val movie: Movie
        get()= _movie

    fun yearToString():String{
        return movie.year.toString()
    }

}
