package com.popo.untitledandroidproject.ui.accountCreation

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.popo.untitledandroidproject.R
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.popo.untitledandroidproject.database.LocalDatabase
import com.popo.untitledandroidproject.databinding.*
import com.popo.untitledandroidproject.ui.login.LoginFragmentDirections
import java.util.Calendar
import java.util.Date



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

        binding.inputBirthday.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(activity!!,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear,
                                                     dayOfMonth ->
                    binding.inputBirthday.text =
                        SpannableStringBuilder("$dayOfMonth/$monthOfYear/$year")
                    viewModel.user.value?.birthdayDate =
                        Date(year,monthOfYear,dayOfMonth).time
                }, year, month, day)
            dpd.show()
        }

        // Code qui remplace la fonction onValidate()
        viewModel.navigateToLoginFragment.observe(this, Observer { user ->
            user?.let {
                this.findNavController().navigate(R.id.action_accountCreationFragment_to_loginFragment)
                viewModel.doneNavigating()
            }
        })

//        binding.buttonValidateAccount.setOnClickListener {view ->
//            binding.root.findNavController().navigate(R.id.action_accountCreationFragment_to_loginFragment)
//        }

        binding.viewModel=viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AccountCreationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
