package icu.bluedream.historior

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.russhwolf.settings.set
import icu.bluedream.historior.backend.preference.LocalSettings
import icu.bluedream.historior.backend.preference.PrefKey
import icu.bluedream.historior.frontend.component.FirstOpenView
import icu.bluedream.historior.frontend.screen.HlxAccountScreen
import icu.bluedream.historior.frontend.screen.HomeScreen
import icu.bluedream.historior.frontend.theme.AppTheme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "璀璨记忆的数字织卷",
    ) {
        AppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                var selectedItem by remember { mutableIntStateOf(0) }
                var isFirstOpened by remember { mutableStateOf(false) }
                if (isFirstOpened) {
                    FirstOpenView {
                        LocalSettings[PrefKey.CHECK_FIRST_OPEN] = "0.0.1"
                        isFirstOpened = LocalSettings.getString(PrefKey.CHECK_FIRST_OPEN, "0.0.0") != "0.0.1"
                    }
                } else {
                    Navigator(HomeScreen()) { nav ->
                        Row(modifier = Modifier.fillMaxSize()) {
                            NavigationRail {
                                NavigationRailItem(
                                    selected = selectedItem == 0,
                                    onClick = {
                                        selectedItem = 0
                                        nav.push(HomeScreen())
                                    },
                                    label = { Text("主页") },
                                    icon = { Icon(Icons.Default.Home, "home") }
                                )
                                NavigationRailItem(
                                    selected = selectedItem == 1,
                                    onClick = {
                                        selectedItem = 1
                                        nav.push(HlxAccountScreen())
                                    },
                                    label = { Text("我的账号") },
                                    icon = { Icon(Icons.Default.Person, "home") }
                                )
                            }
                            CurrentScreen()
                        }
                    }
                }
                LaunchedEffect(Unit) {
                    val lastOpenedVersion: String = LocalSettings.getString(PrefKey.CHECK_FIRST_OPEN, "0.0.0")
                    isFirstOpened = lastOpenedVersion != "0.0.1"
                }
            }
        }
    }
}