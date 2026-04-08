package org.carlosburuel.tickandmorty.di

import org.carlosburuel.tickandmorty.presentation.viewmodel.CharacterViewmodel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CharacterViewmodel(get())
    }
}
