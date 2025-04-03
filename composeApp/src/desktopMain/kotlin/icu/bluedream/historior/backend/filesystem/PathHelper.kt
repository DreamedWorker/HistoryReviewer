package icu.bluedream.historior.backend.filesystem

import java.io.File

object PathHelper {
    private val workRoot: String
        get() {
            val path = File("${System.getProperty("user.home")}/HistoryReviewer")
            if (!path.exists()) {
                path.mkdir()
            }
            return path.absolutePath
        }

    fun makePath(relativePath: String): File = File(workRoot, relativePath)
}