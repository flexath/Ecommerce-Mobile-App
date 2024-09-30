package com.flexath.ecommercemobile.presentation.screens.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.currencyapp.ui.theme.AppColors
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.getAppColor
import com.flexath.ecommercemobile.ui.theme.dimens

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = getAppColor(color = AppColors.COLOR_PRIMARY),
    unselectedColor: Color = getAppColor(color = AppColors.INFO_BACKGROUND),
    onClickDot: (index: Int) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(pageSize) { index ->
            Box(
                modifier = Modifier
                    .size(dimens.smallPadding4)
                    .clip(CircleShape)
                    .background(
                        color = if (index == selectedPage) selectedColor else unselectedColor
                    ).clickable {
                        onClickDot(index)
                    }
            )

            if(index != pageSize - 1) {
                Spacer(modifier = Modifier.width(dimens.smallPadding4))
            }
        }
    }
}

@Composable
fun ScrollablePageIndicator(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = getAppColor(color = AppColors.COLOR_PRIMARY),
    unselectedColor: Color = getAppColor(color = AppColors.INFO_BACKGROUND),
    onClickDot: (index: Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val visibleItemCount = 5

    // Calculate scroll position for auto-scrolling
    LaunchedEffect(selectedPage) {
        if (pageSize > visibleItemCount) {
            val targetIndex = (selectedPage - visibleItemCount / 2).coerceAtLeast(0)
            val targetIndex2 = (selectedPage - visibleItemCount / 2).coerceIn(0, pageSize - visibleItemCount)
            Log.i("ScrollablePageIndicator","Target Index 1: $selectedPage")
            Log.i("ScrollablePageIndicator","Target Index 2: $visibleItemCount")
            Log.i("ScrollablePageIndicator","Target Index 3: ${(selectedPage - visibleItemCount / 2)}")
            Log.i("ScrollablePageIndicator","Target Index 4: $targetIndex")
            lazyListState.animateScrollToItem(targetIndex2)
        }
    }

    Log.i("PageIndicator","Size: $pageSize")
    Log.i("PageIndicator","Page: $selectedPage")

    LazyRow(
        modifier = modifier,
        state = lazyListState,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(count = pageSize) { index ->
            Log.i("PageIndicator","Index Page: $pageSize")
            Log.i("PageIndicator","Index: $index")
            Box(
                modifier = Modifier
                    .size(dimens.smallPadding4)
                    .clip(CircleShape)
                    .background(
                        color = if (index == selectedPage) selectedColor else unselectedColor
                    )
                    .clickable { onClickDot(index) }
            )

            if (index != pageSize - 1) {
                Spacer(modifier = Modifier.width(dimens.smallPadding4))
            }
        }
    }
}

@Preview
@Composable
private fun PageIndicatorPreview() {
    PageIndicator(
        modifier = Modifier,
        dimens = MaterialTheme.dimens,
        pageSize = 3,
        selectedPage = 0,
        onClickDot = {

        }
    )
}