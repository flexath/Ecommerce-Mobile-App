package com.flexath.ecommercemobile.presentation.screens.components

import android.content.Context
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.currencyapp.ui.theme.AppColors
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.getAppColor
import com.flexath.ecommercemobile.R
import com.flexath.ecommercemobile.ui.theme.dimens

fun LazyListScope.productCardList(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    context: Context,
    onNavigate: () -> Unit = {}
) {
    items(count = 10) {
        Spacer(modifier = Modifier.height(dimens.smallPadding4))

        ProductCard(
            modifier = modifier,
            dimens = dimens,
            onNavigate = onNavigate
        )

        Spacer(modifier = Modifier.height(dimens.smallPadding4))
    }
}

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    onNavigate: () -> Unit = {}
) {
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
            Image(
                painter = painterResource(id = R.drawable.splash2),
                contentDescription = null,
                contentScale = ContentScale.Crop,
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
                    text = "$250",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = getAppColor(AppColors.TEXT_COLOR_PRIMARY),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(dimens.smallPadding4))

                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = getAppColor(AppColors.COLOR_PRIMARY)
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
                    text = "Product Name",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
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
        dimens = MaterialTheme.dimens
    )
}