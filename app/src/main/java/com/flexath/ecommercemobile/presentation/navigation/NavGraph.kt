package com.flexath.ecommercemobile.presentation.navigation

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.flexath.ecommercemobile.presentation.screens.detailScreen
import com.flexath.ecommercemobile.presentation.screens.homeScreen
import com.flexath.ecommercemobile.presentation.screens.splashScreen
import com.flexath.ecommercemobile.presentation.viewmodels.ProductViewModel
import com.flexath.ecommercemobile.ui.theme.dimens

@Composable
fun SetUpNavGraph(
    modifier: Modifier = Modifier,
    productViewModel: ProductViewModel,
    startDestination: Screen,
    navController: NavHostController
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val dimens = MaterialTheme.dimens
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier,
        contentWindowInsets = WindowInsets.navigationBars,
    ) {
        val bottomPadding = it.calculateBottomPadding()
        val topPadding = it.calculateTopPadding()

        NavHost(
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
        ) {
            Log.i("NavGraph", "SetUpNavGraph: ${navBackStackEntry?.destination?.route}")

            splashScreen(
                modifier = modifier.navigationBarsPadding(),
                dimens = dimens,
                navController = navController
            )

            homeScreen(
                modifier = modifier
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                context = context,
                dimens = dimens,
                productViewModel = productViewModel,
                navController = navController
            )

            detailScreen(
                modifier = modifier.navigationBarsPadding(),
                context = context,
                dimens = dimens,
                productViewModel = productViewModel,
                navController = navController
            )
        }
    }
}