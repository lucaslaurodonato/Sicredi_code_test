package com.lucasdonato.sicredi_bank_events.model.domain.base

import android.util.Log
import com.lucasdonato.sicredi_bank_events.model.data.model.entities.error.ErrorCode
import com.lucasdonato.sicredi_bank_events.model.data.model.entities.error.ErrorException

/**
 * Use this method for execute requests WS/DB inside methods on UseCase
 */
fun <T : Any?> runMethod(block: () -> T): T {
    try {
        return block()
    } catch (e: Exception) {
        Log.e("UseCaseHelper", e.message ?: "")
        when (e) {
            is ErrorException -> throw ErrorException(e.errorCode)
            else -> throw ErrorException(ErrorCode.GENERIC_ERROR)
        }
    }
}

suspend fun <T : Any?> runSuspend(run: suspend () -> T): T {
    try {
        return run()
    } catch (e: Exception) {
        Log.e("UseCaseHelper", e.message ?: "")
        when (e) {
            is ErrorException -> throw ErrorException(e.errorCode)
            else -> throw ErrorException(ErrorCode.GENERIC_ERROR)
        }
    }
}