package com.martinz.myshoe.ui.shoe_add_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.martinz.myshoe.R
import com.martinz.myshoe.databinding.FragmentShoeAddBinding
import com.martinz.myshoe.model.Shoes
import com.martinz.myshoe.util.UiEvent
import com.martinz.myshoe.viewmodels.ShoeSharedViewModel
import kotlinx.coroutines.launch

class ShoeAddFragment : Fragment() {

    private var _binding: FragmentShoeAddBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShoeSharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater , R.layout.fragment_shoe_add ,container , false)
        binding.mViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        uiEventObservable()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
        _binding = null
    }


    private fun uiEventObservable() {
        lifecycleScope.launch {
            viewModel.uiEventChannel.collect { uiEvent ->
                when (uiEvent) {

                    is UiEvent.ShowToast -> {
                        Toast.makeText(context, uiEvent.message, Toast.LENGTH_SHORT).show()
                    }

                    is UiEvent.NavigateUp -> {
                        findNavController().navigateUp()
                    }
                }
            }
        }

    }


    private fun onClick() {
        binding.apply {
            btnAddShoe.setOnClickListener {
                viewModel.addShoe()

            }
            btnCancel.setOnClickListener {
                viewModel.shoes = Shoes()
                findNavController().navigateUp()
            }
        }
    }

}