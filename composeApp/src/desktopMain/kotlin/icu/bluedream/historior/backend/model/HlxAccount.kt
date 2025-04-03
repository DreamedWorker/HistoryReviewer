package icu.bluedream.historior.backend.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class HlxAccount (
    val msg: String,
    @SerialName("_key")
    val key: String,
    val user: User,
    @SerialName("session_key")
    val sessionKey: String,
    val status: Long
) {
    @Serializable
    data class User (
        val userID: Long,
        val role: Long,
        val nick: String,
        val avatar: String,
        val birthday: Long,
        val age: Long,
        val gender: Long,
        val level: Long,
        val isgold: Long,
        val identityTitle: JsonElement? = null,
        val identityColor: Long,
        val needSetPassword: Long,
        val needSetUserInfo: Long
    )
}
