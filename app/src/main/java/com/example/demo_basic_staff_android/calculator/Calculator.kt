package com.example.demo_basic_staff_android.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.demo_basic_staff_android.R
import com.example.demo_basic_staff_android.database.HistoryDataBase
import com.example.demo_basic_staff_android.databinding.FragmentCalculatorBinding

class Calculator : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
            // bind calculator.kt with fragment.xml
        val binding: FragmentCalculatorBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_calculator,
                container,
                false
            )
        val application = requireNotNull(this.activity).application

        val dataSource = HistoryDataBase.getInstance(application).historyDatabaseDao

        val viewModelFactory = CalculatorViewModelFactory(dataSource)

        val buttons = Buttons()
        val calculatorViewModel = ViewModelProvider(this, viewModelFactory).get(CalculatorViewModel::class.java)

        binding.calculatorViewModel = calculatorViewModel
        binding.button = buttons

        // binding.setLifecycleOwner(this)
        binding.lifecycleOwner = this
        //setHasOptionsMenu(true)
        return binding.root
    }




}