package com.sneakersapp.domain.repo

import com.sneakersapp.data.models.Product
import retrofit2.Response

interface ProductsRepository {

    suspend fun getProducts(): Response<List<Product>>

    suspend fun getProductDetails(id: String): Response<Product>

    suspend fun addProductToCart(product: Product): Response<Boolean>

    suspend fun getCartProducts(): Response<List<Product>>

    suspend fun removeProductFromCart(product: Product): Response<Boolean>
}