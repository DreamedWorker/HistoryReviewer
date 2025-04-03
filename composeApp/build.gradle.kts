import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization") version "2.1.10"
    id("com.google.devtools.ksp") version "2.1.10-1.0.31"
    id("de.jensklingenberg.ktorfit") version "2.5.0"
}

kotlin {
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.icons.jetbrains)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenModel)
            implementation(libs.kotlinx.serialization.json)
            implementation("cn.hutool:hutool-core:5.8.36")
            implementation("com.russhwolf:multiplatform-settings:1.3.0")
            implementation("de.jensklingenberg.ktorfit:ktorfit-lib:2.5.0")
            implementation("org.xerial:sqlite-jdbc:3.49.1.0")
            implementation("com.alibaba.fastjson2:fastjson2:2.0.56")
        }
    }
}


compose.desktop {
    application {
        mainClass = "icu.bluedream.historior.MainKt"

        buildTypes.release.proguard {
            isEnabled = false
        }

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "icu.bluedream.historior"
            packageVersion = "1.0.0"
        }
    }
}
