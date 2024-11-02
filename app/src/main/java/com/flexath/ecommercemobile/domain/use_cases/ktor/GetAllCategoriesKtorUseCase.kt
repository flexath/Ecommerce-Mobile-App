package com.flexath.ecommercemobile.domain.use_cases.ktor

import com.flexath.ecommercemobile.data.Resource
import com.flexath.ecommercemobile.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoriesKtorUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<String>>> {
        return productRepository.getAllCategories()
    }
}