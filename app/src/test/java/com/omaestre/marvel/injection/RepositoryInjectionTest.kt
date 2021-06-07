package com.omaestre.marvel.injection

import com.omaestre.marvel.base.utils.Constants
import com.omaestre.marvel.network.MarvelService
import com.omaestre.marvel.repository.HeroesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertTrue

class RepositoryInjectionTest : KoinTest {

    // Lazy inject property
    private val repository by inject<HeroesRepository>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(retrofitModule)
        modules(repositoryModule)
    }

    @Test
    fun injectService(): Unit = runBlocking {
        // directly request an instance
        assertNotNull(repository)

        val repository = HeroesRepository(MarvelService(Constants.SERVICEURL))
        val response = repository.getHeroes()
        val serviceResponse = repository.getMarvelService().getHeroes()

        assertNotNull(response.data)
        assertNotNull(response.data?.data)
        assertTrue {
            response.data?.data?.results?.get(0)?.name.equals(
                serviceResponse.data?.data?.results?.get(
                    0
                )?.name
            )
        }

    }
}