package com.flexath.ecommercemobile.presentation.screens.components

import android.content.Context
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flexath.currencyapp.ui.theme.AppColors
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.getAppColor
import com.flexath.ecommercemobile.ui.theme.dimens

@Composable
fun ProductCardShimmerEffect(
    modifier: Modifier = Modifier,
    dimens: Dimensions
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimens.smallPadding4)
    ) {
        repeat(10) {
            ProductCardShimmer(
                modifier = modifier,
                dimens = dimens,
            )
        }
    }
}

@Composable
fun ProductCardShimmer(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    context: Context = LocalContext.current
) {
    ElevatedCard(
        shape = RoundedCornerShape(MaterialTheme.dimens.mediumPadding3),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = MaterialTheme.dimens.smallPadding0
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = getAppColor(AppColors.COLOR_BACKGROUND)
        ),
        modifier = Modifier.padding(horizontal = dimens.mediumPadding3).fillMaxWidth().clip(RoundedCornerShape(MaterialTheme.dimens.mediumPadding3))
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(16f/8f)
                .background(getAppColor(AppColors.SEARCH_RESULT_BOX))
                .skeletonEffect()
        )
    }
}

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmer"
    ).value

    background(
        color = getAppColor(color = AppColors.SHIMMER_EFFECT).copy(alpha = alpha)
    )
}

fun Modifier.skeletonEffect(
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(
        animation = tween(
            durationMillis = 500,
            easing = LinearEasing,
            delayMillis = 100
        ),
        repeatMode = RepeatMode.Restart
    ),
    shimmerColors: List<Color> = listOf(
        Color(0xFFC3C3C3).copy(alpha = 0.2f),
        Color(0xFFC3C3C3).copy(alpha = 0.4f),
        Color(0xFFC3C3C3).copy(alpha = 0.2f)
    )
): Modifier = composed {
    var startOffsetX by remember { mutableFloatStateOf(0f) }
    val shimmerWidth = with(LocalDensity.current) { 100.dp.toPx() }

    val transition = rememberInfiniteTransition(label = "transition shimmer")
    startOffsetX = transition.animateFloat(
        initialValue = -shimmerWidth,
        targetValue = shimmerWidth,
        animationSpec = animationSpec, label = "transition shimmer"
    ).value

    background(
        brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + shimmerWidth, 0f)
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PostCardListShimmerPreview() {
    ProductCardShimmerEffect(
        modifier = Modifier.fillMaxWidth(),
        dimens = MaterialTheme.dimens
    )
}