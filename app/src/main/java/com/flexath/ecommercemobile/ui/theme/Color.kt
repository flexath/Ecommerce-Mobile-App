package com.flexath.currencyapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class AppColors {
    COLOR_PRIMARY,
    COLOR_SECONDARY,
    COLOR_BACKGROUND,
    TEXT_COLOR_PRIMARY,
    TEXT_COLOR_SECONDARY,
    STROKE_COLOR,
    SHADOW_COLOR,
    INFO_BACKGROUND,
    SEARCH_RESULT_BOX,
    POST_DETAIL_STATUS_BACKGROUND,
    SHIMMER_EFFECT,
    RENT_TAG_TEXT_COLOR,
    RENT_TAG_BACKGROUND_COLOR,
    LOADING_TRANSPARENT_BACKGROUND,
    ALERT_COLOR,
}

//// Light Theme Colors
//val LightPrimary = Color(0xFFFF7743)
//val LightSecondary = Color(0xFFFEBB9E)
//val LightBackground = Color(0xFFFEFFFB)
//val LightTextColorPrimary = Color(0xFF484554)
//val LightTextColorSecondary = Color(0xFF787586)
//
//val LightSuccess = Color(0xFF28A745)
//val LightError = Color(0xFFDC3545)
//val LightRating = Color(0xFFE8753D)
//val LightIconColor = Color(0xFF9C9BA1)
//
//val LightDivider = Color(0xFFEBEBEB)
//val LightStroke = Color(0xFFB3B9B3)
//val LightSearchField = Color(0xFFF3F3FC)
//val LightInputBoxStroke = Color(0xFFE4DFDF)
//val LightHint = Color(0xFF747688)
//val LightCardBackground = Color(0xFFFFFFFF)
//
//// Dark Theme Colors
//val DarkPrimary = Color(0xFFFF7743)
//val DarkSecondary = Color(0xFFFEBB9E)
//val DarkBackground = Color(0xFFFEFFFB)
//val DarkTextColorPrimary = Color(0xFF484554)
//val DarkTextColorSecondary = Color(0xFF787586)
//
//val DarkSuccess = Color(0xFF35D259)
//val DarkError = Color(0xFFFF3030)
//val DarkRating = Color(0xFFE16224)
//val DarkIconColor = Color(0xFF8E938E)
//
//val DarkDivider = Color(0xFF2A2C2A)
//val DarkStroke = Color(0xFF393C39)
//val DarkSearchField = Color(0xFF393C39)
//val DarkInputBoxStroke = Color(0xFF393C39)
//val DarkHint = Color(0xFFB3B9B3)
//val DarkCardBackground = Color(0xFF19191A)

// For Light Theme
val colorPrimaryLightMode = Color(0xFFFF7743)
val colorSecondaryLightMode = Color(0xFFFEBB9E)
val colorBackgroundLightMode = Color(0xFFFEFFFB)
val textColorPrimaryLightMode = Color(0xFF0C1A30)
val textColorSecondaryLightMode = Color(0xFF787586)

val strokeColorLightMode = Color(0xFFD0C8C8)
val shadowLightMode = Color(0x14484554)
val infoBackgroundLightMode = Color(0xFFFFF1E9)    // #E6F7E8 for TG and #FFF1E9 for Hope Land
val searchResultBoxLightMode = Color(0xFFF6F7FB)
val postDetailStatusBackgroundLightMode = Color(0xFFFF9268)
val shimmerLightMode = Color(0xFFC3C3C3)
val rentTagBackgroundColorLightMode = Color(0xFFFFD6C4)
val rentTagTextColorLightMode = Color(0xFFDA6D47)
val loadingTransparentBackgroundLightMode = Color(0x77000000)
val alertColorLightMode = Color(0xFFF23330)

// For Dark Theme
val colorPrimaryDarkMode = Color(0xFFFF7743)   // #05B539 for TG and #FF7743 for Hope Land
val colorSecondaryDarkMode = Color(0xFFFEBB9E)   //  #81DA99 for TG and # FEBB9E for Hope Land
val colorOnPrimaryDarkMode = Color(0xFFFF7743)   // #05B539 for TG and #FF7743 for Hope Land

val colorBackgroundDarkMode = Color(0xFFFEFFFB)
val textColorPrimaryDarkMode = Color(0xFF0C1A30)
val textColorSecondaryDarkMode = Color(0xFF787586)

val strokeColorDarkMode = Color(0xFFD0C8C8)
val shadowDarkMode = Color(0x14484554)
val infoBackgroundDarkMode = Color(0xFFFFF1E9)    // #E6F7E8 for TG and #FFF1E9 for Hope Land
val searchResultBoxDarkMode = Color(0xFFF6F7FB)
val postDetailStatusBackgroundDarkMode = Color(0xFFFF9268)
val shimmerDarkMode = Color(0xFFC3C3C3)
val rentTagBackgroundColorDarkMode = Color(0xFFFFD6C4)
val rentTagTextColorDarkMode = Color(0xFFDA6D47)
val loadingTransparentBackgroundDarkMode = Color(0x77000000)
val alertColorDarkMode = Color(0xFFF23330)

@Composable
fun getAppColor(color: AppColors): Color {
    return when(color) {
        AppColors.COLOR_PRIMARY -> if(!isSystemInDarkTheme()) colorPrimaryLightMode else colorPrimaryDarkMode
        AppColors.COLOR_SECONDARY -> if(!isSystemInDarkTheme()) colorSecondaryLightMode else colorSecondaryDarkMode
        AppColors.COLOR_BACKGROUND -> if(!isSystemInDarkTheme()) colorBackgroundLightMode else colorBackgroundDarkMode
        AppColors.TEXT_COLOR_PRIMARY -> if(!isSystemInDarkTheme()) textColorPrimaryLightMode else textColorPrimaryDarkMode
        AppColors.TEXT_COLOR_SECONDARY -> if(!isSystemInDarkTheme()) textColorSecondaryLightMode else textColorSecondaryDarkMode
        AppColors.STROKE_COLOR -> if(!isSystemInDarkTheme()) strokeColorLightMode else strokeColorDarkMode
        AppColors.SHADOW_COLOR -> if(!isSystemInDarkTheme()) shadowLightMode else shadowDarkMode
        AppColors.INFO_BACKGROUND -> if(!isSystemInDarkTheme()) infoBackgroundLightMode else infoBackgroundDarkMode
        AppColors.SEARCH_RESULT_BOX -> if(!isSystemInDarkTheme()) searchResultBoxLightMode else searchResultBoxDarkMode
        AppColors.POST_DETAIL_STATUS_BACKGROUND -> if(!isSystemInDarkTheme()) postDetailStatusBackgroundLightMode else postDetailStatusBackgroundDarkMode
        AppColors.SHIMMER_EFFECT -> if(!isSystemInDarkTheme()) shimmerLightMode else shimmerDarkMode
        AppColors.RENT_TAG_BACKGROUND_COLOR -> if(!isSystemInDarkTheme()) rentTagBackgroundColorLightMode else rentTagBackgroundColorDarkMode
        AppColors.RENT_TAG_TEXT_COLOR -> if(!isSystemInDarkTheme()) rentTagTextColorLightMode else rentTagTextColorDarkMode
        AppColors.LOADING_TRANSPARENT_BACKGROUND -> if(!isSystemInDarkTheme()) loadingTransparentBackgroundLightMode else loadingTransparentBackgroundDarkMode
        AppColors.ALERT_COLOR -> if(!isSystemInDarkTheme()) alertColorLightMode else alertColorDarkMode
    }
}