package com.omaestre.marvel.network

import com.omaestre.marvel.domain.model.ResultData
import com.omaestre.marvel.domain.net.Status

interface MarvelServiceInterface {

    fun getHeroes(): Status<ResultData>
    fun getHeroDetail(id: String): Status<ResultData>
}