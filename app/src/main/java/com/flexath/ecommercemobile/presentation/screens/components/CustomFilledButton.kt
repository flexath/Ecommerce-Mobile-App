package com.flexath.ecommercemobile.presentation.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.currencyapp.ui.theme.AppColors
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.getAppColor
import com.flexath.ecommercemobile.ui.theme.EcommerceMobileTheme
import com.flexath.ecommercemobile.ui.theme.dimens

@Composable
fun CustomFilledButton(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    buttonText: String,
    onClick: () -> Unit,
    containerColor: Color = getAppColor(AppColors.COLOR_PRIMARY),
    isLoading: Boolean = false
) {
    Button(
        modifier = modifier.heightIn(min = dimens.extraLargePadding5),
        shape = RoundedCornerShape(dimens.mediumPadding1),
        enabled = !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = getAppColor(AppColors.TEXT_COLOR_PRIMARY)
        ),
        onClick = {
            onClick()
        }
    ) {
        if(isLoading) {
            CircularProgressIndicator(
                color = getAppColor(AppColors.COLOR_PRIMARY),
                strokeWidth = dimens.smallPadding1,
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
        } else {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = getAppColor(AppColors.COLOR_BACKGROUND)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomFilledButtonPreview() {
    EcommerceMobileTheme {
        CustomFilledButton(
            modifier = Modifier.fillMaxWidth().padding(MaterialTheme.dimens.mediumPadding3),
            buttonText = "Login",
            isLoading = false,
            dimens = MaterialTheme.dimens,
            onClick = {}
        )

    }
}