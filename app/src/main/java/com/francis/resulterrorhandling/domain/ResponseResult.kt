package com.francis.resulterrorhandling.domain

typealias RootError = Error

sealed interface ResponseResult<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D) : ResponseResult<D, E>
    data class Error<out D, out E : RootError>(val error: E) : ResponseResult<D, E>
}
