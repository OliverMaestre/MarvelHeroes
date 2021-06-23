package com.omaestre.data.repository

import com.omaestre.core.functional.Status
import com.omaestre.data.models.ResultData


interface RepositoryInterface {

    suspend fun getHeroes(): Status<ResultData>
    suspend fun getHeroDetail(id: String): Status<ResultData>
}