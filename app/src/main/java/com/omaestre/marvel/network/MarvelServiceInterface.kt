package com.omaestre.marvel.network

import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.domain.model.ServiceResponse

interface MarvelServiceInterface {

    fun getHeroes(): Status<ServiceResponse>
    fun getHeroeDetail(id:String): Status<ServiceResponse>
}