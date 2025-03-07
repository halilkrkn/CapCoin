package com.halilkrkn.capcoin.presentation.coin_list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.halilkrkn.capcoin.core.presentation.util.toString
import com.halilkrkn.capcoin.presentation.coin_list.components.CoinListItem
import com.halilkrkn.capcoin.presentation.coin_list.components.previewCoin
import com.halilkrkn.capcoin.ui.theme.CapCoinTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext

@Composable
fun CoinListScreen(
    state: CoinListState,
//    events: Flow<CoinListEvent>,
    modifier: Modifier = Modifier,
) {




/*    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    // Bu yapı, MVVM mimarisinde ViewModel'den gelen hata eventlerini kullanıcıya göstermek için kullanılan REACTIVE bir PATTERN'dir
    LaunchedEffect(key1 = lifecycleOwner.lifecycle) {
        // Komponentin STARTED durumunda olduğunda çalışır
        // Komponent arka plana geçtiğinde otomatik olarak durur ve öne geldiğinde devam eder(tekar çalışır).
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                events.collect { event ->
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
            }
        }
    }*/

    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {

        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins) { coinUi ->
                CoinListItem(
                    coinUI = coinUi,
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }

        }

    }
}


@PreviewLightDark
@Composable
private fun CoinListScreenPreview() {
    CapCoinTheme {
        CoinListScreen(
            state = CoinListState(
                coins = (1..10).map {
                    previewCoin.copy(id = it.toString())
                }
            ),
//            events = emptyFlow(),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}