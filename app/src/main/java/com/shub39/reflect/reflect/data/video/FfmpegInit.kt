package com.shub39.reflect.reflect.data.video

import android.content.Context
import android.os.Environment
import android.util.Log
import com.arthenica.ffmpegkit.FFmpegKit
import com.arthenica.ffmpegkit.ReturnCode
import com.shub39.reflect.reflect.domain.Video
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FfmpegInit(
    context: Context
): Video {

    private val listFile = File(context.filesDir, "file_list.txt")

    override fun createVideo(name: String, filePaths: List<String>) {
        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd-hh-mm-ss"))
        createFileList(filePaths)

        val outputDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "Reflect")
        if (!outputDir.exists()) { outputDir.mkdirs() }
        val outputVideoFile = File(outputDir, "${sanitizeFileName(name)}-$time.mp4")

        val fileListPath = listFile.absolutePath
        val outputVideoPath = outputVideoFile.absolutePath
        Log.d("FFmpegKit", "Output video path: $outputVideoPath")

        val ffmpegCommand = "-f concat -safe 0 -i $fileListPath -vf scale=1080:1920,pad=1080:1920:(ow-iw)/2:(oh-ih)/2 -c:v mpeg4 -r 30 -pix_fmt yuv420p $outputVideoPath"
        FFmpegKit.executeAsync(ffmpegCommand) { session ->
            val returnCode = session.returnCode
            if (ReturnCode.isSuccess(returnCode)) {
                Log.d("FFmpegKit", "Video created successfully: $outputVideoPath")
            } else {
                Log.e("FFmpegKit", "Failed to create video: ${session.failStackTrace}")
            }
        }
    }

    private fun createFileList(filePaths: List<String>) {
        listFile.printWriter().use { out ->
            filePaths.forEach { path ->
                out.println("file '$path'")
                out.println("duration 1")
            }
        }
    }

    private fun sanitizeFileName(input: String): String {
        val invalidChars = Regex("[\\\\/:*?\"<>|\\s]")
        val sanitized = input.replace(invalidChars, "_")
        return sanitized.trim().ifEmpty { "output" }
    }
}