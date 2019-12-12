package com.popo.untitledandroidproject.ui.movieInfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.popo.untitledandroidproject.R

class MovieInfos : Fragment() {

    companion object {
        fun newInstance() =
            MovieInfos()
    }

    private lateinit var viewModel: MovieInfosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_infos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieInfosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
