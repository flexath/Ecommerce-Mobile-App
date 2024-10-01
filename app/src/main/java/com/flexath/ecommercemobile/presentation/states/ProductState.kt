package com.flexath.ecommercemobile.presentation.states

import com.flexath.ecommercemobile.domain.model.ProductVO

data class ProductState(
    val product: ProductVO? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
