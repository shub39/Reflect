package com.shub39.reflect.core.domain

import androidx.annotation.FontRes
import com.shub39.reflect.R

enum class Fonts(
    val fullName: String,
    @FontRes val fontId: Int
) {
    POPPINS("Poppins", R.font.poppins),
}