package com.omaestre.marvel.injection

import com.omaestre.marvel.base.utils.Constants
import com.omaestre.marvel.network.MarvelService
import com.omaestre.marvel.repository.HeroesRepository
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertTrue

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

        val repository = HeroesRepository(MarvelService(Constants.SERVICEURL))
        val response = service.getHeroes()

        assertNotNull(response.data)
        assertNotNull(response.data?.data)
        assertTrue {
            response.data?.data?.results?.get(0)?.name.equals(
                repository.getMarvelService().getHeroes().data?.data?.results?.get(
                    0
                )?.name
            )
        }
    }
}