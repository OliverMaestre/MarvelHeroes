package com.omaestre.marvel.repository

import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.domain.model.ServiceResponse
import com.omaestre.marvel.network.MarvelService
import com.omaestre.marvel.network.MarvelServiceInterface

class HeroesRepository (private val service : MarvelServiceInterface) : RepositoryInterface {

    override suspend fun getHeroes() : Status<ServiceResponse> {
        return service.getHeroes()
    }

    override suspend fun getHeroeDetail(id: String) : Status<ServiceResponse> {
        return service.getHeroeDetail(id)
    }

    //for test
    fun getMarvelService(): MarvelServiceInterface{
        return service
    }
}