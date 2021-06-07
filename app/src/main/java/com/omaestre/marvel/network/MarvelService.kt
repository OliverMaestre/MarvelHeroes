package com.omaestre.marvel.network

import com.omaestre.marvel.BuildConfig
import com.omaestre.marvel.base.utils.Utils
import com.omaestre.marvel.domain.model.ResultData
import com.omaestre.marvel.domain.net.Status
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class MarvelService(baseUrl: String) : MarvelServiceInterface {

    private val service: RetrofitServiceInterface by lazy { createService(baseUrl) }

    //region override methods
    override fun getHeroes(): Status<ResultData> {
        return callService(false, "")
    }

    override fun getHeroDetail(id: String): Status<ResultData> {
        return callService(true, id)
    }
    //endregion

    //region private methods
    private fun createService(baseUrl: String): RetrofitServiceInterface {
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

        val retrofit = rBuilder.build()
        return retrofit.create(RetrofitServiceInterface::class.java)
    }

    private fun callService(isDetail: Boolean, id: String): Status<ResultData> {

        val ts = Calendar.getInstance().timeInMillis
        val hash = Utils.md5(ts.toString() + BuildConfig.PRIVATEKEY + BuildConfig.PUBLICKEY)

        return try {

            val data = if (isDetail) {
                service.getHeroDetail(id, BuildConfig.PUBLICKEY, hash, ts).execute()
            } else {
                service.getHeroes(BuildConfig.PUBLICKEY, hash, ts).execute()
            }
            if (data.isSuccessful && data.body() != null) {
                Status.Success(data.body()!!)
            } else {
                Status.Error()
            }
        } catch (a: IOException) {
            Status.Error()
        } catch (a: RuntimeException) {
            Status.Error()
        }
    }
    //endregion
}