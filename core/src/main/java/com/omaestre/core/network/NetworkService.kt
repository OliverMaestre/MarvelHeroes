package com.omaestre.core.network
import com.omaestre.core.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkService(baseUrl: String){

    private val retrofit = createService(baseUrl)

    fun getNetworkProvider() : Retrofit{
        return retrofit
    }

    //region private methods
    private fun createService(baseUrl: String):  Retrofit{
        val builder = OkHttpClient.Builder()
        //set time out
        builder.readTimeout(5000, TimeUnit.SECONDS)
        // Init logging interceptor
        if (BuildConfig.BUILD_TYPE != "release") {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
        }

        val rBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())

        //set baseURL
        rBuilder.baseUrl(baseUrl)

        return rBuilder.build()
    }

}