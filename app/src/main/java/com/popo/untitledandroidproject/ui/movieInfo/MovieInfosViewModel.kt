package com.popo.untitledandroidproject.ui.movieInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.popo.untitledandroidproject.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.Job

class MovieInfosViewModel(application: Application, private val _movie: Movie) : AndroidViewModel(application) {
    private val viewModelJob = Job()
    private val uiScope= CoroutineScope(Dispatchers.Main+viewModelJob )

//    private val _movie=MutableLiveData<Movie>()
    val movie: Movie
        get()=_movie
}
