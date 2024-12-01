package com.shub39.reflect.reflect.presentation.reflect_page

import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun copyImage(
    uri: Uri,
    stateId: Long,
    date: LocalDate,
    context: Context
): Boolean {
    val picturesDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Reflect")
    val subfolder = File(picturesDir, stateId.toString())

    if (!subfolder.exists() || !subfolder.isDirectory) subfolder.mkdirs()

    try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)

        if (inputStream != null) {
            val fileName = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".jpg"
            val destinationFile = File(subfolder, fileName)

            val outputStream = FileOutputStream(destinationFile)

            val buffer = ByteArray(1024)
            var bytesRead: Int

            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }

            inputStream.close()
            outputStream.close()

            return true
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return false
}