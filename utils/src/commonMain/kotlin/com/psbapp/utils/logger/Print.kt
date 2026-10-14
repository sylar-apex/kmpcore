package com.psbapp.utils.logger

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.jvm.JvmStatic

enum class LogPriority { VERBOSE, DEBUG, INFO, NETWORK, WARN, ERROR }

// Android actual uses android.util.Log (Logcat colors by priority).
// Other platforms actual uses ANSI-colored println (terminal console only, no effect on Logcat).
internal expect fun platformLog(priority: LogPriority, tag: String, message: String, throwable: Throwable?)

object Print {

    private const val TAG = "AppLog => "
    private const val DEFAULT_CHUNK_SIZE = 1000

    fun log(message: String?, tag: String = TAG) {
        platformLog(LogPriority.INFO, tag, message.orEmpty(), null)
    }

    fun logInfo(message: String?, tag: String = TAG) {
        platformLog(LogPriority.INFO, tag, message.orEmpty(), null)
    }

    fun logWarn(message: String?, tag: String = TAG) {
        platformLog(LogPriority.WARN, tag, message.orEmpty(), null)
    }

    fun logDebug(message: String?, tag: String = TAG) {
        platformLog(LogPriority.DEBUG, tag, message.orEmpty(), null)
    }

    fun logVerbose(message: String?, tag: String = TAG) {
        platformLog(LogPriority.VERBOSE, tag, message.orEmpty(), null)
    }

    fun logNetwork(message: String?, tag: String = TAG) {
        platformLog(LogPriority.NETWORK, tag, message.orEmpty(), null)
    }

    fun logError(message: String?, throwable: Throwable? = null, tag: String = TAG) {
        platformLog(LogPriority.ERROR, tag, message.orEmpty(), throwable)
    }

    @JvmStatic
    fun logAsync(message: String, tag: String = TAG, maxLogSize: Int = DEFAULT_CHUNK_SIZE) {
        CoroutineScope(Dispatchers.Default).launch {
            logLong(message, tag, maxLogSize)
        }
    }

    @JvmStatic
    fun logLong(message: String, tag: String = TAG, maxLogSize: Int = DEFAULT_CHUNK_SIZE) {
        if (message.length <= maxLogSize) {
            platformLog(LogPriority.INFO, tag, message, null)
            return
        }

        val totalChunks = (message.length + maxLogSize - 1) / maxLogSize
        var start = 0
        var chunkIndex = 1

        while (start < message.length) {
            val end = (start + maxLogSize).coerceAtMost(message.length)
            platformLog(LogPriority.INFO, tag, "[$chunkIndex/$totalChunks] ${message.substring(start, end)}", null)
            start = end
            chunkIndex++
        }
    }
}
