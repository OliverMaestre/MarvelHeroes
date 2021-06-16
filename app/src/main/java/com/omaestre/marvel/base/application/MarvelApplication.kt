package com.omaestre.marvel.base.application

import android.app.Application
import android.content.Context
import com.omaestre.marvel.base.injection.repositoryModule
import com.omaestre.marvel.base.injection.retrofitModule
import com.omaestre.marvel.base.injection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    //region override methods
    override fun onCreate() {
        super.onCreate()
        //start koin
        startInjection()
    }
    //endregion

    //region private methods
    private fun startInjection() {
        startKoin {
            androidContext(this@MarvelApplication)
            modules(repositoryModule)
            modules(retrofitModule)
            modules(viewModelModule)
        }
    }
    //endregion
}