package com.omaestre.marvel.injection

import com.omaestre.marvel.network.MarvelService
import com.omaestre.marvel.network.MarvelServiceInterface
import org.koin.dsl.bind
import org.koin.dsl.module

val retrofitModule = module {

    single {  MarvelService()} bind MarvelServiceInterface::class
}