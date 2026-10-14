package com.psbapp.utils.clipboard

import androidx.compose.ui.platform.ClipEntry

/**
 * Extension function to read text from a ClipEntry asynchronously. This bridges the
 * platform-specific clipboard APIs to a common interface.
 */
expect suspend fun ClipEntry.readText(): String?
