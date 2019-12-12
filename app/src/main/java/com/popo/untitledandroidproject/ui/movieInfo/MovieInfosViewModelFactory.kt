package com.popo.untitledandroidproject.ui.movieInfo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.popo.untitledandroidproject.model.Movie
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class MovieInfosViewModelFactory(private val application: Application, private val movie: Movie): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieInfosViewModel::class.java)){
            return MovieInfosViewModel(
                application,
                movie
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}