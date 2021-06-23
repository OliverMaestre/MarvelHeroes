package com.omaestre.data.repository


import com.omaestre.core.functional.Status
import com.omaestre.data.models.ResultData
import com.omaestre.data.network.MarvelServiceInterface


class HeroesRepository(private val service: MarvelServiceInterface) : RepositoryInterface {

    override suspend fun getHeroes(): Status<ResultData> {
        return service.getHeroes()
    }

    override suspend fun getHeroDetail(id: String): Status<ResultData> {
        return service.getHeroDetail(id)
    }

//    //for test
//    fun getMarvelService(): MarvelServiceInterface {
//        return service
//    }
}