package icu.bluedream.historior.frontend.screen.subview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import historyreviewer.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginSubView(login: (String, String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(Res.drawable.compose_multiplatform), "logo",
                modifier = Modifier.size(98.dp)
            )
            Text(
                stringResource(Res.string.account_title),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
        Column(modifier = Modifier.fillMaxWidth().padding(top = 32.dp)) {
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            TextField(
                username,
                { neo -> username = neo },
                placeholder = { Text(stringResource(Res.string.account_name)) },
                leadingIcon = { Icon(Icons.Default.Email, "") },
                trailingIcon = {
                    if (username.isNotBlank() && username.isNotEmpty()) {
                        IconButton({
                            username = ""
                        }) { Icon(Icons.Default.ClearAll, "") }
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
            )
            TextField(
                password,
                { neo -> password = neo },
                placeholder = { Text(stringResource(Res.string.account_password)) },
                leadingIcon = { Icon(Icons.Default.Password, "") },
                trailingIcon = {
                    if (password.isNotBlank() && password.isNotEmpty()) {
                        IconButton({
                            password = ""
                        }) { Icon(Icons.Default.ClearAll, "") }
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = {
                    login(username, password)
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text(stringResource(Res.string.account_login))
            }
        }
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Icon(Icons.Outlined.Info, "")
            Text(
                stringResource(Res.string.account_exp),
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(Modifier.weight(1f))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.PrivacyTip, "")
            Text(stringResource(Res.string.account_privacy), style = MaterialTheme.typography.bodyMedium)
            Text(stringResource(Res.string.account_privacy_exp), style = MaterialTheme.typography.bodySmall)
        }
    }
}