package com.halilkrkn.capcoin.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> ObserveAsEvents(
    events: Flow<T>,
    key1: Any? = null,
    key2: Any? = null,
    onEvent: (T) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    // Bu yapı, MVVM mimarisinde ViewModel'den gelen hata eventlerini kullanıcıya göstermek için kullanılan REACTIVE bir PATTERN'dir
    LaunchedEffect(key1 = lifecycleOwner.lifecycle, key1, key2) {
        // Komponentin STARTED durumunda olduğunda çalışır
        // Komponent arka plana geçtiğinde otomatik olarak durur ve öne geldiğinde devam eder(tekar çalışır).
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                events.collect(onEvent)
            }
        }
    }
}