package com.omaestre.marvel.network

import com.omaestre.marvel.BuildConfig
import com.omaestre.marvel.base.utils.Constants
import com.omaestre.marvel.base.utils.Utils
import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.domain.model.ServiceResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class MarvelService : MarvelServiceInterface{

    private val service : RetrofitServiceInterface by lazy { createService() }

    //region override methods
   override fun getHeroes(): Status<ServiceResponse> {

        val ts = Calendar.getInstance().timeInMillis
        val hash = Utils.md5(ts.toString()+BuildConfig.PRIVATEKEY+BuildConfig.PUBLICKEY)

        val data = service.getHeroes(BuildConfig.PUBLICKEY,hash,ts).execute()
        return if (data.isSuccessful && data.body()!=null) {
            Status.Success(data.body()!!)
        } else {
            Status.Error(Throwable(""))
        }
    }

    override fun getHeroeDetail(id:String): Status<ServiceResponse> {

        val ts = Calendar.getInstance().timeInMillis
        val hash = Utils.md5(ts.toString()+BuildConfig.PRIVATEKEY+BuildConfig.PUBLICKEY)

        val data = service.getHeroeDetail(id,BuildConfig.PUBLICKEY,hash,ts).execute()
        return if (data.isSuccessful && data.body()!=null) {
            Status.Success(data.body()!!)
        } else {
            Status.Error(Throwable(""))
        }
    }
    //endregion

    //region private methods
    private fun createService():RetrofitServiceInterface{
        val builder = OkHttpClient.Builder()
        //set time out
        builder.readTimeout(5000, TimeUnit.SECONDS)
        // Init loggin interceptor
        if(BuildConfig.BUILD_TYPE != "release") {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
        }

        val rBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())

        //set baseURL
        rBuilder.baseUrl(Constants.SERVICEURL)

        val retrofit = rBuilder.build()
        return retrofit.create(RetrofitServiceInterface::class.java)
    }
    //endregion
}