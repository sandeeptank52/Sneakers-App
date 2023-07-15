package com.sneakersapp.ui.product_detail.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sneakersapp.data.models.Color
import com.sneakersapp.databinding.ItemProductColorBinding

class ProductColorAdapter : ListAdapter<Color, ProductColorAdapter.ProductColorViewHolder>(ProductColorDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductColorViewHolder {
        val binding = ItemProductColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductColorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductColorViewHolder(private val binding: ItemProductColorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(color: Color) {
            binding.apply {
                this.color = color
                this.root.isSelected = color.isSelected
                this.root.setOnClickListener {
                    currentList.map {
                        it.isSelected = color == it
                    }
                    notifyDataSetChanged()
                }
            }
            binding.executePendingBindings()
        }
    }

    object ProductColorDiffCallback : DiffUtil.ItemCallback<Color>() {
        override fun areItemsTheSame(oldItem: Color, newItem: Color): Boolean {
            return oldItem.colorCode == newItem.colorCode && oldItem.isSelected == newItem.isSelected
        }

        override fun areContentsTheSame(oldItem: Color, newItem: Color): Boolean {
            return oldItem == newItem
        }
    }

}