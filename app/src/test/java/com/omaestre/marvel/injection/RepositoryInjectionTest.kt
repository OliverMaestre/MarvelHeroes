package com.omaestre.marvel.injection

import com.google.gson.Gson
import com.omaestre.marvel.base.utils.Constants
import com.omaestre.marvel.domain.model.ResultData
import com.omaestre.marvel.network.MarvelService
import com.omaestre.marvel.repository.HeroesRepository
import com.omaestre.marvel.utils.MockValues
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertTrue

class RepositoryInjectionTest : KoinTest {

    // Lazy inject property
    private val repository by inject<HeroesRepository>()
    private lateinit var server : MockWebServer

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(retrofitModule)
        modules(repositoryModule)
    }


    @Before
    fun setUp(){
        server = MockWebServer()
        server.start()
    }

    @Test
    fun injectService(): Unit = runBlocking {
        // directly request an instance
        assertNotNull(repository)

        // Schedule some responses.
        server.enqueue(MockResponse().setResponseCode(200).setBody(MockValues.getMockJson()))

        // Ask the server for its URL.
        val baseUrl = server.url("/")


        val repository = HeroesRepository(MarvelService(baseUrl.toString()))
        val response = repository.getHeroes()

        assertNotNull(response.data)
        assertNotNull(response.data?.data)

        val mockServiceResponse =
            Gson().fromJson(MockValues.getMockJson(), ResultData::class.java) as ResultData

        assertTrue {
            response.data?.data?.results?.get(0)?.name.equals(
                mockServiceResponse.data.results[0].name
            )
        }

    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}