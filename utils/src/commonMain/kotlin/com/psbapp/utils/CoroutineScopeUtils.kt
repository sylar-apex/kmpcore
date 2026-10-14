package com.psbapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun CoroutineScope.coroutineLaunch(block: suspend () -> Unit) {
    this.launch {
        block()
    }
}

@Composable
fun getCoroutineScope() = rememberCoroutineScope()
