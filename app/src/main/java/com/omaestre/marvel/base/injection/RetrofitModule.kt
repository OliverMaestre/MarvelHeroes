package com.omaestre.marvel.base.injection

import com.omaestre.core.base.utils.Constants
import com.omaestre.core.network.MarvelService
import com.omaestre.core.network.MarvelServiceInterface
import org.koin.dsl.bind
import org.koin.dsl.module

val retrofitModule = module {

    single { MarvelService(Constants.SERVICEURL) } bind MarvelServiceInterface::class
}