package com.martinz.myshoe.ui.instructions_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.martinz.myshoe.R
import com.martinz.myshoe.databinding.FragmentInstructionsBinding
import kotlinx.coroutines.*


class InstructionsFragment : Fragment() {
    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater , R.layout.fragment_instructions , container , false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }


    private fun onClick() {
        binding.apply {
            binding.nextBtn.setOnClickListener { findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoesFragment()) }
        }
    }




}