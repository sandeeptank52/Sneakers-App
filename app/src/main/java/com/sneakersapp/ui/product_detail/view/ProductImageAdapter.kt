package com.sneakersapp.ui.product_detail.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sneakersapp.databinding.ItemProductImgBinding

class ProductImageAdapter : RecyclerView.Adapter<ProductImageAdapter.ProductImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder {
        val binding = ItemProductImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) {
        holder.bind()
    }

    inner class ProductImageViewHolder(private val binding: ItemProductImgBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

}