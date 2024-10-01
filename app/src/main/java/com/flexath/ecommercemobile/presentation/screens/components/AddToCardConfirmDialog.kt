package com.flexath.ecommercemobile.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flexath.currencyapp.ui.theme.AppColors
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.getAppColor
import com.flexath.ecommercemobile.domain.model.ProductVO
import com.flexath.ecommercemobile.presentation.constants.ProductColor

@Composable
fun AddToCardDialog(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    product: ProductVO,
    size: Double,
    color: ProductColor,
    quantity: Int,
    showDialog: Boolean,
    onDismiss: (Boolean) -> Unit = {},
    onNavigate: (String) -> Unit = {}
) {
    val totalPrice = product.price?.times(quantity)
    CustomDialog(showDialog = showDialog, onDismiss = { onDismiss(false) }) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = dimens.smallPadding4),
            modifier = Modifier.padding(dimens.mediumPadding3)
        ) {
            Column(
                modifier = Modifier
                    .padding(dimens.mediumPadding3)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Order Summary",
                    style = MaterialTheme.typography.headlineSmall,
                    color = getAppColor(AppColors.TEXT_COLOR_PRIMARY)
                )

                Spacer(modifier = Modifier.height(dimens.mediumPadding3))

                Text(
                    text = "Product: ${product.title}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = getAppColor(AppColors.TEXT_COLOR_PRIMARY)
                )

                Spacer(modifier = Modifier.height(dimens.smallPadding4))

                Text(
                    text = "Size: $size",
                    style = MaterialTheme.typography.bodyMedium,
                    color = getAppColor(AppColors.TEXT_COLOR_PRIMARY)
                )

                Spacer(modifier = Modifier.height(dimens.smallPadding4))

                Text(
                    text = "Color: ${color.name}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = getAppColor(AppColors.TEXT_COLOR_PRIMARY)
                )

                Spacer(modifier = Modifier.height(dimens.smallPadding4))

                Text(
                    text = "Quantity: $quantity",
                    style = MaterialTheme.typography.bodyMedium,
                    color = getAppColor(AppColors.TEXT_COLOR_PRIMARY)
                )

                Spacer(modifier = Modifier.height(dimens.smallPadding2))

                HorizontalDivider(modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(dimens.smallPadding2))

                Text(
                    text = "Total price: $totalPrice",
                    style = MaterialTheme.typography.bodyMedium,
                    color = getAppColor(AppColors.TEXT_COLOR_PRIMARY)
                )

                Spacer(modifier = Modifier.height(dimens.mediumPadding3))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomFilledButton(
                        modifier = Modifier.weight(1f),
                        dimens = dimens,
                        containerColor = getAppColor(AppColors.COLOR_SECONDARY),
                        buttonText = "Cancel",
                        onClick = {
                            onDismiss(false)
                        }
                    )

                    Spacer(modifier = Modifier.width(dimens.smallPadding4))

                    CustomFilledButton(
                        modifier = Modifier.weight(1f),
                        dimens = dimens,
                        containerColor = getAppColor(AppColors.COLOR_PRIMARY),
                        buttonText = "Confirm",
                        onClick = {
                            onDismiss(false)
                            onNavigate("Added successfully")
                        }
                    )
                }
            }
        }
    }
}