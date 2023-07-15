package com.sneakersapp.ui.product_detail.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sneakersapp.data.models.Size
import com.sneakersapp.databinding.ItemProductSizeBinding

class ProductSizeAdapter :
    ListAdapter<Size, ProductSizeAdapter.ProductSizeViewHolder>(ProductSizeDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSizeViewHolder {
        val binding =
            ItemProductSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductSizeViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ProductSizeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductSizeViewHolder(private val binding: ItemProductSizeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(size: Size) {
            binding.apply {
                this.size = size
                this.root.isSelected = size.isSelected
                this.root.setOnClickListener {
                    currentList.map {
                        it.isSelected = size == it
                    }
                    notifyDataSetChanged()
                }
            }
            binding.executePendingBindings()
        }
    }

    object ProductSizeDiffCallback : DiffUtil.ItemCallback<Size>() {
        override fun areItemsTheSame(oldItem: Size, newItem: Size): Boolean {
            return oldItem.size == newItem.size && oldItem.isSelected == newItem.isSelected
        }

        override fun areContentsTheSame(oldItem: Size, newItem: Size): Boolean {
            return oldItem == newItem
        }
    }

}