package com.sneakersapp.ui.cart.view

import com.sneakersapp.data.models.Product

interface CartAdapterClickListeners {
    fun removeProductFromCart(product: Product)
}