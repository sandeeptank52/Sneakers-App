package com.sneakersapp.utils

import com.sneakersapp.R
import com.sneakersapp.data.models.Color
import com.sneakersapp.data.models.Media
import com.sneakersapp.data.models.Product
import com.sneakersapp.data.models.Size
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DummyServer {

    private val cartList: MutableList<Product> = mutableListOf()

    suspend fun getProducts(): List<Product> = withContext(Dispatchers.Default) {
        val productList = mutableListOf<Product>()
        for (i in 1..100) {
            val product = Product(
                id = "$i",
                brand = "Brand$i",
                colorway = "Colorway$i",
                gender = "Gender$i",
                media = Media(
                    imageUrl = "https://example.com/image$i.jpg",
                    smallImageUrl = "https://example.com/small_image$i.jpg",
                    thumbUrl = "https://example.com/thumb_image$i.jpg"
                ),
                releaseDate = "ReleaseDate$i",
                retailPrice = 100 + i,
                styleId = "StyleId$i",
                shoe = "Shoe$i",
                name = "Nike Air $i",
                title = "Product Title $i",
                year = 2023
            )
            productList.add(product)
        }
        productList
    }


    fun getProductDetails(id: String): Product {
        return Product(
            id = id,
            brand = "Brand$id",
            colorway = "Colorway$id",
            gender = "Gender$id",
            media = Media(
                imageUrl = "https://example.com/image$id.jpg",
                smallImageUrl = "https://example.com/small_image$id.jpg",
                thumbUrl = "https://example.com/thumb_image$id.jpg"
            ),
            releaseDate = "ReleaseDate$id",
            retailPrice = 100 + id.toInt(),
            styleId = "StyleId$id",
            shoe = "Shoe$id",
            name = "Nike Air $id",
            title = "Product Title $id",
            year = 2023,
            sizes = getSizeList(),
            colors = getColorList()
        )
    }

    private fun getSizeList(): List<Size> {
        val sizeList = mutableListOf<Size>()
        for (i in 1..3) {
            val size = Size(
                size = i + 5
            )
            sizeList.add(size)
        }
        return sizeList
    }

    private fun getColorList(): List<Color> {
        val colorList = mutableListOf<Color>()
        colorList.add(Color(R.color.light_salmon))
        colorList.add(Color(R.color.white))
        colorList.add(Color(R.color.baby_pink))
        return colorList
    }


    fun addProductToCart(product: Product) {
        cartList.add(product)
    }

    fun getCartProducts(): List<Product> {
        return cartList
    }

    fun removeProductFromCart(product: Product) {
        cartList.remove(product)
    }
}