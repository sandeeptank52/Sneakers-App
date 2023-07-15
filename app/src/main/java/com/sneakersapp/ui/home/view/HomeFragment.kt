package com.sneakersapp.ui.home.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sneakersapp.R
import com.sneakersapp.data.models.Product
import com.sneakersapp.databinding.FragmentHomeBinding
import com.sneakersapp.ui.home.viewmodel.HomeViewModel
import com.sneakersapp.utils.AppConstants.INTENT_PARAM_PRODUCT_ID
import com.sneakersapp.utils.hide
import com.sneakersapp.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val vm: HomeViewModel by viewModels()

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter(object : ProductAdapterClickListeners {
            override fun onProductClick(product: Product) {
                openProductDetailPage(product)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvProducts.adapter = productAdapter
        binding.ivSearch.setOnClickListener {
            if (binding.etSearch.isVisible) {
                performSearch(binding.etSearch.text.toString())
            } else {
                updateSearch(true)
            }
        }
        binding.closeSearch.setOnClickListener {
            updateSearch(false)
        }
    }

    private fun openProductDetailPage(product: Product) {
        val bundle = bundleOf(INTENT_PARAM_PRODUCT_ID to product.id)
        findNavController().navigate(R.id.action_action_home_to_productDetailsFragment, bundle)
    }

    private fun observeViewModel() {
        vm.products.observe(this) { products ->
            productAdapter.submitList(products)
        }
        vm.errorMsg.observe(this) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateSearch(isNeedToOpen: Boolean) {
        if (isNeedToOpen) {
            binding.tvTitle.hide()
            binding.etSearch.show()
            binding.etSearch.requestFocus()
            binding.closeSearch.show()
        } else {
            binding.tvTitle.show()
            binding.etSearch.hide()
            binding.closeSearch.hide()
            val inputMethodManager =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
            performSearch("")
        }
    }

    private fun performSearch(query: String) {
        productAdapter.filter.filter(query)
    }
}