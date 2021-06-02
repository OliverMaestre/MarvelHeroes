package com.omaestre.marvel.network

import com.omaestre.marvel.domain.model.ServiceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceInterface {

    @GET("v1/public/characters")
    fun getHeroes (@Query("apikey") key : String,
                           @Query("hash") hash : String,
                           @Query("ts") ts : Long) : Call<ServiceResponse>

    @GET("v1/public/characters/{heroeId}")
    fun getHeroeDetail (@Path("heroeId") heroeId:String,
                        @Query("apikey") key : String,
                        @Query("hash") hash : String,
                        @Query("ts") ts : Long) : Call<ServiceResponse>

}