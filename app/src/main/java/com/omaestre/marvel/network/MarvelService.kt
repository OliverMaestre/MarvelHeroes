package com.omaestre.marvel.network

import com.omaestre.marvel.BuildConfig
import com.omaestre.marvel.base.utils.Constants
import com.omaestre.marvel.base.utils.Utils
import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.domain.model.ServiceResponse
import com.omaestre.marvel.network.interceptor.NetworkInterceptor
import com.omaestre.marvel.network.interceptor.NoConnectivityException
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
        val hash = Utils.md5(ts.toString() + BuildConfig.PRIVATEKEY + BuildConfig.PUBLICKEY)

        return try {
            val data = service.getHeroes(BuildConfig.PUBLICKEY, hash, ts).execute()
            if (data.isSuccessful && data.body() != null) {
                Status.Success(data.body()!!)
            } else {
                Status.Error()
            }
        } catch (a: NoConnectivityException) {
            Status.Error()
        }
    }

    override fun getHeroeDetail(id:String): Status<ServiceResponse> {

        val ts = Calendar.getInstance().timeInMillis
        val hash = Utils.md5(ts.toString()+BuildConfig.PRIVATEKEY+BuildConfig.PUBLICKEY)

        val data = service.getHeroeDetail(id,BuildConfig.PUBLICKEY,hash,ts).execute()
        return if (data.isSuccessful && data.body()!=null) {
            Status.Success(data.body()!!)
        } else {
            Status.Error()
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

        builder.addInterceptor(NetworkInterceptor())

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