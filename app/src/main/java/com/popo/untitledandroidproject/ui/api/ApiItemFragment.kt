package com.popo.untitledandroidproject.ui.api

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.popo.untitledandroidproject.R
import com.popo.untitledandroidproject.databinding.FragmentApiitemListBinding
import com.popo.untitledandroidproject.model.Movie
import com.popo.untitledandroidproject.service.MyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ApiItemFragment.OnListFragmentInteractionListener] interface.
 */
class ApiItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentApiitemListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_apiitem_list,container,false)

        val application = requireNotNull(this.activity).application

        val viewModelFactory = ApiListViewModelFactory(application)

        val viewModel=
            ViewModelProviders.of(
                this, viewModelFactory).get(ApiListViewModel::class.java)

        binding.viewModel=viewModel
        binding.lifecycleOwner = this



        var viewModelJob = Job()
        val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
        var listMovies=ArrayList<Movie>()
        coroutineScope.launch {
            val getPropertiesDeferred = MyApi.retrofitService.getPropertiesAsync()
            try {
                val result = getPropertiesDeferred.await()
                val nbElements=result.parameters.rows
                for(i in 0 until nbElements){
                    val record= result.records!![i].fields
                    val title=record.title
                    val img_src=record.urlPoster
                    val year=Integer.valueOf(record.year)
                    val director=record.directedBy
                    val wikiUrl=record.urlEn

                    var movie=Movie(title,img_src,year,director,wikiUrl)
                    listMovies.add(movie)
                }
                binding.list.adapter=MyApiItemRecyclerViewAdapter(listMovies)

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }

        //val view = inflater.inflate(R.layout.fragment_apiitem_list, container, false)



        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


}
