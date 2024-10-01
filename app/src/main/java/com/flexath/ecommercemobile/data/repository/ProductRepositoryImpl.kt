package com.flexath.ecommercemobile.data.repository

import com.flexath.ecommercemobile.data.Resource
import com.flexath.ecommercemobile.domain.repository.ProductRepository
import com.flexath.findit.main.data.remote.api.ProductApi
import com.flexath.ecommercemobile.domain.model.ProductVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
): ProductRepository {

    override fun getAllProducts(
        limit: Int?
    ): Flow<Resource<List<ProductVO>>> = flow {
        emit(Resource.Loading())

        try {
            val remoteProductList = productApi.getAllProducts(limit).toProductListResponse().products
            emit(Resource.Success(remoteProductList))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = listOf()
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = listOf()
            ))
        }
    }

    override fun getProduct(productId: Int): Flow<Resource<ProductVO>> = flow {
        emit(Resource.Loading())

        try {
            val product = productApi.getProduct(productId = productId).toProductVO()
            emit(Resource.Success(
                data = product
            ))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = null
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = null
            ))
        }
    }

    override fun getAllCategories(): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())

        try {
            val productList = productApi.getAllCategories().map {
                it.name ?: ""
            }
            emit(Resource.Success(
                data = productList
            ))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = emptyList()
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = emptyList()
            ))
        }
    }

    override fun getProductsOfCategory(categoryName: String): Flow<Resource<List<ProductVO>>> = flow {
        emit(Resource.Loading())

        try {
            val remoteProductList = productApi.getProductsOfCategory(categoryName = categoryName).toProductListResponse().products
            emit(Resource.Success(
                data = remoteProductList
            ))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = listOf()
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = listOf()
            ))
        }
    }

    override fun searchProducts(query: String): Flow<Resource<List<ProductVO>>> = flow {
        emit(Resource.Loading())

        try {
            val productList = productApi.searchProducts(query = query).toProductListResponse().products
            emit(
                Resource.Success(
                data = productList
            ))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = emptyList()
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = emptyList()
            ))
        }
    }
}