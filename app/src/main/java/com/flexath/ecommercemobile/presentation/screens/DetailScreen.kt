package com.flexath.ecommercemobile.presentation.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.flexath.currencyapp.ui.theme.AppColors
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.getAppColor
import com.flexath.ecommercemobile.R
import com.flexath.ecommercemobile.presentation.constants.productColors
import com.flexath.ecommercemobile.presentation.constants.productSizes
import com.flexath.ecommercemobile.presentation.extensions.enterPop
import com.flexath.ecommercemobile.presentation.extensions.enterPush
import com.flexath.ecommercemobile.presentation.extensions.exitPop
import com.flexath.ecommercemobile.presentation.extensions.exitPush
import com.flexath.ecommercemobile.presentation.navigation.Screen
import com.flexath.ecommercemobile.presentation.screens.components.CustomFilledButton
import com.flexath.ecommercemobile.presentation.screens.components.ScrollablePageIndicator
import com.flexath.ecommercemobile.presentation.viewmodels.MainViewModel
import com.flexath.ecommercemobile.ui.theme.dimens
import kotlinx.coroutines.launch

fun NavGraphBuilder.detailScreen(
    modifier: Modifier = Modifier,
    context: Context,
    dimens: Dimensions,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable<Screen.Detail>(
        enterTransition = enterPush,
        exitTransition = exitPush,
        popEnterTransition = enterPop,
        popExitTransition = exitPop
    ) {
        val navArgs = it.toRoute<Screen.Detail>()
        val productId = navArgs.productId

        DetailScreen(
            modifier = modifier,
            dimens = dimens,
            context = context,
            productId = productId,
            onClickBackButton = {
                navController.popBackStack()
            },
            onClickFavoriteButton = {

            },
            onNavigate = {
                navController.popBackStack()
            }
        )
    }
}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    context: Context,
    productId: Int = 0,
    onClickBackButton: () -> Unit = {},
    onClickFavoriteButton: () -> Unit = {},
    onNavigate: () -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage = 0) {
        3 ?: 0
    }

    val coroutineScope = rememberCoroutineScope()

    var selectedSizeIndex by rememberSaveable {
        mutableStateOf(0)
    }

    var selectedColorIndex by rememberSaveable {
        mutableStateOf(0)
    }

    var quantity by rememberSaveable {
        mutableStateOf(0)
    }

    var descriptionVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    var freeDeliveryVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            Surface(
                tonalElevation = dimens.smallPadding2,
                color = getAppColor(color = AppColors.COLOR_BACKGROUND),
                shadowElevation = dimens.smallPadding2,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomFilledButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimens.mediumPadding3),
                    dimens = dimens,
                    buttonText = "Add to Card",
                    onClick = {

                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()

        LazyColumn(
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            item {
                Box {
                    HorizontalPager(state = pagerState) { page ->
//                        SubcomposeAsyncImage(
//                            model = ImageRequest.Builder(context)
//                                .crossfade(true)
//                                .placeholder(R.drawable.img_hl_default_landscape)
//                                .data(currentPhoto.medium).build(),
//                            contentDescription = "Post Image",
//                            loading = {
//                                CircularProgressIndicator(
//                                    color = getAppColor(color = AppColors.COLOR_PRIMARY),
//                                    strokeWidth = dimens.smallPadding4,
//                                    modifier = Modifier.scale(0.1f)
//                                )
//                            },
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier
//                                .aspectRatio(376f / 348f)
//                                .clickable {
//                                    onClickImage(page)
//                                }
//                        )

                        Image(
                            painter = painterResource(id = R.drawable.splash2),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .aspectRatio(376f / 348f)
                        )

                    }

                    Surface(
                        shape = CircleShape,
                        tonalElevation = dimens.smallPadding4,
                        color = getAppColor(color = AppColors.COLOR_BACKGROUND),
                        shadowElevation = dimens.smallPadding4,
                        onClick = {
                            onClickBackButton()
                        },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(
                                start = dimens.mediumPadding3,
                                top = dimens.doubleExtraLargePadding2
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back Button",
                            tint = getAppColor(color = AppColors.TEXT_COLOR_PRIMARY),
                            modifier = Modifier.padding(dimens.smallPadding4)
                        )
                    }

                    Surface(
                        shape = CircleShape,
                        tonalElevation = dimens.smallPadding4,
                        color = getAppColor(color = AppColors.COLOR_BACKGROUND),
                        shadowElevation = dimens.smallPadding4,
                        onClick = {
                            onClickFavoriteButton()
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(
                                end = dimens.mediumPadding3,
                                top = dimens.doubleExtraLargePadding2
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite Button",
                            tint = getAppColor(color = AppColors.COLOR_PRIMARY),
                            modifier = Modifier.padding(dimens.smallPadding4)
                        )
                    }

                    ScrollablePageIndicator(
                        dimens = dimens,
                        pageSize = 3,
                        selectedPage = pagerState.currentPage,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .width(dimens.extraLargePadding5_2x + dimens.mediumPadding3)
                            .padding(dimens.mediumPadding1),
                        onClickDot = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(it)
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(dimens.mediumPadding3))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimens.mediumPadding3)
                ) {
                    Text(
                        text = "Men's shoes",
                        style = MaterialTheme.typography.titleSmall,
                        color = getAppColor(color = AppColors.TEXT_COLOR_SECONDARY),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(dimens.smallPadding4))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = getAppColor(color = AppColors.COLOR_PRIMARY),
                        )

                        Spacer(modifier = Modifier.width(dimens.smallPadding1))

                        Text(
                            text = "4.7",
                            style = MaterialTheme.typography.titleSmall,
                            color = getAppColor(color = AppColors.TEXT_COLOR_SECONDARY),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dimens.smallPadding4))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimens.mediumPadding3)
                ) {
                    Text(
                        text = "Nike Air Max 27",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = getAppColor(color = AppColors.TEXT_COLOR_PRIMARY),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(dimens.smallPadding4))

                    Text(
                        text = "$290",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = getAppColor(color = AppColors.TEXT_COLOR_PRIMARY),
                    )
                }

                Spacer(modifier = Modifier.height(dimens.mediumPadding3))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimens.mediumPadding3)
                ) {
                    Text(
                        text = "Size",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = getAppColor(color = AppColors.TEXT_COLOR_PRIMARY),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(dimens.smallPadding4))

                    Text(
                        text = "US UK EU",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                        ),
                        color = getAppColor(color = AppColors.TEXT_COLOR_SECONDARY),
                    )
                }

                Spacer(modifier = Modifier.height(dimens.mediumPadding3))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectableGroup()
                ) {
                    items(count = productSizes.size) { index ->
                        val productSize = productSizes[index]

                        if (index == 0) {
                            Spacer(modifier = Modifier.width(dimens.smallPadding4))
                        }

                        SizeCard(
                            modifier = Modifier
                                .padding(horizontal = dimens.smallPadding4)
                                .clip(RoundedCornerShape(dimens.smallPadding4))
                                .selectable(
                                    selected = selectedSizeIndex == index,
                                    onClick = {
                                        selectedSizeIndex = index
                                    }
                                ),
                            isSelected = selectedSizeIndex == index,
                            context = context,
                            size = productSize,
                            dimens = dimens
                        )

                        if (index == 9) {
                            Spacer(modifier = Modifier.width(dimens.smallPadding4))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dimens.mediumPadding3))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimens.mediumPadding3)
                ) {
                    Text(
                        text = stringResource(R.string.lbl_description),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = getAppColor(color = AppColors.TEXT_COLOR_PRIMARY),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(dimens.smallPadding4))

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = getAppColor(color = AppColors.TEXT_COLOR_SECONDARY),
                        modifier = Modifier.clickable {
                            descriptionVisibility = !descriptionVisibility
                        }
                    )
                }

                AnimatedVisibility(
                    visible = descriptionVisibility,
                    enter = fadeIn(
                        animationSpec = tween(200)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(200)
                    )
                ) {
                    Spacer(modifier = Modifier.height(dimens.mediumPadding1))

                    Text(
                        text = "The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks. With a built-in mirror, it's convenient for on-the-go makeup application.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = getAppColor(color = AppColors.TEXT_COLOR_SECONDARY),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimens.mediumPadding3)
                    )
                }

                HorizontalDivider(
                    modifier = Modifier
                        .padding(dimens.mediumPadding3)
                        .fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimens.mediumPadding3)
                ) {
                    Text(
                        text = stringResource(R.string.lbl_free_delivery_and_returns),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = getAppColor(color = AppColors.TEXT_COLOR_PRIMARY),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(dimens.smallPadding4))

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = getAppColor(color = AppColors.TEXT_COLOR_SECONDARY),
                        modifier = Modifier.clickable {
                            freeDeliveryVisibility = !freeDeliveryVisibility
                        }
                    )
                }

                AnimatedVisibility(
                    visible = freeDeliveryVisibility,
                    enter = fadeIn(
                        animationSpec = tween(200)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(200)
                    )
                ) {
                    Spacer(modifier = Modifier.height(dimens.mediumPadding1))

                    Text(
                        text = "The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks. With a built-in mirror, it's convenient for on-the-go makeup application.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = getAppColor(color = AppColors.TEXT_COLOR_SECONDARY),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimens.mediumPadding3)
                    )
                }

                HorizontalDivider(
                    modifier = Modifier
                        .padding(dimens.mediumPadding3)
                        .fillMaxWidth()
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectableGroup()
                ) {
                    items(productColors.size) { index ->
                        val color = productColors[index]
                        if (index == 0) {
                            Spacer(modifier = Modifier.width(dimens.smallPadding4))
                        }

                        ColorCircle(
                            modifier = Modifier
                                .padding(horizontal = dimens.smallPadding4)
                                .clip(CircleShape)
                                .selectable(
                                    selected = selectedColorIndex == index,
                                    onClick = {
                                        selectedColorIndex = index
                                    }
                                ),
                            dimens = dimens,
                            isSelected = selectedColorIndex == index,
                            color = color
                        )

                        if (index == 9) {
                            Spacer(modifier = Modifier.width(dimens.smallPadding4))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dimens.largePadding2))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimens.mediumPadding3)
                ) {
                    Text(
                        text = "Quantity",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = getAppColor(color = AppColors.TEXT_COLOR_PRIMARY),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(dimens.smallPadding4))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                       Icon(
                           imageVector = Icons.Default.Remove,
                           contentDescription = null,
                           tint = getAppColor(color = AppColors.TEXT_COLOR_PRIMARY),
                           modifier = Modifier
                               .clip(CircleShape)
                               .border(
                                   width = dimens.smallPadding0,
                                   color = getAppColor(color = AppColors.STROKE_COLOR),
                                   shape = CircleShape
                               )
                               .background(getAppColor(color = AppColors.COLOR_BACKGROUND))
                               .clickable {
                                   --quantity
                               }
                               .padding(dimens.smallPadding2)
                       )

                        Spacer(modifier = Modifier.width(dimens.smallPadding4))

                        Text(
                            text = if(quantity <= 0) {
                                quantity = 0
                                quantity.toString()
                            } else {
                                quantity.toString()
                            },
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = getAppColor(color = AppColors.TEXT_COLOR_PRIMARY),
                        )

                        Spacer(modifier = Modifier.width(dimens.smallPadding4))

                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = getAppColor(color = AppColors.TEXT_COLOR_PRIMARY),
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(
                                    width = dimens.smallPadding0,
                                    color = getAppColor(color = AppColors.STROKE_COLOR),
                                    shape = CircleShape
                                )
                                .background(getAppColor(color = AppColors.COLOR_BACKGROUND))
                                .clickable {
                                    ++quantity
                                }
                                .padding(dimens.smallPadding2)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dimens.smallPadding4))
            }

        }
    }
}

@Composable
fun SizeCard(
    modifier: Modifier = Modifier,
    context: Context,
    dimens: Dimensions,
    size: Double,
    isSelected: Boolean = false
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(dimens.smallPadding4))
            .background(
                color = if (isSelected) getAppColor(color = AppColors.COLOR_PRIMARY)
                else getAppColor(color = AppColors.SEARCH_RESULT_BOX)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = size.toString(),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Medium
            ),
            color = getAppColor(color = AppColors.TEXT_COLOR_PRIMARY),
            modifier = Modifier
                .padding(vertical = dimens.smallPadding4, horizontal = dimens.mediumPadding3)
        )
    }
}

@Composable
fun ColorCircle(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    color: Color,
    isSelected: Boolean = false
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(dimens.largePadding5)
            .clip(CircleShape)
            .background(color = color)
            .padding(dimens.smallPadding2)
    ) {
        if(isSelected) {
            Image(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                colorFilter = ColorFilter.tint(getAppColor(AppColors.ALERT_COLOR)),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailScreenPreview() {

    DetailScreen(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        dimens = MaterialTheme.dimens,
        context = LocalContext.current
    )

}