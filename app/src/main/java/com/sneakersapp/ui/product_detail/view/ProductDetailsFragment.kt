package com.sneakersapp.ui.product_detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.sneakersapp.R
import com.sneakersapp.data.models.Product
import com.sneakersapp.databinding.FragmentProductDetailsBinding
import com.sneakersapp.ui.product_detail.viewmodel.ProductDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment(), ProductDetailsFragmentClickListeners {
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!


    private val vm: ProductDetailsViewModel by viewModels()
    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.fetchProduct(args.productID)
        bindViews()
    }

    private fun bindViews() {
        binding.clicklistener = this
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvProductImage)
        binding.rvProductImage.adapter = ProductImageAdapter()
        binding.rvProductSize.adapter = ProductSizeAdapter()
        binding.rvProductColor.adapter = ProductColorAdapter()
        val indicatorAdapter = ProductImageIndicatorAdapter()
        binding.rvIndicator.adapter = indicatorAdapter
        binding.rvProductImage.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dx != 0) {
                    val layoutManager = binding.rvProductImage.layoutManager as LinearLayoutManager
                    val visiblePosition = layoutManager.findFirstVisibleItemPosition()
                    indicatorAdapter.setCurrentPosition(visiblePosition)
                }
            }
        })
    }

    private fun observeViewModel() {
        vm.errorMsg.observe(this) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
        vm.product.observe(this) {
            binding.product = it
            updateSizeAndColorOfProduct(it)
        }
        vm.navigateToCart.observe(this) {
            Toast.makeText(requireContext(), "Added to Cart", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_productDetailsFragment_to_action_cart)
        }
    }

    private fun updateSizeAndColorOfProduct(product: Product) {
        if (product.sizes.isNotEmpty()) {
            (binding.rvProductSize.adapter as ProductSizeAdapter).submitList(product.sizes.apply {
                this.first().isSelected = true
            })
        }
        if (product.colors.isNotEmpty()) {
            (binding.rvProductColor.adapter as ProductColorAdapter).submitList(product.colors.apply {
                this.first().isSelected = true
            })
        }
    }

    override fun onBackClick() {
        requireActivity().onBackPressed()
    }

    override fun onAddToCartClick() {
        vm.addProductToCart()
    }
}