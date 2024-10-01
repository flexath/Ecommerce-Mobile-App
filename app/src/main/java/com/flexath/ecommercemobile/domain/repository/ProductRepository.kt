package com.flexath.ecommercemobile.domain.repository

import com.flexath.ecommercemobile.data.Resource
import com.flexath.ecommercemobile.domain.model.ProductVO
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(
        limit: Int?
    ): Flow<Resource<List<ProductVO>>>

    fun getProduct(
        productId: Int
    ): Flow<Resource<ProductVO>>

    fun getAllCategories(): Flow<Resource<List<String>>>

    fun getProductsOfCategory(
        categoryName: String
    ): Flow<Resource<List<ProductVO>>>

    fun searchProducts(query: String): Flow<Resource<List<ProductVO>>>
}