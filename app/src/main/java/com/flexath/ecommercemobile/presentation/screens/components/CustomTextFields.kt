package com.flexath.ecommercemobile.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.flexath.currencyapp.ui.theme.AppColors
import com.flexath.currencyapp.ui.theme.getAppColor
import com.flexath.ecommercemobile.ui.theme.CustomFont
import com.flexath.ecommercemobile.ui.theme.dimens
import com.flexath.ecommercemobile.ui.theme.getFont

@Composable
fun InnerBasicTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textFieldState: TextFieldState = rememberTextFieldState(),
    hint: String,
    onKeyboardAction: (String) -> Unit = {},
    onFocusChange: (Boolean) -> Unit
) {
    val onFocusChangeUpdated by rememberUpdatedState(newValue = onFocusChange)

    if (enabled) {
        LaunchedEffect(key1 = textFieldState.text) {
            if (textFieldState.text.isEmpty()) {
                onFocusChangeUpdated(false)
            } else {
                onFocusChangeUpdated(true)
            }
        }
    }

    BasicTextField(
        state = textFieldState,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = getFont(CustomFont.Poppins),
            color = getAppColor(AppColors.TEXT_COLOR_PRIMARY)
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        onKeyboardAction = {
            onKeyboardAction(textFieldState.text.toString())
        },
        lineLimits = TextFieldLineLimits.SingleLine,
        cursorBrush = SolidColor(value = MaterialTheme.colorScheme.primary),
        decorator = { innerTextField ->
            if (textFieldState.text.isEmpty()) {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = getFont(CustomFont.Poppins),
                    ),
                    color = getAppColor(AppColors.TEXT_COLOR_SECONDARY),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            innerTextField()
        },
        modifier = modifier.onFocusChanged {
            onFocusChangeUpdated(it.isFocused)
        },
    )
}

@Composable
fun SearchBasicTextField(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    hint: String,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: Int? = null,
    leadingIconTint: Color = MaterialTheme.colorScheme.primary,
    focusedStrokeColor: Color = MaterialTheme.colorScheme.primary,
    trailingIcon: Int? = null,
    trailingIconTint: Color = MaterialTheme.colorScheme.primary,
    onClickTrailingIcon: () -> Unit = {},
    onKeyboardAction: (String) -> Unit = {}
) {
    val defaultStrokeColor = getAppColor(AppColors.STROKE_COLOR)
    var isFocused by remember { mutableStateOf(false) }
    val strokeColor by remember(key1 = isFocused) {
        derivedStateOf {
            if (isFocused) focusedStrokeColor else defaultStrokeColor
        }
    }

    ElevatedCard(
        shape = RoundedCornerShape(MaterialTheme.dimens.mediumPadding3),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = MaterialTheme.dimens.smallPadding2
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = getAppColor(AppColors.COLOR_BACKGROUND)
        ),
        modifier = modifier

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = MaterialTheme.dimens.mediumPadding3, horizontal = MaterialTheme.dimens.mediumPadding1)
        ) {
            leadingIcon?.let {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    tint = leadingIconTint,
                    contentDescription = "Leading Icon",
                )
            }

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.mediumPadding1))

            InnerBasicTextField(
                modifier = Modifier.weight(1f),
                textFieldState = textFieldState,
                hint = hint,
                enabled = enabled,
                readOnly = readOnly,
                onKeyboardAction = onKeyboardAction,
                onFocusChange = {
                    isFocused = it
                },
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.mediumPadding1))

            trailingIcon?.let {
                Icon(
                    painter = painterResource(id = trailingIcon),
                    tint = trailingIconTint,
                    contentDescription = "Leading Icon",
                    modifier = Modifier
                        .clickable {
                            onClickTrailingIcon()
                        }
                )
            }
        }
    }
}