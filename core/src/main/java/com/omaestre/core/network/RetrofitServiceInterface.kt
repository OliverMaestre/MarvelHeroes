package com.omaestre.core.network

import com.omaestre.core.domain.model.ResultData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceInterface {

    @GET("v1/public/characters")
    fun getHeroes(
        @Query("apikey") key: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long
    ): Call<ResultData>

    @GET("v1/public/characters/{heroId}")
    fun getHeroDetail(
        @Path("heroId") heroId: String,
        @Query("apikey") key: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long
    ): Call<ResultData>

}