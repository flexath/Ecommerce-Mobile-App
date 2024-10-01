package com.flexath.ecommercemobile.data.remote.dto

import com.flexath.ecommercemobile.domain.model.ProductVO
import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("brand")
    val brand: String?,

    @SerializedName("category")
    val category: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("discountPercentage")
    val discountPercentage: Double?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("images")
    val images: List<String>?,

    @SerializedName("price")
    val price: Double?,

    @SerializedName("rating")
    val rating: Double?,

    @SerializedName("stock")
    val stock: Int?,

    @SerializedName("thumbnail")
    val thumbnail: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("availabilityStatus")
    val availabilityStatus: String?,

    @SerializedName("dimensions")
    val dimensions: DimensionsDto?,

    @SerializedName("meta")
    val meta: MetaDto?,

    @SerializedName("minimumOrderQuantity")
    val minimumOrderQuantity: Int?,

    @SerializedName("returnPolicy")
    val returnPolicy: String?,

    @SerializedName("reviews")
    val reviews: List<ReviewDto>?,

    @SerializedName("shippingInformation")
    val shippingInformation: String?,

    @SerializedName("sku")
    val sku: String?,

    @SerializedName("tags")
    val tags: List<String>?,

    @SerializedName("warrantyInformation")
    val warrantyInformation: String?,

    @SerializedName("weight")
    val weight: Double?
) {
    fun toProductVO(): ProductVO {
        return ProductVO(
            id = id ?: 0,
            title = title,
            description = description,
            price = price?.toInt(),
            rating = rating,
            stock = stock,
            brand = brand,
            thumbnail = thumbnail,
            images = images,
            shippingInformation = shippingInformation,
            warrantyInformation = warrantyInformation,
            availabilityStatus = availabilityStatus
        )
    }
}