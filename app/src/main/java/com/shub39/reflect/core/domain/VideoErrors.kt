package com.shub39.reflect.core.domain

sealed interface VideoError: Error {
   enum class LocalError: VideoError {
      UNKNOWN
   }
}