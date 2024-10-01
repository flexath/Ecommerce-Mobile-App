package com.flexath.ecommercemobile.domain.use_cases

import com.flexath.ecommercemobile.data.Resource
import com.flexath.ecommercemobile.domain.repository.ProductRepository
import com.flexath.ecommercemobile.domain.model.ProductVO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductOfCategoryUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(
        categoryName: String
    ): Flow<Resource<List<ProductVO>>> {
        return productRepository.getProductsOfCategory(categoryName)
    }
}