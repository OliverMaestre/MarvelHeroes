package com.omaestre.core.injection

import com.google.gson.Gson
import com.omaestre.core.domain.model.ResultData
import com.omaestre.core.network.MarvelService
import com.omaestre.core.utils.MockValues
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertTrue

class ServiceInjectionTest : KoinTest {

    // Lazy inject property
    private val service by inject<MarvelService>()
    private lateinit var server : MockWebServer

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        val mockedModule = module{
            single{
                MarvelService(server.url("/").toString())
            }
        }
        loadKoinModules(mockedModule)
    }

    @Before
    fun setUp(){
        server = MockWebServer()
        server.start()
    }

    @Test
    fun injectService() {
        // directly request an instance
        assertNotNull(service)
        // Schedule some responses.
        server.enqueue(MockResponse().setResponseCode(200).setBody(MockValues.getMockJson()))
        val response = service.getHeroes()

        val mockServiceResponse =
            Gson().fromJson(MockValues.getMockJson(), ResultData::class.java) as ResultData

        assertNotNull(response.data)
        assertNotNull(response.data?.data)
        assertTrue {
            response.data?.data?.results?.get(0)?.name.equals(
                mockServiceResponse.data.results[0].name
            )
        }
    }
}