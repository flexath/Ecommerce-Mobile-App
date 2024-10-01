package com.flexath.ecommercemobile.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.ecommercemobile.data.Resource
import com.flexath.ecommercemobile.domain.use_cases.MainUseCases
import com.flexath.ecommercemobile.presentation.states.ProductCategoryListState
import com.flexath.ecommercemobile.presentation.states.ProductListState
import com.flexath.ecommercemobile.presentation.states.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val mainUseCase: MainUseCases
) : ViewModel() {

    private var _productListState = MutableStateFlow(ProductListState())
    val productListState get() = _productListState.asStateFlow()

    private val _isProductListFetched = MutableStateFlow(false)
    val isProductListFetched get() = _isProductListFetched.asStateFlow()

    private val _productCategoryListState = MutableStateFlow(ProductCategoryListState())
    val productCategoryListState get() = _productCategoryListState.asStateFlow()

    // Shared flow to emit product data
    private val _productState = MutableStateFlow(ProductState())
    val productState get() = _productState.asStateFlow()

    private val _productListOfCategoryState = MutableStateFlow(ProductListState())
    val productListOfCategoryState get() = _productListOfCategoryState.asStateFlow()

    fun getAllProducts(
        limit: Int? = null
    ) {
        viewModelScope.launch {
            mainUseCase.getAllProductsUseCase.invoke(limit = limit)
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            _productListState.update { productState ->
                                productState.copy(
                                    productList = emptyList(),
                                    isLoading = true,
                                    isError = false
                                )
                            }
                        }

                        is Resource.Success -> {
                            _productListState.update { productState ->
                                productState.copy(
                                    productList = it.data ?: emptyList(),
                                    isLoading = false,
                                    isError = false
                                )
                            }
                        }

                        else -> {
                            _productListState.update { productState ->
                                productState.copy(
                                    productList = emptyList(),
                                    isLoading = false,
                                    isError = true
                                )
                            }
                        }
                    }
                    _isProductListFetched.update {
                        true
                    }
                }
        }
    }

    fun getProduct(
        productId: Int
    ) {
        viewModelScope.launch {
            mainUseCase.getProductUseCase.invoke(productId)
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _productState.update { product ->
                                product.copy(
                                    product = null,
                                    isLoading = true,
                                    isError = false
                                )
                            }
                        }

                        is Resource.Success -> {
                            _productState.update { product ->
                                product.copy(
                                    product = it.data,
                                    isLoading = false,
                                    isError = false
                                )
                            }
                        }

                        else -> {
                            _productState.update { product ->
                                product.copy(
                                    product = null,
                                    isLoading = false,
                                    isError = true
                                )
                            }
                        }
                    }
                }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            mainUseCase.getAllProductCategoriesUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            Log.i("CollectData Resource", "Loading")
                            _productCategoryListState.update { productCategoryState ->
                                productCategoryState.copy(
                                    productCategoryList = it.data ?: emptyList(),
                                    isLoading = true
                                )
                            }
                        }

                        is Resource.Success -> {
                            _productCategoryListState.update { productCategoryState ->
                                productCategoryState.copy(
                                    productCategoryList = it.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                        }

                        else -> {
                            _productCategoryListState.update { productCategoryState ->
                                productCategoryState.copy(
                                    productCategoryList = it.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
        }
    }

    fun getAllProductsOfCategory(
        categoryName: String
    ) {

        viewModelScope.launch {
            mainUseCase.getAllProductOfCategoryUseCase.invoke(categoryName = categoryName)
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _productListOfCategoryState.update { categoryProductListState ->
                                categoryProductListState.copy(
                                    productList = it.data ?: emptyList(),
                                    isLoading = true
                                )
                            }
                        }

                        is Resource.Success -> {
                            _productListOfCategoryState.update { categoryProductListState ->
                                categoryProductListState.copy(
                                    productList = it.data ?: emptyList(),
                                    isLoading = true
                                )
                            }
                        }

                        else -> {
                            _productListOfCategoryState.update { categoryProductListState ->
                                categoryProductListState.copy(
                                    productList = emptyList(),
                                    isLoading = true
                                )
                            }
                        }
                    }
                }
        }
    }
}