package com.shift.test.validator.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shift.test.validator.R
import com.shift.test.validator.databinding.MainScreenFragmentBinding

class MainScreenFragment: Fragment() {

    lateinit var binding: MainScreenFragmentBinding
    lateinit var args: MainScreenFragmentArgs
    lateinit var name: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_screen_fragment,
            container,
            false
        )
        args = MainScreenFragmentArgs.fromBundle(arguments!!)
        name = args.firstName + " " + args.lastName
        binding.helloButton.setOnClickListener { onHello() }
        return binding.root
    }

    private fun onHello() {
        Toast.makeText(context, "Hello, ${name}", Toast.LENGTH_LONG).show()
    }

}