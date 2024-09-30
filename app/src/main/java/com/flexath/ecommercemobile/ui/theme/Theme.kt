package com.flexath.ecommercemobile.ui.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.flexath.currencyapp.ui.theme.colorBackgroundDarkMode
import com.flexath.currencyapp.ui.theme.colorBackgroundLightMode
import com.flexath.currencyapp.ui.theme.colorPrimaryDarkMode
import com.flexath.currencyapp.ui.theme.colorPrimaryLightMode
import com.flexath.currencyapp.ui.theme.colorSecondaryDarkMode
import com.flexath.currencyapp.ui.theme.colorSecondaryLightMode
import com.flexath.currencyapp.ui.theme.largeCompatDimensions
import com.flexath.currencyapp.ui.theme.mediumCompatDimensions
import com.flexath.currencyapp.ui.theme.smallCompatDimensions
import com.flexath.ecommercemobile.MainActivity

private val DarkColorScheme = darkColorScheme(
    primary = colorPrimaryDarkMode,
    secondary = colorSecondaryDarkMode,
    background = colorBackgroundDarkMode
)

private val LightColorScheme = lightColorScheme(
    primary = colorPrimaryLightMode,
    secondary = colorSecondaryLightMode,
    background = colorBackgroundLightMode

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun EcommerceMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    activity: Activity = LocalContext.current as MainActivity,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val windowSize = calculateWindowSizeClass(activity = activity)
    val config = LocalConfiguration.current

    var appDimens = mediumCompatDimensions
    var typography = getMediumTypography()

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (config.screenWidthDp <= 360) {
                Log.i("Dimens", "Compact: ${config.screenWidthDp}")
                appDimens = smallCompatDimensions
                typography = getSmallCompatTypography()
            } else if (config.screenWidthDp < 599) {
                Log.i("Dimens", "Compact Medium: ${config.screenWidthDp}")
                appDimens = mediumCompatDimensions
                typography = getMediumTypography()
            } else {
                Log.i("Dimens", "Compact Large: ${config.screenWidthDp}")
                appDimens = largeCompatDimensions
                typography = getLargeCompatTypography()
            }
        }
    }

//        WindowWidthSizeClass.Medium -> {
//            appDimens = MediumDimens
//            typography = CompactTypography
//        }
//
//        WindowWidthSizeClass.Expanded -> {
//            appDimens = ExpandedDimens
//            typography = ExpandedTypography
//        }
//
//        else -> {
//            appDimens = ExpandedDimens
//            typography = ExpandedTypography
//        }

    ProvideAppThemeUtils(
        appDimens = appDimens,
        typography = typography
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}

val MaterialTheme.dimens
    @Composable
    get() = LocalAppDimens.current