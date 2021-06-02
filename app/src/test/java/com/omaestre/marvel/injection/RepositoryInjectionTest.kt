package com.omaestre.marvel.injection

import com.omaestre.marvel.network.MarvelService
import com.omaestre.marvel.repository.HeroesRepository
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertEquals

class RepositoryInjectionTest : KoinTest {

    // Lazy inject property
    private val repository by inject<HeroesRepository>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(retrofitModule)
        modules(respositoryModule)
    }

    @Test
    fun injectService() :  Unit = runBlocking{
        // directly request an instance
        assertNotNull(repository)

        val repository = HeroesRepository(MarvelService())
        val response = repository.getHeroes()
        val serviceResponse = repository.getMarvelService().getHeroes()

        assertEquals(response.data?.code,serviceResponse.data?.code)
        assertEquals(response.data?.status,serviceResponse.data?.status)
    }
}