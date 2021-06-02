package com.omaestre.marvel.utils

import com.omaestre.marvel.domain.model.*

class MockValues {

    companion object{

        fun getServiceResponse() = ServiceResponse(
            code = 200,
            status = "ok",
            data = getData()
        )

       private fun getHeroe() = Heroe(
            id = 0,
            name = "Iron Man",
            description = "El superheroe mas grande del mundo",
            thumbnail = getThumbNail(),
            comics = getList(),
            series = getList(),
            stories = getList(),
            url = "http://gateway.marvel.com/v1/public/characters/1011334"
        )

        private fun getThumbNail() = Thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
            extension = "jpg"
        )

        private fun getList() = Document(
            emptyList()
        )

        private fun getHeroesList() : List<Heroe>{
            val list = ArrayList<Heroe>()
            list.add(getHeroe())
            return list
        }

        private fun getData() = Data(
            results = getHeroesList()
        )
    }




}