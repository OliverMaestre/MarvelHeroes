package com.omaestre.marvel.injection

import com.omaestre.marvel.network.MarvelService
import com.omaestre.marvel.repository.HeroesRepository
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertEquals

class ServiceInjectionTest : KoinTest {

    // Lazy inject property
    private val service by inject<MarvelService>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(retrofitModule)
    }

    @Test
    fun injectService() {
        // directly request an instance
        assertNotNull(service)

        val repository = HeroesRepository(MarvelService())
        val response = service.getHeroes()

        assertEquals(response.data?.code,repository.getMarvelService().getHeroes().data?.code)
        assertEquals(response.data?.status,repository.getMarvelService().getHeroes().data?.status)
    }
}