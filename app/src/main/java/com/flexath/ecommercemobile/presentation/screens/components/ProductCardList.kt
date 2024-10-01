package com.flexath.ecommercemobile.presentation.screens.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.flexath.currencyapp.ui.theme.AppColors
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.getAppColor
import com.flexath.ecommercemobile.domain.model.ProductVO
import com.flexath.ecommercemobile.ui.theme.dimens

fun LazyListScope.productCardList(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    context: Context,
    productList: List<ProductVO>,
    onNavigate: (id: Int) -> Unit = {},
) {
    items(count = productList.size) { index ->
        val product = productList[index]
        Spacer(modifier = Modifier.height(dimens.smallPadding4))

        ProductCard(
            modifier = modifier,
            dimens = dimens,
            context = context,
            product = product,
            onNavigate = {
                onNavigate(product.id)
            }
        )

        Spacer(modifier = Modifier.height(dimens.smallPadding4))
    }
}

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    context: Context,
    product: ProductVO,
    onNavigate: () -> Unit = {},
) {
    var isClickOnFavorite by rememberSaveable {
        mutableStateOf(false)
    }

    ElevatedCard(
        shape = RoundedCornerShape(MaterialTheme.dimens.mediumPadding3),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = MaterialTheme.dimens.smallPadding0
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = getAppColor(AppColors.COLOR_BACKGROUND)
        ),
        modifier = modifier.clip(RoundedCornerShape(MaterialTheme.dimens.mediumPadding3))
            .clickable {
                onNavigate()
            }
    ) {
        Box(
            modifier = Modifier.background(getAppColor(AppColors.SEARCH_RESULT_BOX))
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(context).data(product.thumbnail.orEmpty())
                    .crossfade(true)
                    .build(),
                contentDescription = product.title.orEmpty(),
                contentScale = ContentScale.Fit,
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.scale(0.1f)
                    )
                },
                modifier = Modifier.align(Alignment.Center).aspectRatio(16f/8f)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(horizontal = dimens.mediumPadding3, vertical = dimens.smallPadding4)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = getAppColor(AppColors.TEXT_COLOR_PRIMARY),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(dimens.smallPadding4))

                Icon(
                    imageVector = if(isClickOnFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = getAppColor(AppColors.COLOR_PRIMARY),
                    modifier = Modifier.clickable {
                        isClickOnFavorite = !isClickOnFavorite
                    }
                )
            }

            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = dimens.mediumPadding3, vertical = dimens.smallPadding4)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${product.title}",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = getAppColor(AppColors.TEXT_COLOR_PRIMARY),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(dimens.smallPadding4))

                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                    tint = getAppColor(AppColors.COLOR_BACKGROUND),
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(
                            dimens.smallPadding0,
                            getAppColor(AppColors.STROKE_COLOR),
                            CircleShape
                        )
                        .background(getAppColor(AppColors.TEXT_COLOR_SECONDARY))
                        .padding(dimens.smallPadding4)
                        .clickable {

                        }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProductCardPreview() {
    ProductCard(
        modifier = Modifier.fillMaxWidth(),
        dimens = MaterialTheme.dimens,
        context = LocalContext.current,
        product = ProductVO(
            id = 1,
            title = "Product Name",
            description = "Product Description",
            price = 250,
            rating = 0.7,
            stock = 1,
            brand = "Brand Name",
            thumbnail = "https://i.dummyjson.com/data/products/1/thumbnail.jpg",
            images = listOf(),
            shippingInformation = ""
        )
    )
}