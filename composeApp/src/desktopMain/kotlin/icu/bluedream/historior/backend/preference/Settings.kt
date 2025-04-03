package icu.bluedream.historior.backend.preference

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

val LocalSettings: Settings
    get() = PreferencesSettings(Preferences.userRoot())
