package com.flexath.ecommercemobile.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.flexath.currencyapp.ui.theme.AppColors
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.getAppColor
import com.flexath.ecommercemobile.R
import com.flexath.ecommercemobile.presentation.navigation.Screen
import com.flexath.ecommercemobile.presentation.screens.components.CustomFilledButton
import com.flexath.ecommercemobile.ui.theme.CustomFont
import com.flexath.ecommercemobile.ui.theme.dimens
import com.flexath.ecommercemobile.ui.theme.getFont

fun NavGraphBuilder.splashScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    navController: NavHostController
) {
    composable<Screen.Splash>(

    ) {
        SplashScreen(
            modifier = modifier,
            dimens = dimens,
            onNavigate = {
                navController.navigate(Screen.Home)
            }
        )
    }
}

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    onNavigate: () -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.splash1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = dimens.mediumPadding3)
        ) {
            Text(
                text = "Live Your\nPerfect",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = getFont(CustomFont.Inter)
                ),
                textAlign = TextAlign.Center,
                color = getAppColor(AppColors.COLOR_BACKGROUND),
                modifier = Modifier
                    .padding(horizontal = dimens.mediumPadding3)
            )

            Spacer(modifier = Modifier.height(dimens.largePadding2))

            Text(
                text = "Smart, gorgeous & fashionable\ncollection makes you cool",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = getFont(CustomFont.Inter)
                ),
                color = getAppColor(AppColors.COLOR_BACKGROUND),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = dimens.mediumPadding3)
            )

            Spacer(modifier = Modifier.height(dimens.largePadding10))

            CustomFilledButton(
                modifier = Modifier
                    .padding(dimens.mediumPadding3)
                    .fillMaxWidth(),
                dimens = dimens,
                buttonText = "Start",
                onClick = {
                    onNavigate()
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen(
        dimens = MaterialTheme.dimens
    )
}