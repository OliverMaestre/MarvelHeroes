package com.omaestre.marvel.injection

import com.omaestre.marvel.repository.HeroesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HeroesRepository(get()) }
}