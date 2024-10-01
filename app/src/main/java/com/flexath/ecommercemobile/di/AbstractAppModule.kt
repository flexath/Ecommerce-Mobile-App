package com.flexath.ecommercemobile.di

import com.flexath.ecommercemobile.data.repository.ProductRepositoryImpl
import com.flexath.ecommercemobile.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractAppModule {
    @Binds
    abstract fun provideProductRepository(repositoryImpl: ProductRepositoryImpl): ProductRepository
}