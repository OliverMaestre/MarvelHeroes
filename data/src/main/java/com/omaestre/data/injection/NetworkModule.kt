package com.omaestre.data.injection

import com.omaestre.data.network.MarvelService
import com.omaestre.data.network.MarvelServiceInterface
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {

    single { MarvelService(get()) } bind MarvelServiceInterface::class
}