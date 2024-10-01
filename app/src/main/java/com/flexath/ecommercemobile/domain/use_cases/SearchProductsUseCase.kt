package com.flexath.ecommercemobile.domain.use_cases

import com.flexath.ecommercemobile.data.Resource
import com.flexath.ecommercemobile.domain.model.ProductVO
import com.flexath.ecommercemobile.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<ProductVO>>> {
        return productRepository.searchProducts(query = query)
    }
}