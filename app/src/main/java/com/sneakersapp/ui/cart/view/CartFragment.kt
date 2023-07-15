package com.sneakersapp.ui.cart.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sneakersapp.data.models.Product
import com.sneakersapp.databinding.FragmentCartBinding
import com.sneakersapp.ui.cart.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(), CartFragmentClickListeners {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val vm: CartViewModel by viewModels()
    private val cartProductAdapter: CartProductAdapter by lazy {
        CartProductAdapter(object : CartAdapterClickListeners {
            override fun removeProductFromCart(product: Product) {
                vm.removeProductFromCart(product)
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
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = vm
        binding.clicklistener = this
        binding.rvCartItem.adapter = cartProductAdapter
    }


    private fun observeViewModel() {
        vm.cartProducts.observe(this) {
            cartProductAdapter.submitList(it.toList())
            vm.updateUIData(it)
        }
        vm.errorMsg.observe(this) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackClick() {
        requireActivity().onBackPressed()
    }

}