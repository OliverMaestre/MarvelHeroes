package com.omaestre.data.utils

import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

class MockValues {

    companion object {

        fun getMockJson(): String {
            val inputStream = this::class.java.classLoader?.getResourceAsStream("response.json")
            val source = inputStream?.let { inputStream.source().buffer() }
            return source?.readString(StandardCharsets.UTF_8) ?: ""

        }

        fun getServiceResponse() = com.omaestre.data.models.ResultData(
            data = getData()
        )

        private fun getHero() = com.omaestre.data.models.Hero(
            id = 0,
            name = "Iron Man",
            description = "El super heroe mas grande del mundo",
            thumbnail = getThumbNail(),
            comics = getList(),
            series = getList(),
            stories = getList(),
            url = "http://gateway.marvel.com/v1/public/characters/1011334"
        )

        private fun getThumbNail() = com.omaestre.data.models.Thumbnail(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
            extension = "jpg"
        )

        private fun getList() = com.omaestre.data.models.Document(
            emptyList()
        )

        private fun getHeroesList(): List<com.omaestre.data.models.Hero> {
            val list = ArrayList<com.omaestre.data.models.Hero>()
            list.add(getHero())
            return list
        }

        private fun getData() = com.omaestre.data.models.Data(
            results = getHeroesList()
        )
    }


}