package com.flexath.ecommercemobile.domain.use_cases

import javax.inject.Inject

data class MainUseCases @Inject constructor(
    val getAllProductsUseCase: GetAllProductUseCase,
    val getProductUseCase: GetProductUseCase,
    val getAllProductCategoriesUseCase: GetAllCategoriesUseCase,
    val getAllProductOfCategoryUseCase: GetAllProductOfCategoryUseCase,
    val searchProductsUseCase: SearchProductsUseCase
)