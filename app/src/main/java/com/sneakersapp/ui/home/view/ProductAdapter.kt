package com.sneakersapp.ui.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sneakersapp.R
import com.sneakersapp.data.models.Product
import com.sneakersapp.databinding.ItemProductBinding
import java.util.*

class ProductAdapter(private val productAdapterClickListeners: ProductAdapterClickListeners) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(), Filterable {
    private val differ = AsyncListDiffer(this, ProductDiffCallback)
    private val filteredList = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = filteredList[position]
        holder.bind(currentProduct)
    }

    override fun getItemCount(): Int = filteredList.size

    fun submitList(products: List<Product>) {
        filteredList.addAll(products)
        differ.submitList(products)
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                binding.product = product
                Glide.with(itemView)
                    .load(R.drawable.nike_img)
                    .into(ivProductImg)
                this.root.setOnClickListener {
                    productAdapterClickListeners.onProductClick(product)
                }
            }
            binding.executePendingBindings()
        }
    }

    object ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredResults = if (constraint.isNullOrEmpty()) {
                    differ.currentList.toList()
                } else {
                    val filterPattern = constraint.toString().lowercase(Locale.ROOT).trim()
                    differ.currentList.filter { product ->
                        product.name?.lowercase(Locale.ROOT)?.contains(filterPattern) ?: false
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredResults
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val filteredList = results?.values as? List<Product>
                if (filteredList != null) {
                    this@ProductAdapter.filteredList.clear()
                    this@ProductAdapter.filteredList.addAll(filteredList)
                    notifyDataSetChanged()
                }
            }
        }
    }
}