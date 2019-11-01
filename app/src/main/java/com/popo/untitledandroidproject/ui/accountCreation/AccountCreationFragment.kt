package com.popo.untitledandroidproject.ui.accountCreation

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.popo.untitledandroidproject.R
import com.popo.untitledandroidproject.database.LocalDatabase
import com.popo.untitledandroidproject.databinding.*


class AccountCreationFragment : Fragment() {

    companion object {
        fun newInstance() =
            AccountCreationFragment()
    }

    private lateinit var viewModel: AccountCreationViewModel
    private lateinit var binding: AccountCreationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(inflater,R.layout.account_creation_fragment,container,false)
        binding.lifecycleOwner=this

        val application = requireNotNull(this.activity ).application
        val dataSource = LocalDatabase.getInstance(application).userDao

        val accountCreationViewModelFactory= AccountCreationViewModelFactory(dataSource,application)
        viewModel = ViewModelProviders.of(this, accountCreationViewModelFactory)
            .get(AccountCreationViewModel::class.java)

        binding.viewModel=viewModel


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AccountCreationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
