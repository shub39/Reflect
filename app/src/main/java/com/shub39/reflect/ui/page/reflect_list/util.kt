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
        preview = getFilePaths(title),
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
fun getFilePaths(
    subFolderName: String,
    no: Int = 10,
): List<String> {

    val pictureDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Reflect")

    if (!pictureDir.exists()) {
        pictureDir.mkdirs()
    }

    val subFolder = File(pictureDir, subFolderName)

    if (!subFolder.exists() || !subFolder.isDirectory) {
        subFolder.mkdirs()
        return emptyList()
    }

    val sortedFiles = if (no > 0) {
        subFolder.listFiles()?.take(no)?.sortedByDescending { it.lastModified() } ?: emptyList()
    } else {
        subFolder.listFiles()?.sortedByDescending { it.lastModified() } ?: emptyList()
    }

    Log.d("Reflect", "getFilePaths: $sortedFiles")
    return sortedFiles.map { it.absolutePath }

}

fun changeFolderName(
    previousName: String,
    name: String,
) {
    val pictureDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Reflect")

    if (!pictureDir.exists()) {
        pictureDir.mkdirs()
    }

    val subFolder = File(pictureDir, previousName)

    if (!subFolder.exists() || !subFolder.isDirectory) {
        File(pictureDir, name).mkdirs()
    } else {
        subFolder.renameTo(File(pictureDir, name))
    }
}