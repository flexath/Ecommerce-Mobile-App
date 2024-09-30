package com.flexath.ecommercemobile.presentation.screens

import android.content.Context
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.flexath.currencyapp.ui.theme.AppColors
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.getAppColor
import com.flexath.ecommercemobile.R
import com.flexath.ecommercemobile.presentation.extensions.enterPop
import com.flexath.ecommercemobile.presentation.extensions.exitPop
import com.flexath.ecommercemobile.presentation.extensions.exitPush
import com.flexath.ecommercemobile.presentation.navigation.Screen
import com.flexath.ecommercemobile.presentation.screens.components.CategoryCard
import com.flexath.ecommercemobile.presentation.screens.components.SearchBasicTextField
import com.flexath.ecommercemobile.presentation.screens.components.productCardList
import com.flexath.ecommercemobile.presentation.viewmodels.MainViewModel
import com.flexath.ecommercemobile.ui.theme.dimens

fun NavGraphBuilder.homeScreen(
    modifier: Modifier = Modifier,
    context: Context,
    dimens: Dimensions,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable<Screen.Home>(
        exitTransition = exitPush,
        popEnterTransition = enterPop,
        popExitTransition = exitPop
    ) {
        HomeScreen(
            modifier = modifier,
            dimens = dimens,
            context = context,
            onNavigate = {
                navController.navigate(
                    Screen.Detail(
                        productId = 1
                    )
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    context: Context,
    onNavigate: () -> Unit = {},
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        snapAnimationSpec = tween(200)
    )

    val textFieldState = rememberTextFieldState()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = getAppColor(AppColors.COLOR_BACKGROUND),
                    scrolledContainerColor = getAppColor(AppColors.COLOR_BACKGROUND)
                ),
                title = {
                    Text(text = "Home")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = getAppColor(AppColors.TEXT_COLOR_PRIMARY)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            tint = getAppColor(AppColors.TEXT_COLOR_PRIMARY)
                        )
                    }
                }
            )
        }
    ) {
        val topPadding = it.calculateTopPadding()

        LazyColumn(
            modifier = Modifier.padding(top = topPadding)
        ) {
            item {

                SearchBasicTextField(
                    modifier = Modifier
                        .padding(dimens.mediumPadding3)
                        .fillMaxWidth(),
                    textFieldState = textFieldState,
                    hint = "Search",
                    leadingIcon = R.drawable.ic_search,
                    trailingIcon = null,
                    onClickTrailingIcon = {

                    }
                )

                Spacer(modifier = Modifier.height(dimens.smallPadding4))

                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(count = 10) { index ->

                        if(index == 0) {
                            Spacer(modifier = Modifier.width(dimens.smallPadding4))
                        }

                        CategoryCard(
                            modifier = Modifier.padding(horizontal = dimens.smallPadding4),
                            context = context,
                            dimens = dimens
                        )

                        if(index == 9) {
                            Spacer(modifier = Modifier.width(dimens.smallPadding4))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dimens.mediumPadding3))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimens.mediumPadding3)
                ) {
                    Text(
                        text = stringResource(R.string.lbl_popular),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = getAppColor(AppColors.TEXT_COLOR_PRIMARY)
                    )

                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = null,
                        tint = getAppColor(AppColors.TEXT_COLOR_PRIMARY),
                        modifier = Modifier
                            .clip(CircleShape)
                            .border(
                                dimens.smallPadding0,
                                getAppColor(AppColors.STROKE_COLOR),
                                CircleShape
                            )
                            .padding(dimens.smallPadding2)
                            .clickable {

                            }
                    )
                }

                Spacer(modifier = Modifier.height(dimens.smallPadding4))
            }

            productCardList(
                modifier = Modifier.padding(horizontal = dimens.mediumPadding3).fillMaxWidth(),
                dimens = dimens,
                context = context,
                onNavigate = onNavigate
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        dimens = MaterialTheme.dimens,
        context = LocalContext.current
    )

}