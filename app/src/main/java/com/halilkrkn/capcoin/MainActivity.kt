package com.halilkrkn.capcoin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.halilkrkn.capcoin.core.presentation.util.ObserveAsEvents
import com.halilkrkn.capcoin.core.presentation.util.toString
import com.halilkrkn.capcoin.presentation.coin_list.CoinListEvent
import com.halilkrkn.capcoin.presentation.coin_list.CoinListScreen
import com.halilkrkn.capcoin.presentation.coin_list.CoinListState
import com.halilkrkn.capcoin.presentation.coin_list.CoinListViewModel
import com.halilkrkn.capcoin.ui.theme.CapCoinTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CapCoinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val context = LocalContext.current
                    ObserveAsEvents(events = viewModel.events) { event ->
                        when (event) {
                            is CoinListEvent.Error -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    CoinListScreen(
                        state = state,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}