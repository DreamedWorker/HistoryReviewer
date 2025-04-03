package icu.bluedream.historior.frontend.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import historyreviewer.composeapp.generated.resources.*
import historyreviewer.composeapp.generated.resources.Res
import historyreviewer.composeapp.generated.resources.compose_multiplatform
import historyreviewer.composeapp.generated.resources.wizard_tip
import historyreviewer.composeapp.generated.resources.wizard_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.system.exitProcess

@Composable
fun FirstOpenView(
    agreeEvt: () -> Unit
) {
    val colorfulString = buildAnnotatedString {
        append(stringResource(Res.string.wizard_tip))
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.error,
                textDecoration = TextDecoration.Underline
            )) {
            append(stringResource(Res.string.wizard_right))
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(Res.drawable.compose_multiplatform), "logo",
            modifier = Modifier.size(98.dp)
        )
        Text(
            stringResource(Res.string.wizard_title),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        Text(colorfulString, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.weight(1f))
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Outlined.Warning, "warning",
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(stringResource(Res.string.wizard_special), style = MaterialTheme.typography.bodyLarge)
            Text(stringResource(Res.string.wizard_special_exp), style = MaterialTheme.typography.bodySmall)
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton({
                exitProcess(0)
            }) {
                Text(stringResource(Res.string.app_disagree))
            }
            Spacer(Modifier.width(8.dp).height(1.dp))
            Button(agreeEvt){
                Text(stringResource(Res.string.app_agree))
            }
        }
    }
}