package com.psbapp.mvi

import kotlinx.coroutines.Job

/**
 * Handle returned by every `observe…` call; cancel it when the React component unmounts.
 *
 * Wraps the [Job] an [MviJsBridge] observation runs on, so JavaScript gets a handle it can hold
 * without seeing a coroutine type.
 */
@OptIn(ExperimentalJsExport::class)
@JsExport
class JsSubscription internal constructor(private val job: Job) {
    fun cancel() {
        job.cancel()
    }
}
