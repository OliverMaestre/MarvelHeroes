package com.omaestre.marvel.base.application

import android.app.Application
import android.content.Context
import android.util.Log
import com.omaestre.marvel.injection.respositoryModule
import com.omaestre.marvel.injection.retrofitModule
import com.omaestre.marvel.injection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication  : Application(){

    val applicationContext = this

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
    private  fun startInjection(){
        startKoin{
            androidContext(this@MarvelApplication)
            modules(respositoryModule)
            modules(retrofitModule)
            modules(viewModelModule)
        }
    }
    //endregion

    companion object{

        var instance:Application? = null

        fun getApplicationContext(): Context? {
            return instance?.applicationContext
        }

    }
}