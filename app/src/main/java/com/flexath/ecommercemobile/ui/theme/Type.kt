package com.flexath.ecommercemobile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexath.currencyapp.ui.theme.largeCompatDimensions
import com.flexath.currencyapp.ui.theme.mediumCompatDimensions
import com.flexath.currencyapp.ui.theme.smallCompatDimensions
import com.flexath.ecommercemobile.R

val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold)
)

val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

enum class CustomFont {
    Inter,
    Poppins
}

fun getFont(font: CustomFont): FontFamily {
    return when (font) {
        CustomFont.Inter -> Inter
        CustomFont.Poppins -> Poppins
    }
}

@Immutable
data class EcommerceTypography(
    val displayLarge: TextStyle = TextStyle.Default,
    val displayMedium: TextStyle = TextStyle.Default,
    val displaySmall: TextStyle = TextStyle.Default,
    val headlineLarge: TextStyle = TextStyle.Default,
    val headlineMedium: TextStyle = TextStyle.Default,
    val headlineSmall: TextStyle = TextStyle.Default,
    val titleLarge: TextStyle = TextStyle.Default,
    val titleMedium: TextStyle = TextStyle.Default,
    val titleSmall: TextStyle = TextStyle.Default,
    val bodyLarge: TextStyle = TextStyle.Default,
    val bodyMedium: TextStyle = TextStyle.Default,
    val bodySmall: TextStyle = TextStyle.Default,
    val labelMedium: TextStyle = TextStyle.Default,
    val labelSmall: TextStyle = TextStyle.Default
)

fun getMediumTypography(font: CustomFont = CustomFont.Poppins): Typography {
    return Typography(
        displayLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = mediumCompatDimensions.largeText10,
            lineHeight = mediumCompatDimensions.largeText10,
            letterSpacing = 4.sp
        ),
        displayMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = mediumCompatDimensions.largeText8,
            lineHeight = mediumCompatDimensions.largeText9,
            letterSpacing = 0.2.sp
        ),
        displaySmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = mediumCompatDimensions.largeText7,
            lineHeight = mediumCompatDimensions.largeText8,
            letterSpacing = 0.2.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = mediumCompatDimensions.largeText6,
            lineHeight = mediumCompatDimensions.largeText9,
            letterSpacing = 0.2.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = mediumCompatDimensions.largeText5,
            lineHeight = mediumCompatDimensions.largeText8,
        ),
        headlineSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = mediumCompatDimensions.largeText4,
            lineHeight = mediumCompatDimensions.largeText7,
        ),
        titleLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = mediumCompatDimensions.largeText2,
            lineHeight = mediumCompatDimensions.largeText6,
            letterSpacing = 0.2.sp
        ),
        titleMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = mediumCompatDimensions.largeText1,
            lineHeight = mediumCompatDimensions.largeText5,
            letterSpacing = 0.2.sp
        ),
        titleSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = mediumCompatDimensions.mediumText5,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText4,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText3,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        bodySmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText3,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        labelMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText2,
            lineHeight = mediumCompatDimensions.mediumText3,
            letterSpacing = 0.2.sp
        ),
        labelSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText1,
            lineHeight = mediumCompatDimensions.mediumText3,
            letterSpacing = 0.2.sp
        )
    )
}

fun getSmallCompatTypography(font: CustomFont = CustomFont.Poppins): Typography {
    return Typography(
        displayLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = smallCompatDimensions.largeText10,
            lineHeight = smallCompatDimensions.largeText10
        ),
        displayMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = smallCompatDimensions.largeText8,
            lineHeight = smallCompatDimensions.largeText9
        ),
        displaySmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = smallCompatDimensions.largeText7,
            lineHeight = smallCompatDimensions.largeText8
        ),
        headlineLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = smallCompatDimensions.largeText6,
            lineHeight = smallCompatDimensions.largeText9,
            letterSpacing = 0.2.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = smallCompatDimensions.largeText5,
            lineHeight = smallCompatDimensions.largeText8,
        ),
        headlineSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = smallCompatDimensions.largeText4,
            lineHeight = smallCompatDimensions.largeText7,
        ),
        titleLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = smallCompatDimensions.largeText2,
            lineHeight = smallCompatDimensions.largeText6,
            letterSpacing = 0.2.sp
        ),
        titleMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = smallCompatDimensions.largeText1,
            lineHeight = smallCompatDimensions.largeText5,
            letterSpacing = 0.2.sp
        ),
        titleSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = smallCompatDimensions.mediumText5,
            lineHeight = smallCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = smallCompatDimensions.mediumText4,
            lineHeight = smallCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = smallCompatDimensions.mediumText3,
            lineHeight = smallCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        bodySmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = smallCompatDimensions.mediumText3,
            lineHeight = smallCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        labelMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = smallCompatDimensions.mediumText2,
            lineHeight = smallCompatDimensions.mediumText3,
            letterSpacing = 0.2.sp
        ),
        labelSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = smallCompatDimensions.mediumText1,
            lineHeight = smallCompatDimensions.mediumText3,
            letterSpacing = 0.2.sp
        )
    )
}

fun getLargeCompatTypography(font: CustomFont = CustomFont.Poppins): Typography {
    return Typography(
        displayLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = largeCompatDimensions.largeText10,
            lineHeight = largeCompatDimensions.largeText10
        ),
        displayMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = largeCompatDimensions.largeText8,
            lineHeight = largeCompatDimensions.largeText9
        ),
        displaySmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = largeCompatDimensions.largeText7,
            lineHeight = largeCompatDimensions.largeText8
        ),
        headlineLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = largeCompatDimensions.largeText6,
            lineHeight = largeCompatDimensions.largeText9,
            letterSpacing = 0.2.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = largeCompatDimensions.largeText5,
            lineHeight = largeCompatDimensions.largeText8,
        ),
        headlineSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.SemiBold,
            fontSize = largeCompatDimensions.largeText4,
            lineHeight = largeCompatDimensions.largeText7,
        ),
        titleLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = largeCompatDimensions.largeText2,
            lineHeight = largeCompatDimensions.largeText6,
            letterSpacing = 0.2.sp
        ),
        titleMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = largeCompatDimensions.largeText1,
            lineHeight = largeCompatDimensions.largeText5,
            letterSpacing = 0.2.sp
        ),
        titleSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = largeCompatDimensions.mediumText5,
            lineHeight = largeCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = largeCompatDimensions.mediumText4,
            lineHeight = largeCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = largeCompatDimensions.mediumText3,
            lineHeight = largeCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        bodySmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = largeCompatDimensions.mediumText3,
            lineHeight = largeCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        labelMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = largeCompatDimensions.mediumText2,
            lineHeight = largeCompatDimensions.mediumText3,
            letterSpacing = 0.2.sp
        ),
        labelSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = largeCompatDimensions.mediumText1,
            lineHeight = largeCompatDimensions.mediumText3,
            letterSpacing = 0.2.sp
        )
    )
}

fun getResponsiveTypography(screenWidthDp: Dp, font: CustomFont): Typography {
    return when {
        screenWidthDp < 360.dp -> getSmallCompatTypography(font)
        screenWidthDp < 600.dp -> getMediumTypography(font)
        else -> getLargeCompatTypography(font)
    }
}