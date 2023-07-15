package com.sneakersapp.ui.cart.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sneakersapp.data.models.Product
import com.sneakersapp.databinding.ItemCartProductBinding

class CartProductAdapter(private val cartAdapterClickListeners: CartAdapterClickListeners) :
    ListAdapter<Product, CartProductAdapter.CartProductViewHolder>(CartProductDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val binding =
            ItemCartProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val currentProduct = getItem(position)
        holder.bind(currentProduct)
    }

    inner class CartProductViewHolder(private val binding: ItemCartProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                binding.product = product
                this.ivDelete.setOnClickListener {
                    cartAdapterClickListeners.removeProductFromCart(product)
                }
            }
        }
    }

    object CartProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}