package com.omaestre.core.repository

import com.omaestre.core.domain.model.ResultData
import com.omaestre.core.domain.net.Status


interface RepositoryInterface {

    suspend fun getHeroes(): Status<ResultData>
    suspend fun getHeroDetail(id: String): Status<ResultData>
}