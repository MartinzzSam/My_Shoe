package com.martinz.myshoe.ui.login_fragment

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.martinz.myshoe.R
import com.martinz.myshoe.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }



    private fun validation() {
        binding.apply {
            val email = etEmail.text.toString().trim().lowercase()
            val password = etPassword.text.toString().trim()
            onValidate(email, password)
        }
    }


    private fun onValidate(email: String, password: String) {
        binding.apply {

            if (email.isBlank()) {
                tfEmail.error = getString(R.string.blank)
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tfEmail.error = getString(R.string.email_error)
                return
            }

            if (password.length < 8) {
                tfPassword.error = getString(R.string.password_length)
                return
            }

            if (password.isBlank()) {
                tfPassword.error = getString(R.string.blank)
                return
            } else {
                tfEmail.error = null
                tfPassword.error = null
                onNavigate()
            }
        }
    }

    private fun onClick() {
        binding.apply {
            loginButton.setOnClickListener { validation() }
        }
    }

    private fun onNavigate() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }

}