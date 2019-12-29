package com.popo.untitledandroidproject.ui.presentationScreen

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.popo.untitledandroidproject.R
import com.popo.untitledandroidproject.databinding.FragmentPresentationScreenBinding


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PresentationScreen.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PresentationScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class PresentationScreen : Fragment() {
    private lateinit var binding: FragmentPresentationScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        binding =DataBindingUtil.inflate(inflater, R.layout.fragment_presentation_screen,container,false)
        binding.lifecycleOwner=this

        binding.btnOk.setOnClickListener { view ->
            binding.root.findNavController().navigate(R.id.action_presentationScreen_to_loginFragment)

        }
        return binding.root
    }

}
