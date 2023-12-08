package com.test.testcoroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

fun CoroutineScope.doAsyncJob(): Deferred<Some> {
    return async {
        time5000()
    }
}

suspend fun time5000(): Some {
    delay(5000)
    return Some("name")
}

data class Some(
    val name: String
)