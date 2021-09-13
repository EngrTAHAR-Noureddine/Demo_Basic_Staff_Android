package com.example.demo_basic_staff_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.demo_basic_staff_android.databinding.FragmentCalculatorBinding

class Calculator : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            // bind calculator.kt with fragment.xml
        val binding: FragmentCalculatorBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_calculator,
                container,
                false
            )

        //setHasOptionsMenu(true)
        return binding.root
    }




}