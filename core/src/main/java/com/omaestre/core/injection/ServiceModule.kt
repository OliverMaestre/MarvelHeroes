package com.omaestre.core.injection

import com.omaestre.core.base.utils.Constants
import com.omaestre.core.network.NetworkService
import org.koin.dsl.module

val serviceModule = module {

    single { NetworkService(Constants.SERVICEURL) }
}