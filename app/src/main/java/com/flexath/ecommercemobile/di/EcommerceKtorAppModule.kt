package com.flexath.ecommercemobile.di

import com.flexath.ecommercemobile.data.remote.api.ktor.ProductKtorClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@InstallIn(SingletonComponent::class)
@Module
class EcommerceKtorAppModule {

    @Provides
    fun getHttpClient(httpClient: ProductKtorClient): HttpClient = httpClient.getHttpClient()
}