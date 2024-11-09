package com.shub39.reflect.ui.page.reflect_page

import android.net.Uri
import com.shub39.reflect.data.Reflect

sealed interface ReflectPageAction {
    data class OnEdit(val reflect: Reflect): ReflectPageAction
    data class OnDelete(val id: Long): ReflectPageAction
    data class OnPlay(val id: Long): ReflectPageAction
    data class OnAdd(val uri: Uri): ReflectPageAction
}