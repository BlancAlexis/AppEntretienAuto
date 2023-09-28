package com.example.manageyourcar.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.manageyourcar.R
import com.example.manageyourcar.databinding.FragmentAddCarBinding
import com.example.manageyourcar.databinding.FragmentAddUserBinding
import com.example.manageyourcar.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class addCarFragment : Fragment() {

    val userViewModel: UserViewModel by viewModel()
    private lateinit var binding: FragmentAddCarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddCarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
       // binding.spiMake.onItemSelectedListener = object : Adap
    }

    companion object {
        fun newInstance(): addCarFragment {
            return addCarFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerData = arrayOf("Option 1", "Option 2", "Option 3")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spiModel.adapter = adapter

        val spinnerDatas = arrayOf("Option 1", "Option 2", "Option 3")

        val adapterA = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerDatas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spiMake.adapter = adapterA


        binding.spiMake.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                println(binding.spiMake.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

            binding.spiModel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    println(binding.spiModel.selectedItem.toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
    }}
}