package com.flexath.ecommercemobile.presentation.screens.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DataSaverOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.flexath.currencyapp.ui.theme.AppColors
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.getAppColor

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    context: Context,
    dimens: Dimensions
) {
    Box(
        modifier = modifier
            .width(dimens.doubleExtraLargePadding10)
            .height(dimens.doubleExtraLargePadding2)
            .border(
            width = dimens.smallPadding0,
            color = getAppColor(AppColors.STROKE_COLOR),
            shape = RoundedCornerShape(dimens.mediumPadding3)
        ),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            imageVector = Icons.Default.DataSaverOn,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(dimens.mediumPadding3))
                .padding(vertical = dimens.mediumPadding1, horizontal = dimens.mediumPadding3)
        )
    }
}