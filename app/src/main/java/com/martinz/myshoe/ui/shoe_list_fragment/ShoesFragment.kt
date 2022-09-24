package com.martinz.myshoe.ui.shoe_list_fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.martinz.myshoe.R
import com.martinz.myshoe.databinding.FragmentShoesBinding
import com.martinz.myshoe.databinding.ItemShoeBinding
import com.martinz.myshoe.model.Shoes
import com.martinz.myshoe.viewmodels.ShoeSharedViewModel

class ShoesFragment : Fragment() {

    private var _binding: FragmentShoesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShoeSharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater , R.layout.fragment_shoes ,container , false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        isLoadingObservable()
        shoesObservable()
        onClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
        _binding = null
    }



    private fun shoesObservable() {
        viewModel.shoesLiveData.observe(binding.lifecycleOwner!!) {
            it.forEach { shoe ->
                createShoesView(shoes = shoe)
            }
        }
    }

    private fun isLoadingObservable() {
        viewModel.isLoading.observe(binding.lifecycleOwner!!) { loading ->
            if(loading == true) {
               binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }
    }




     private fun createShoesView(shoes: Shoes) {
        val view: ItemShoeBinding =
            ItemShoeBinding.inflate(layoutInflater)

        try {
            if (shoes.shoesPicture != null)
            view.ivShoe.setImageResource(shoes.shoesPicture)
            view.shoe = shoes
            binding.container.addView(view.root)
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "addNewView: ${e.message ?: e.toString()}")
        }
    }

    private fun onClick() {
        Log.i("Tag" , binding.toString())
        binding.apply {
            addNewShoes.setOnClickListener {
                findNavController().navigate(ShoesFragmentDirections.actionShoesFragmentToShoeAddFragment())
            }
            myToolbar.setNavigationOnClickListener {
                findNavController().navigate(ShoesFragmentDirections.actionShoesFragmentToLoginFragment())
            }
        }


    }


}