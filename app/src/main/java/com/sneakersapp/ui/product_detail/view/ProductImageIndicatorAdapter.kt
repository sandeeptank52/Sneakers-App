package com.sneakersapp.ui.product_detail.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sneakersapp.databinding.ItemIndicatorBinding

class ProductImageIndicatorAdapter( private var currentPosition: Int = 0) : RecyclerView.Adapter<ProductImageIndicatorAdapter.ProductImageIndicatorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageIndicatorViewHolder {
        val binding = ItemIndicatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductImageIndicatorViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ProductImageIndicatorViewHolder, position: Int) {
        holder.bind(holder.adapterPosition)
    }

    fun setCurrentPosition(position: Int) {
        this.currentPosition = position
        notifyDataSetChanged()
    }

    inner class ProductImageIndicatorViewHolder(private val binding: ItemIndicatorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(adapterPosition: Int) {
            binding.apply {
                if(adapterPosition == currentPosition){
                    ivIndicator.alpha = 1f
                }else{
                    ivIndicator.alpha = 0.2f
                }
            }

        }
    }

}