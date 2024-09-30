package com.flexath.ecommercemobile.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Splash: Screen()

    @Serializable
    data object Home: Screen()

    @Serializable
    data class Detail(
        val productId: Int
    ): Screen()
}