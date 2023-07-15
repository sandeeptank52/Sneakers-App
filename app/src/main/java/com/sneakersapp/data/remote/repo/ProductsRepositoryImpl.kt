package com.sneakersapp.data.remote.repo

import com.sneakersapp.data.models.Product
import com.sneakersapp.data.remote.AppApis
import com.sneakersapp.domain.repo.ProductsRepository
import com.sneakersapp.utils.DummyServer
import retrofit2.Response
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val appApi: AppApis) : ProductsRepository {
    override suspend fun getProducts(): Response<List<Product>> {
        val products = DummyServer.getProducts()
        return Response.success(products)
    }

    override suspend fun getProductDetails(id: String): Response<Product> {
        return Response.success(DummyServer.getProductDetails(id))
    }

    override suspend fun addProductToCart(product: Product): Response<Boolean> {
        DummyServer.addProductToCart(product)
        return Response.success(true)
    }

    override suspend fun getCartProducts(): Response<List<Product>> {
        return Response.success(DummyServer.getCartProducts())
    }

    override suspend fun removeProductFromCart(product: Product): Response<Boolean> {
        DummyServer.removeProductFromCart(product)
        return Response.success(true)
    }
}