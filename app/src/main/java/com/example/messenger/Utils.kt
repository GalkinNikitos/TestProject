package com.example.messenger

import android.os.Environment
import java.io.File
import java.io.FileWriter

object Utils {
    fun saveDataToFileInDocuments(fileName: String, content: String): Boolean {
        if (isExternalStorageWritable()) {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            val file = File(downloadsDir, fileName)

            try {
                FileWriter(file).use { writer ->
                    writer.write(content)
                }
                return true
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }
    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }
}