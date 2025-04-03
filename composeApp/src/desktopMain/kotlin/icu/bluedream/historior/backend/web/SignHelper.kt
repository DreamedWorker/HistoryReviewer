package icu.bluedream.historior.backend.web

import java.security.MessageDigest

object SignHelper {
    val LOGIN_TAGS = listOf<String>("account", "device_code", "password", "voice_code")

    fun toSign(args: Map<String, String>, tags: List<String>, isUpperCase: Boolean = false): String? {
        var sign = ""
        for (k in tags) {
            if (args.containsKey(k)) {
                sign += k + args[k]
            } else {
                return null
            }
        }
        val finalString = sign + "fa1c28a5b62e79c3e63d9030b6142e4b"
        return if (isUpperCase) {
            md5(finalString).uppercase()
        } else {
            md5(finalString)
        }
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(input.toByteArray(Charsets.UTF_8))
        return digest.joinToString("") { "%02x".format(it) }
    }
}