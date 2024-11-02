package com.flexath.ecommercemobile.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    val products: List<ProductVO>?
)
