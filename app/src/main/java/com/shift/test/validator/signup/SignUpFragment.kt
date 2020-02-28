package com.shift.test.validator.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.shift.test.validator.R
import com.shift.test.validator.databinding.SignUpFragmentBinding

class SignUpFragment: Fragment() {

    private lateinit var binding: SignUpFragmentBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.sign_up_fragment,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        binding.signUpViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.description.observe(viewLifecycleOwner, Observer { getDescription ->
            binding.description.text = getDescription.toString()
        })
        viewModel.isValid.observe(viewLifecycleOwner, Observer { isValid ->
            if (isValid) onSignUp()
        })


        binding.apply {
            firstNameEdit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
//
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.getFirstName(s.toString())
                }
            })
            lastNameEdit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
//
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.getlastName(s.toString())
                }
            })
            birthdayEdit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.getBirthday(s.toString())
                }
            })
            passwordEdit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.getPassword(s.toString())
                }
            })
            confirmPasswordEdit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.getConfirmedPassword(s.toString())
                }
            })
        }

        updateProfile()

        return binding.root
    }

   private fun updateProfile() {
       binding.apply {
           firstNameEdit.setText(viewModel.firstName.value)
           lastNameEdit.setText(viewModel.lastName.value)
           birthdayEdit.setText(viewModel.birthday.value)
           passwordEdit.setText(viewModel.password.value)
           confirmPasswordEdit.setText(viewModel.confirmedPassword.value)
       }
   }

   private fun onSignUp() {
        view?.findNavController()
            ?.navigate(SignUpFragmentDirections
                .actionSignUpDestinationToMainScreenFragment(
                    viewModel.firstName.value?:"", viewModel.lastName.value?:"") )
       viewModel.onSignedUp()
   }
}