package icu.bluedream.historior.frontend.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import icu.bluedream.historior.frontend.screen.subview.LoginSubView
import icu.bluedream.historior.frontend.viewmodel.AccountViewModel
import kotlinx.coroutines.launch

class HlxAccountScreen : Screen {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val viewModel = rememberScreenModel { AccountViewModel() }
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            Modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) {
            if (viewModel.uiState.currentAccount != null) {
                Column {
                    Text("已登录")
                    Text(viewModel.uiState.currentAccount.toString())
                }
            } else {
                LoginSubView { oriUn, oriPW ->
                    viewModel.tryLogin(oriUn, oriPW) { msg ->
                        scope.launch {
                            snackbarHostState.showSnackbar(msg)
                        }
                    }
                }
            }
        }
        LaunchedEffect(Unit) {
            viewModel.loadLocalAccount {
                scope.launch { snackbarHostState.showSnackbar(it) }
            }
        }
    }
}