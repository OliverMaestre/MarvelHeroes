package com.omaestre.marvel.network.interceptor

import com.omaestre.marvel.base.application.MarvelApplication
import com.omaestre.marvel.base.utils.Utils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class NetworkInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!Utils.checkInternetConnection(MarvelApplication.getApplicationContext())) {
            throw NoConnectivityException()
        } else {
            val builder: Request.Builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        }
    }
}

class NoConnectivityException : IOException() {
    override val message = "No connectivity exception"
}