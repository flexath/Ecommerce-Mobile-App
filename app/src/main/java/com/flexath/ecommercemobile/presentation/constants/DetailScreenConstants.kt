package com.flexath.ecommercemobile.presentation.constants

import androidx.compose.ui.graphics.Color

data class ProductColor(
    val name: String,
    val color: Color
)

val productColors = listOf(
    ProductColor("Light Orange", Color(0xFFFF7743)),
    ProductColor("Teal", Color(0xFF009688)),
    ProductColor("Gold", Color(0xFFD4AF37)),
    ProductColor("Lavender", Color(0xFFE6E6FA)),
    ProductColor("Blue", Color(0xFF1E88E5)),
    ProductColor("Green", Color(0xFF43A047)),
    ProductColor("Yellow", Color(0xFFFFF176)),
    ProductColor("Purple", Color(0xFF9C27B0)),
    ProductColor("Orange", Color(0xFFFF9800)),
    ProductColor("Pink", Color(0xFFE55384)),
    ProductColor("Brown", Color(0xFF795548)),
    ProductColor("Gray", Color(0xFF9E9E9E)),
    ProductColor("Black", Color(0xFF000000))
)

val productSizes = listOf(5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0)