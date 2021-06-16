package com.omaestre.core.network

import com.omaestre.core.domain.model.ResultData
import com.omaestre.core.domain.net.Status


interface MarvelServiceInterface {

    fun getHeroes(): Status<ResultData>
    fun getHeroDetail(id: String): Status<ResultData>
}