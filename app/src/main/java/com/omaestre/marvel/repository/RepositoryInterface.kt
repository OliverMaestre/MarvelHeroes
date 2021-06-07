package com.omaestre.marvel.repository

import com.omaestre.marvel.domain.model.ResultData
import com.omaestre.marvel.domain.net.Status

interface RepositoryInterface {

    suspend fun getHeroes(): Status<ResultData>
    suspend fun getHeroDetail(id: String): Status<ResultData>
}