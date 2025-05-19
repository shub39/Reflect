package com.shub39.reflekt.domain

sealed interface DataError: Error {
    enum class Database: DataError {
        UNKNOWN
    }
}