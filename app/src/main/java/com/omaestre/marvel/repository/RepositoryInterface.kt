package com.omaestre.marvel.repository

import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.domain.model.ServiceResponse

interface RepositoryInterface {

    suspend fun getHeroes() : Status<ServiceResponse>
    suspend fun getHeroeDetail(id:String) : Status<ServiceResponse>
}