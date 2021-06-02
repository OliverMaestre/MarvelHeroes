package com.omaestre.marvel.injection

import com.omaestre.marvel.repository.HeroesRepository
import org.koin.dsl.module

val respositoryModule = module{
    single { HeroesRepository(get())}
}