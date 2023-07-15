package com.sneakersapp.ui.home.view

import com.sneakersapp.data.models.Product

interface ProductAdapterClickListeners {
    fun onProductClick(product: Product)
}