package com.flexath.ecommercemobile.data.repository

import androidx.room.util.query
import com.flexath.ecommercemobile.data.Resource
import com.flexath.ecommercemobile.data.remote.api.ApiConstants
import com.flexath.ecommercemobile.data.remote.dto.ProductCategoryItemDto
import com.flexath.ecommercemobile.data.remote.dto.ProductCategoryListDto
import com.flexath.ecommercemobile.data.remote.dto.ProductListResponseDto
import com.flexath.ecommercemobile.domain.model.ProductVO
import com.flexath.ecommercemobile.domain.repository.ProductRepository
import com.flexath.findit.main.data.remote.api.ProductApi
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductKtorRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : ProductRepository {
    override fun getAllProducts(limit: Int?): Flow<Resource<List<ProductVO>>> = flow {
        emit(Resource.Loading())

        try {
            val remoteProductList = httpClient.get<ProductListResponseDto> {
                url("${ApiConstants.DUMMY_JSON_BASE_URL}products?limit=${limit ?: 0}")
            }.toProductListResponse()
            emit(Resource.Success(remoteProductList.products.orEmpty()))
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
            val product = httpClient.get<ProductVO> {
                url("${ApiConstants.DUMMY_JSON_BASE_URL}products/${productId}")
            }
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
            val productList = httpClient.get<ProductCategoryListDto> {
                url("${ApiConstants.DUMMY_JSON_BASE_URL}products/categories")
            }.map {
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
            val remoteProductList = httpClient.get<ProductListResponseDto> {
                url("${ApiConstants.DUMMY_JSON_BASE_URL}products/category/${categoryName}")
            }.toProductListResponse().products
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
            val productList = httpClient.get<ProductListResponseDto> {
                url("${ApiConstants.DUMMY_JSON_BASE_URL}products/search?q=${query}")
            }.toProductListResponse().products
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