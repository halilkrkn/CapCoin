package com.halilkrkn.capcoin.core.presentation.util

import android.content.Context
import com.halilkrkn.capcoin.R
import com.halilkrkn.capcoin.core.util.NetworkError

fun NetworkError.toString(context: Context): String {
    val resId = when (this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.NO_INTERNET_CONNECTION -> R.string.error_no_internet_connection
        NetworkError.SERVER_ERROR -> R.string.error_server_error
        NetworkError.SERIALIZATION_ERROR -> R.string.error_serialization
        NetworkError.UNKNOWN_ERROR -> R.string.error_unknown
    }

    return context.getString(resId)
}