package com.shub39.reflect.reflect.presentation

import android.os.Environment
import android.util.Log
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun isValidTitle(title: String): Boolean {
    val invalidCharacters = Regex("[\\\\/:*?\"<>|]")
    return title.isNotEmpty() && title.isNotBlank() && title.length <= 30 && !invalidCharacters.containsMatchIn(title)
}

// Creates folders if they not exist
fun getFilePathsWithDates(
    subFolderName: String,
    no: Int = 10,
): Map<LocalDate, String> {

    val pictureDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Reflect")
    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    if (!pictureDir.exists()) {
        pictureDir.mkdirs()
    }

    val subFolder = File(pictureDir, subFolderName)

    if (!subFolder.exists() || !subFolder.isDirectory) {
        subFolder.mkdirs()
        return emptyMap()
    }

    val files = subFolder.listFiles() ?: return emptyMap()

    val sortedFiles = files
        .filter { it.isFile }
        .mapNotNull { file ->
            val datePart = file.nameWithoutExtension
            try {
                val date = LocalDate.parse(datePart, dateFormatter)
                date to file.absolutePath
            } catch (e: Exception) {
                Log.e("Reflect", "Invalid file name: ${file.name}")
                null
            }
        }
        .sortedByDescending { it.first }


    return if (no > 0) {
        sortedFiles.take(no).toMap()
    } else {
        sortedFiles.toMap()
    }

}