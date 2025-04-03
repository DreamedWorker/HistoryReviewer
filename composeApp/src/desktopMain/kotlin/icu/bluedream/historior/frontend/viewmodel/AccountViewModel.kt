package icu.bluedream.historior.frontend.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.alibaba.fastjson2.JSONObject
import com.russhwolf.settings.set
import historyreviewer.composeapp.generated.resources.Res
import historyreviewer.composeapp.generated.resources.*
import icu.bluedream.historior.backend.data.HlxAccountUiState
import icu.bluedream.historior.backend.model.HlxAccount
import icu.bluedream.historior.backend.preference.LocalSettings
import icu.bluedream.historior.backend.preference.PrefKey
import icu.bluedream.historior.backend.web.SignHelper
import icu.bluedream.historior.backend.web.hlxApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.getString

class AccountViewModel : ScreenModel {
    var uiState: HlxAccountUiState by mutableStateOf(HlxAccountUiState())
        private set

    fun loadLocalAccount(sendError: (String) -> Unit) {
        val data = LocalSettings.getString(PrefKey.LOCAL_ACCOUNT, "ERR")
        if (data != "ERR") {
            try {
                val formed = Json.decodeFromString<HlxAccount>(data)
                uiState = uiState.copy(currentAccount = formed)
            } catch (e: Exception) {
                sendError(e.message ?: "Unknown error!")
            }
        }
    }

    fun tryLogin(un: String, pw: String, sendError: (String) -> Unit) {
        screenModelScope.launch(Dispatchers.IO) {
            val passwordN = SignHelper.md5(pw)
            val loginMap = mapOf(
                "device_code" to "[d]9d77f167-7b58-4ab1-ae5e-0c9a5d316508",
                "voice_code" to "",
                "account" to un,
                "password" to passwordN
            )
            val loginResult = hlxApi.testLogin(
                account = un,
                password = passwordN,
                sign = SignHelper.toSign(loginMap, SignHelper.LOGIN_TAGS) ?: "EMPTY"
            )
            val jsonResult = JSONObject.parse(loginResult)
            if (jsonResult.containsKey("user")) {
                val formedValue = Json.decodeFromString<HlxAccount>(loginResult)
                LocalSettings[PrefKey.LOCAL_ACCOUNT] = Json.encodeToString(formedValue)
                withContext(Dispatchers.Main) {
                    uiState = uiState.copy(currentAccount = formedValue)
                }
            } else {
                withContext(Dispatchers.Main) {
                    sendError(jsonResult.getString("msg"))
                }
            }
        }
    }
}