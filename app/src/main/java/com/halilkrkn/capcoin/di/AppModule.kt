package com.halilkrkn.capcoin.di

import com.halilkrkn.capcoin.core.data.networking.HttpClientFactory
import com.halilkrkn.capcoin.data.networking.RemoteCoinDataSource
import com.halilkrkn.capcoin.domain.CoinDataSource
import com.halilkrkn.capcoin.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    // Networking
    single { HttpClientFactory.create(CIO.create()) }

    // Data sources
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    // ViewModels
    viewModelOf(::CoinListViewModel)




}