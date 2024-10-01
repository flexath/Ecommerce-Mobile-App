package com.flexath.ecommercemobile.presentation.states

import com.flexath.ecommercemobile.domain.model.ProductVO

data class ProductListState(
    val productList: List<ProductVO> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
