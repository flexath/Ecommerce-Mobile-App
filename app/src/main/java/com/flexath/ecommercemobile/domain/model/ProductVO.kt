package com.flexath.ecommercemobile.domain.model

data class ProductVO(
    val id: Int = 0,
    val title: String?,
    val description: String? = "",
    val price: Int?,
    val rating: Double?,
    val stock: Int?,
    val brand: String?,
    val thumbnail: String?,
    val images: List<String>?,
    val shippingInformation: String? = "",
    val warrantyInformation: String? = "",
    val availabilityStatus: String? = ""
)
