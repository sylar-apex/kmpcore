package com.psbapp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.time.Duration.Companion.seconds


fun downTimer(totalTime: Int, delayTimeInSecond: Int = 1): Flow<Int> = flow {
    var time = totalTime
    while (time >= -1) {
        delay(delayTimeInSecond.seconds)
        emit(time--)
    }
}.flowOn(Dispatchers.Default)
