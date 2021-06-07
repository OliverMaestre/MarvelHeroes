package com.omaestre.marvel.repository

import com.omaestre.marvel.domain.model.ResultData
import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.network.MarvelServiceInterface

class HeroesRepository(private val service: MarvelServiceInterface) : RepositoryInterface {

    override suspend fun getHeroes(): Status<ResultData> {
        return service.getHeroes()
    }

    override suspend fun getHeroDetail(id: String): Status<ResultData> {
        return service.getHeroDetail(id)
    }

    //for test
    fun getMarvelService(): MarvelServiceInterface {
        return service
    }
}