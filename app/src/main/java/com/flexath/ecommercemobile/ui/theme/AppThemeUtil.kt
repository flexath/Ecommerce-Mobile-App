package com.flexath.ecommercemobile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.EcommerceColorScheme
import com.flexath.currencyapp.ui.theme.mediumCompatDimensions

@Composable
fun ProvideAppThemeUtils(
    appDimens: Dimensions,
    typography: Typography,
    content: @Composable () -> Unit,
) {
    val appDimensions = remember {
        appDimens
    }

    CompositionLocalProvider(LocalAppDimens provides appDimensions) {
        content()
    }
}

val LocalAppDimens = compositionLocalOf {
    mediumCompatDimensions
}

val ScreenOrientation
    @Composable
    get() = LocalConfiguration.current.orientation