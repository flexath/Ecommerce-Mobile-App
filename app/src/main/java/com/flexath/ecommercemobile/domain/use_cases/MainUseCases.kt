package com.flexath.ecommercemobile.domain.use_cases

import com.flexath.ecommercemobile.domain.use_cases.ktor.GetAllCategoriesKtorUseCase
import com.flexath.ecommercemobile.domain.use_cases.ktor.GetAllProductKtorUseCase
import com.flexath.ecommercemobile.domain.use_cases.ktor.GetAllProductOfCategoryKtorUseCase
import com.flexath.ecommercemobile.domain.use_cases.ktor.GetProductKtorUseCase
import com.flexath.ecommercemobile.domain.use_cases.ktor.SearchProductsKtorUseCase
import com.flexath.ecommercemobile.domain.use_cases.retrofit.GetAllCategoriesUseCase
import com.flexath.ecommercemobile.domain.use_cases.retrofit.GetAllProductOfCategoryUseCase
import com.flexath.ecommercemobile.domain.use_cases.retrofit.GetAllProductUseCase
import com.flexath.ecommercemobile.domain.use_cases.retrofit.GetProductUseCase
import com.flexath.ecommercemobile.domain.use_cases.retrofit.SearchProductsUseCase
import javax.inject.Inject

//data class MainUseCases @Inject constructor(
//    val getAllProductsUseCase: GetAllProductUseCase,
//    val getProductUseCase: GetProductUseCase,
//    val getAllProductCategoriesUseCase: GetAllCategoriesUseCase,
//    val getAllProductOfCategoryUseCase: GetAllProductOfCategoryUseCase,
//    val searchProductsUseCase: SearchProductsUseCase
//)

data class MainUseCases @Inject constructor(
    val getAllProductsUseCase: GetAllProductKtorUseCase,
    val getProductUseCase: GetProductKtorUseCase,
    val getAllProductCategoriesUseCase: GetAllCategoriesKtorUseCase,
    val getAllProductOfCategoryUseCase: GetAllProductOfCategoryKtorUseCase,
    val searchProductsUseCase: SearchProductsKtorUseCase
)