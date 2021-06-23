package com.omaestre.data.network

import com.omaestre.core.functional.Status
import com.omaestre.data.models.ResultData


interface MarvelServiceInterface {

    fun getHeroes(): Status<ResultData>
    fun getHeroDetail(id: String): Status<ResultData>
}