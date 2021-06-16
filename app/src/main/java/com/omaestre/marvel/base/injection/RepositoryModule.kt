package com.omaestre.marvel.base.injection

import com.omaestre.core.repository.HeroesRepository
import com.omaestre.core.repository.RepositoryInterface
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single { HeroesRepository(get()) } bind RepositoryInterface::class
}