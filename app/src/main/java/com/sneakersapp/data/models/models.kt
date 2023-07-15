package com.sneakersapp.data.models

data class Product(
    val id: String? = null,
    val brand: String? = null,
    val colorway: String? = null,
    val gender: String? = null,
    val media: Media? = Media(),
    val releaseDate: String? = null,
    val retailPrice: Int? = null,
    val styleId: String? = null,
    val shoe: String? = null,
    val name: String? = null,
    val title: String? = null,
    val year: Int? = null,
    val sizes : List<Size> = listOf(),
    val colors : List<Color> = listOf()
)

data class Media(
    var imageUrl: String? = null,
    var smallImageUrl: String? = null,
    var thumbUrl: String? = null
)

data class Size(
    var size : Int = 0,
    //local
    var isSelected : Boolean = false
)
data class Color(
    var colorCode : Int = 0,
    //local
    var isSelected : Boolean = false
)