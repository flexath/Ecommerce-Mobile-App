package com.flexath.ecommercemobile.presentation.states

data class ProductCategoryListState(
    val productCategoryList: List<String> = emptyList(),
    val isLoading: Boolean = false
)
