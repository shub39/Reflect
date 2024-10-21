package com.shub39.reflect.ui.page.reflect_list

import android.os.Environment
import android.util.Log
import com.shub39.reflect.data.Reflect
import java.io.File

fun Reflect.toReflectUi(): ReflectUI {
    return ReflectUI(
        id = id,
        title = title,
        description = description,
        start = start,
        reminder = reminder,
        preview = get10FilePaths(title),
    )
}

fun ReflectUI.toReflect(): Reflect {
    return Reflect(
        id = id,
        title = title,
        description = description,
        reminder = reminder,
        start = start,
    )
}

// Creates folders if they not exist
private fun get10FilePaths(
    subFolderName: String,
): List<String> {

    val pictureDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Reflect")

    if (!pictureDir.exists()) {
        pictureDir.mkdirs()
        Log.d("Reflect Utils", "Reflect folder created")
    }

    val subFolder = File(pictureDir, subFolderName)

    if (!subFolder.exists() || !subFolder.isDirectory) {
        subFolder.mkdirs()
        Log.d("Reflect Utils", "Subfolder created at ${subFolder.absolutePath}")
        return emptyList()
    }

    val sortedFiles = subFolder.listFiles()?.take(10)?.sortedByDescending { it.lastModified() } ?: emptyList()
    Log.d("Reflect Utils", "Returning ${sortedFiles.size} files from ${subFolder.absolutePath}")
    return sortedFiles.map { it.absolutePath }

}