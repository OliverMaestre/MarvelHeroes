package com.omaestre.marvel.utils

import com.omaestre.marvel.domain.model.*
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

class MockValues {

    companion object {

        fun getMockJson(): String {
            val inputStream = javaClass.classLoader?.getResourceAsStream("response.json")
            val source = inputStream?.let { inputStream.source().buffer() }
            return source?.readString(StandardCharsets.UTF_8) ?: ""

        }

        fun getServiceResponse() = ResultData(
            data = getData()
        )

        private fun getHero() = Hero(
            id = 0,
            name = "Iron Man",
            description = "El super heroe mas grande del mundo",
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

        private fun getHeroesList(): List<Hero> {
            val list = ArrayList<Hero>()
            list.add(getHero())
            return list
        }

        private fun getData() = Data(
            results = getHeroesList()
        )
    }


}