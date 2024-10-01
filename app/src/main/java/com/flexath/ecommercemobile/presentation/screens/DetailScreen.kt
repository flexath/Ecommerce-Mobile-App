package com.flexath.ecommercemobile.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
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
import com.flexath.ecommercemobile.presentation.screens.components.AddToCardDialog
import com.flexath.ecommercemobile.presentation.screens.components.CustomFilledButton
import com.flexath.ecommercemobile.presentation.screens.components.PageIndicator
import com.flexath.ecommercemobile.presentation.states.ProductState
import com.flexath.ecommercemobile.presentation.viewmodels.ProductViewModel
import com.flexath.ecommercemobile.ui.theme.dimens
import kotlinx.coroutines.launch

fun NavGraphBuilder.detailScreen(
    modifier: Modifier = Modifier,
    context: Context,
    dimens: Dimensions,
    productViewModel: ProductViewModel,
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

        LaunchedEffect(key1 = productId) {
            productViewModel.getProduct(productId)
        }

        val productState = productViewModel.productState.collectAsStateWithLifecycle()

        Box(modifier = Modifier.fillMaxSize()) {
            DetailScreen(
                modifier = modifier,
                dimens = dimens,
                context = context,
                productState = productState.value,
                onClickBackButton = {
                    navController.popBackStack()
                },
                onClickFavoriteButton = {

                },
                onNavigate = {
                    navController.popBackStack()
                }
            )

            // Loading overlay
            if (productState.value.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White.copy(alpha = 0.3f))
                        .clickable {},
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        strokeWidth = dimens.smallPadding1
                    )
                }
            } else if (productState.value.isError) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White.copy(alpha = 0.3f))
                        .clickable {

                        },
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        strokeWidth = dimens.smallPadding1
                    )
                }
            }
        }
    }
}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    context: Context,
    productState: ProductState,
    onClickBackButton: () -> Unit = {},
    onClickFavoriteButton: () -> Unit = {},
    onNavigate: () -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage = 0) {
        productState.product?.images?.size ?: 0
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
    
    var currentColor by remember {
        mutableStateOf(Color(0xFFFF7743))
    }

    var descriptionVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    var freeDeliveryVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    var isClickOnFavorite by rememberSaveable {
        mutableStateOf(false)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    /** These input values to this dialog should be from view model,
    not from screen ( Cannot do further more due to time constraint
    and due to electricity will cut off at 9am ) **/
    AddToCardDialog(
        modifier = Modifier,
        dimens = dimens,
        showDialog = showDialog,
        product = productState.product ?: return,
        size = productSizes[selectedSizeIndex],
        color = productColors[selectedColorIndex],
        quantity = quantity,
        onDismiss = {
            showDialog = it
        },
        onNavigate = { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            onNavigate()
        }
    )

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
                    containerColor = currentColor,
                    buttonText = stringResource(R.string.lbl_add_to_card),
                    onClick = {
                        showDialog = true
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(376f / 348f)
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxWidth()
                    ) { page ->
                        val photo = productState.product?.images?.get(page)
                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(context)
                                .crossfade(true)
                                .data(photo.orEmpty()).build(),
                            contentDescription = "Product Image",
                            loading = {
                                CircularProgressIndicator(
                                    color = getAppColor(color = AppColors.COLOR_PRIMARY),
                                    strokeWidth = dimens.smallPadding4,
                                    modifier = Modifier.scale(0.1f)
                                )
                            },
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .aspectRatio(376f / 348f)
                                .nestedScroll(rememberNestedScrollInteropConnection())
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
                            isClickOnFavorite = !isClickOnFavorite
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
                            imageVector = if(isClickOnFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite Button",
                            tint = currentColor,
                            modifier = Modifier.padding(dimens.smallPadding4)
                        )
                    }
                    
                    if((productState.product?.images?.size ?: 0) > 1) {
                        PageIndicator(
                            dimens = dimens,
                            pageSize = productState.product?.images?.size ?: 0,
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
                }

                Spacer(modifier = Modifier.height(dimens.mediumPadding3))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimens.mediumPadding3)
                ) {
                    Text(
                        text = productState.product?.brand.orEmpty(),
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
                            tint = currentColor,
                        )

                        Spacer(modifier = Modifier.width(dimens.smallPadding1))

                        Text(
                            text = (productState.product?.rating ?: 0.0).toString(),
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
                        text = productState.product?.title.orEmpty(),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = getAppColor(color = AppColors.TEXT_COLOR_PRIMARY),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(dimens.smallPadding4))

                    Text(
                        text = "$${productState.product?.price ?: 0}",
                        style = MaterialTheme.typography.titleMedium.copy(
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
                            selectedColor = currentColor,
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
                        .clickable {
                            descriptionVisibility = !descriptionVisibility
                        }
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
                        animationSpec = tween(300)
                    )
                ) {
                    Spacer(modifier = Modifier.height(dimens.mediumPadding1))

                    Text(
                        text = productState.product?.description.orEmpty(),
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
                        .clickable {
                            freeDeliveryVisibility = !freeDeliveryVisibility
                        }
                        .padding(horizontal = dimens.mediumPadding3)
                ) {
                    Text(
                        text = stringResource(R.string.lbl_other_information),
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
                            freeDeliveryVisibility = !freeDeliveryVisibility
                        }
                    )
                }

                AnimatedVisibility(
                    visible = freeDeliveryVisibility,
                    enter = fadeIn(
                        animationSpec = tween(200)
                    )
                ) {
                    Spacer(modifier = Modifier.height(dimens.mediumPadding1))

                    Column {
                        Text(
                            text = "Status - ${productState.product?.availabilityStatus}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = getAppColor(color = AppColors.TEXT_COLOR_SECONDARY),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = dimens.mediumPadding3)
                        )

                        Spacer(modifier = Modifier.height(dimens.smallPadding4))

                        Text(
                            text = "Shipping Information - ${productState.product?.shippingInformation}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = getAppColor(color = AppColors.TEXT_COLOR_SECONDARY),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = dimens.mediumPadding3)
                        )

                        Spacer(modifier = Modifier.height(dimens.smallPadding4))

                        Text(
                            text = "Warranty Information - ${productState.product?.warrantyInformation}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = getAppColor(color = AppColors.TEXT_COLOR_SECONDARY),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = dimens.mediumPadding3)
                        )
                    }
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
                        val productColor = productColors[index]
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
                                        currentColor = productColor.color
                                        selectedColorIndex = index
                                    }
                                ),
                            dimens = dimens,
                            isSelected = selectedColorIndex == index,
                            color = productColor.color
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

                Spacer(modifier = Modifier.height(dimens.mediumPadding3))
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
    selectedColor: Color = getAppColor(AppColors.COLOR_PRIMARY),
    isSelected: Boolean = false
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(dimens.smallPadding4))
            .background(
                color = if (isSelected) selectedColor
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
        context = LocalContext.current,
        productState = ProductState()
    )

}