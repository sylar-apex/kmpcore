package com.psbapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController

@Composable
fun getKeyboard(): SoftwareKeyboardController? = LocalSoftwareKeyboardController.current

