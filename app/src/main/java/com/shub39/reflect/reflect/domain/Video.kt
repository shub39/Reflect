package com.shub39.reflect.reflect.domain

import com.shub39.reflect.core.domain.Result
import com.shub39.reflect.core.domain.VideoError

interface Video {
    fun createVideo(name: String, filePaths: List<String>, callback: (Result<String, VideoError>) -> Unit)
}