package com.shub39.reflect.domain

sealed interface DataError: Error {
    enum class Database: DataError {
        UNKNOWN
    }
}