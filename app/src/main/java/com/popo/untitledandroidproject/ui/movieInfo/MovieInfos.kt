package com.popo.untitledandroidproject.ui.movieInfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs

import com.popo.untitledandroidproject.R
import com.popo.untitledandroidproject.databinding.MovieInfosFragmentBinding
import com.squareup.picasso.Picasso

class MovieInfos : Fragment() {

    companion object {
        fun newInstance() =
            MovieInfos()
    }

    private lateinit var binding: MovieInfosFragmentBinding
    private lateinit var viewModel: MovieInfosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.movie_infos_fragment, container, false)
        binding.lifecycleOwner=this

        val args=MovieInfosArgs.fromBundle(arguments!!)
        val movie=args.movie
        val application= requireNotNull(this.activity).application

        val viewModelFactory = MovieInfosViewModelFactory(application,movie)
        viewModel=ViewModelProviders.of(this,viewModelFactory)
            .get(MovieInfosViewModel::class.java)


        Picasso.get().load(movie.img).into(binding.imgPoster)
        binding.tvYear.text=movie.year.toString()
        binding.tvDirector.text=movie.director
        binding.tvTitle.text=movie.title
        binding.tvWiki.text=movie.wikiUrl


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieInfosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
