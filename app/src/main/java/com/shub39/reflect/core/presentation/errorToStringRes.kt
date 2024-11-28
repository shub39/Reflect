package com.shub39.reflect.core.presentation

import com.shub39.reflect.R
import com.shub39.reflect.core.domain.VideoError

fun errorToStringRes(error: VideoError): Int {
    return when (error) {
        VideoError.LocalError.UNKNOWN -> R.string.error_unknown
    }
}