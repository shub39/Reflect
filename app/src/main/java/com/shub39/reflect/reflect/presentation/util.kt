package com.shub39.reflect.reflect.presentation

fun isValidTitle(title: String): Boolean {
    val invalidCharacters = Regex("[\\\\/:*?\"<>|]")
    return title.isNotEmpty() && title.isNotBlank() && title.length <= 30 && !invalidCharacters.containsMatchIn(title)
}