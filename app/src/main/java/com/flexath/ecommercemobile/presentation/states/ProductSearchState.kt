package com.flexath.ecommercemobile.presentation.states

import com.flexath.ecommercemobile.domain.model.ProductVO

data class ProductSearchState(
    val query: String = "",
    val productList: List<ProductVO> = emptyList()
)