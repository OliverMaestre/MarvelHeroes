package com.omaestre.marvel.base.application

import android.app.Application
import android.content.Context
import com.omaestre.marvel.injection.repositoryModule
import com.omaestre.marvel.injection.retrofitModule
import com.omaestre.marvel.injection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    //region override methods
    override fun onCreate() {
        super.onCreate()
        //save instance
        instance = this
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

    companion object {

        var instance: Application? = null

        fun getApplicationContext(): Context? {
            return instance?.applicationContext
        }
    }
}