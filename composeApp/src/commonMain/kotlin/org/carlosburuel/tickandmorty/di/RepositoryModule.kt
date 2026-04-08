package org.carlosburuel.tickandmorty.di

import org.carlosburuel.tickandmorty.data.repository.CharacterRepositoryImpl
import org.carlosburuel.tickandmorty.domain.repository.CharacterRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CharacterRepository> {
        CharacterRepositoryImpl(get())
    }
}
