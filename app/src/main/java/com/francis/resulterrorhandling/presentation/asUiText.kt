package com.francis.resulterrorhandling.presentation

import com.francis.resulterrorhandling.R
import com.francis.resulterrorhandling.domain.DataError
import com.francis.resulterrorhandling.domain.ResponseResult

fun DataError.asUiText():UiText{
    return when(this){
        DataError.Local.DISK_FULL -> UiText.StringResource(
            R.string.error_disk_full
        )
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(
            R.string.request_timeout
        )
        DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(
            R.string.youve_hit_your_rate_limit
        )
        DataError.Network.NO_INTERNET -> UiText.StringResource(
            R.string.no_internet
        )
        DataError.Network.PAYLOAD_TOO_LARGE -> UiText.StringResource(
            R.string.file_too_large
        )
        DataError.Network.SERVER_ERROR -> UiText.StringResource(
            R.string.server_error
        )
        DataError.Network.SERIALIZATION -> UiText.StringResource(
            R.string.error_serialization
        )
        DataError.Network.UNKNOWN -> UiText.StringResource(
            R.string.unknown_error
        )
    }
}

fun ResponseResult.Error<*,DataError>.asErrorUiText():UiText = error.asUiText()