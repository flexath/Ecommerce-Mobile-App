package com.flexath.ecommercemobile.presentation.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
 fun CustomDialog(
     showDialog: Boolean,
     onDismiss: () -> Unit,
     content: @Composable () -> Unit
 ) {
     if (showDialog) {
         Dialog(
             onDismissRequest = onDismiss
         ) {
             content()
         }
     }
 }