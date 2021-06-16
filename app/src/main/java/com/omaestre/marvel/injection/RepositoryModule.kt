package com.omaestre.marvel.injection

import com.omaestre.marvel.repository.HeroesRepository
import com.omaestre.marvel.repository.RepositoryInterface
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single { HeroesRepository(get()) } bind RepositoryInterface::class
}