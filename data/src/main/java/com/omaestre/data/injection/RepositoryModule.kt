package com.omaestre.data.injection

import com.omaestre.data.repository.HeroesRepository
import com.omaestre.data.repository.RepositoryInterface
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single { HeroesRepository(get()) } bind RepositoryInterface::class
}