package icu.bluedream.historior.frontend.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        Button({println(System.getProperty("user.dir"))}) { Text("测试") }
    }
}