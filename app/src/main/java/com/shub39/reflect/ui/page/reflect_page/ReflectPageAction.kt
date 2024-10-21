package com.shub39.reflect.ui.page.reflect_page

import android.net.Uri

sealed interface ReflectPageAction {
    data class OnEdit(val id: String): ReflectPageAction
    data class OnDelete(val id: String): ReflectPageAction
    data class OnPlay(val id: String): ReflectPageAction
    data class OnAdd(val uri: Uri): ReflectPageAction
}