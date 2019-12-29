package com.popo.untitledandroidproject.ui.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton

import com.popo.untitledandroidproject.R
import com.popo.untitledandroidproject.database.LocalDatabase
import com.popo.untitledandroidproject.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.view.*

import kotlinx.android.synthetic.main.fragment_login.*



class LoginFragment : Fragment() {


    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        super.onCreate(savedInstanceState)

        binding =DataBindingUtil.inflate(inflater, R.layout.fragment_login,container,false)
        binding.lifecycleOwner=this


        val application = requireNotNull(this.activity).application
        val dataSource = LocalDatabase.getInstance(application).userDao

        val loginViewModelFactory=LoginViewModelFactory(dataSource,application)
        loginViewModel = ViewModelProviders.of(this, loginViewModelFactory)
            .get(LoginViewModel::class.java)




        loginViewModel.loginFormState.observe(this@LoginFragment, Observer {
            val loginState = it ?: return@Observer

            if (loginState.usernameError != null) {
                binding.username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(viewLifecycleOwner, Observer {
            val loginResult = it ?: return@Observer

            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                onSuccessfulLogin(loginResult.success)
                this.findNavController().navigate(R.id.action_loginFragment_to_apiItemFragment)
                loginViewModel.doneNavigating()
            }
//            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
//            finish()
        })

        binding.username.afterTextChanged {
            loginViewModel.loginDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
            // disable login button unless both username / password is valid
            binding.login.isEnabled= loginViewModel.loginFormState.value?.isDataValid ?: true
        }

        binding.password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
                // disable login button unless both username / password is valid
                binding.login.isEnabled= loginViewModel.loginFormState.value?.isDataValid ?: true
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            binding.username.text.toString(),
                            binding.password.text.toString()
                        )
                }
                false
            }

            binding.login.setOnClickListener {
                loginViewModel.login(binding.username.text.toString(), binding.password.text.toString())
            }
        }


        binding.viewModel=loginViewModel
        binding.apply{
            username.hint=getString(R.string.prompt_email)
            password.hint=getString(R.string.prompt_password)
            login.text=getString(R.string.action_sign_in)
        }

        binding.bAccountCreation.setOnClickListener {view ->
            binding.root.findNavController().navigate(R.id.action_loginFragment_to_accountCreationFragment)
        }


        return binding.root
    }

    private fun onSuccessfulLogin(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        Toast.makeText(
            this.context,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()

    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(this.context, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
