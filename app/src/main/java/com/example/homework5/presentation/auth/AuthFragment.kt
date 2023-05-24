package com.example.homework5.presentation.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.homework5.R
import com.example.homework5.data.model.remote.CheckUserNameResult
import com.example.homework5.data.remote.AuthResult
import com.example.homework5.databinding.FragmentAuthBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment: Fragment(R.layout.fragment_auth) {
    private val binding by viewBinding(FragmentAuthBinding::bind)
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            tilPassword.visibility = View.GONE
            tilUsername.setEndIconDrawable(R.drawable.ic_cancel_48)

            btnContinue.setOnClickListener {
                val username = editTextUsername.text.toString()
                viewModel.checkUserName(username)
            }

            viewModel.checkUsernameResultLiveData.observe(viewLifecycleOwner){response ->
                when(response){
                    is AuthResult.Error -> {
                        editTextUsername.isEnabled = true
                        editTextUsername.isFocusable = true
                        Snackbar.make(root, R.string.error_unknown, Snackbar.LENGTH_SHORT).setAction(
                            R.string.retry
                        ){viewModel.checkUserName(editTextUsername.text.toString())}.show()
                    }

                    is AuthResult.NetworkError -> {
                        editTextUsername.isEnabled = true
                        editTextUsername.isFocusable = true
                        Snackbar.make(root, R.string.error_network, Snackbar.LENGTH_SHORT).show()

                    }

                    is AuthResult.PasswordError -> {}

                    is AuthResult.Success -> {
                        when(response.data){
                            CheckUserNameResult.TooShort -> {
                                showUsernameErrorState(R.string.username_tooshort)
                            }
                            CheckUserNameResult.TooLong -> {
                                showUsernameErrorState(R.string.username_toolong)
                            }
                            CheckUserNameResult.InvalidCharacters -> {
                                showUsernameErrorState(R.string.username_invalid_char)
                            }
                            else -> {
                                hideUsernameErrorState()
                                tilPassword.visibility = View.VISIBLE
                                editTextUsername.isEnabled = false
                                editTextUsername.isFocusable = false

                                btnContinue.setOnClickListener {
                                    val username = editTextUsername.text.toString()
                                    val password = editTextPassword.text.toString()

                                    if (password.length > 7){
                                        hidePasswordErrorState()
                                        viewModel.authorize(username, password, response.data)
                                    }
                                    else {
                                        showPasswordErrorState(R.string.password_tooshort)
                                    }
                                }
                            }
                        }

                    }

                    is AuthResult.Validate -> {
                        editTextUsername.isFocusable = false
                        editTextUsername.isEnabled = false
                    }

                }
            }

            viewModel.tokenResponseLiveData.observe(viewLifecycleOwner){response ->
                when(response){
                    is AuthResult.Error -> {
                        editTextPassword.isEnabled = true
                        editTextPassword.isFocusable = true
                        Snackbar.make(root, R.string.error_unknown, Snackbar.LENGTH_SHORT).show()
                    }
                    is AuthResult.NetworkError -> {
                        editTextPassword.isEnabled = true
                        editTextPassword.isFocusable = true
                        Snackbar.make(root, R.string.error_network, Snackbar.LENGTH_SHORT).show()
                    }
                    is AuthResult.PasswordError -> {
                        editTextPassword.isEnabled = true
                        editTextPassword.isFocusable = true
                        showPasswordErrorState(R.string.password_tooshort)
                    }
                    is AuthResult.Success -> {
                        response.data.also { token ->
                            viewModel.saveToken(token)
                            findNavController().navigate(
                                AuthFragmentDirections.actionAuthFragmentToNavGraph()
                            )
                        }
                    }
                    is AuthResult.Validate -> {
                        editTextPassword.isEnabled = false
                    }
                }

            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(binding.root){view, insets ->
            val imeInset = insets.getInsets(WindowInsetsCompat.Type.ime())

            binding.btnContinue.updatePadding(
                bottom = imeInset.bottom
            )

            WindowInsetsCompat.Builder().setInsets(
                WindowInsetsCompat.Type.ime(),
                Insets.of(
                    imeInset.left,
                    0,
                    imeInset.right,
                    0)
            ).build()
        }
        binding.root.requestApplyInsets()


    }

    private fun showUsernameErrorState(stringCauseId: Int){
        with(binding){
            tilUsername.error = getString(stringCauseId)
            tilUsername.isEnabled = true
            tilUsername.setEndIconDrawable(R.drawable.ic_error)
        }
    }

    private fun hideUsernameErrorState(){
        with(binding){
            tilUsername.setEndIconDrawable(R.drawable.ic_cancel_24)
            tilUsername.error = null
        }
    }

    private fun showPasswordErrorState(stringCauseId:Int){
        with(binding){
            tilPassword.error = getString(stringCauseId)
            tilUsername.isEnabled = true
            tilPassword.setEndIconDrawable(R.drawable.ic_error)
        }
    }

    private fun hidePasswordErrorState(){
        with(binding){
            tilPassword.error = null
            tilPassword.setEndIconDrawable((R.drawable.ic_cancel_24))
        }
    }

}