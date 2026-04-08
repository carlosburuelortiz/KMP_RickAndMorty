package org.carlosburuel.tickandmorty

import androidx.compose.ui.window.ComposeUIViewController
import org.carlosburuel.tickandmorty.di.initKoin
import org.carlosburuel.tickandmorty.presentation.App
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin { }
    }) { App() }
