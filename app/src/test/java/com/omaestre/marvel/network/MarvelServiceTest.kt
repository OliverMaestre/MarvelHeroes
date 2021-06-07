package com.omaestre.marvel.network

import com.google.gson.Gson
import com.omaestre.marvel.domain.model.ResultData
import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.utils.MockValues
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertTrue


class MarvelServiceTest{

    private lateinit var mockService: MarvelService
    private lateinit var server : MockWebServer


    @Before
    fun setup(){
        mockService = Mockito.mock(MarvelService::class.java)
        server = MockWebServer()
        // Start the server.
        server.start()
    }

    @Test
    fun getMockResponse(){
        val data = MockValues.getServiceResponse()
        val status = Status.Success(data)
        Mockito.`when`(mockService.getHeroes()).thenReturn(status)

        val response = mockService.getHeroes()

        val results = response.data?.data?.results
        assertTrue {results?.isNotEmpty() == true}
        assertEquals(results?.size , data.data.results.size)
    }

    @Test
    @Throws(Exception::class)
    fun getHeroesTest() {

        // Schedule some responses.
        server.enqueue(MockResponse().setResponseCode(200).setBody(MockValues.getMockJson()))

        // Ask the server for its URL.
        val baseUrl = server.url("/")

        val service = MarvelService(baseUrl.toString())
        val response = service.getHeroes()
        assertNotNull(response)
        assertNotNull(response.data)


        // check request
        val request = server.takeRequest()
        assertEquals("/v1/public/characters", request.path?.substring(0, request.path!!.indexOf("?")))
    }

    @Test
    @Throws(Exception::class)
    fun getHeroDetailTest() {

        // Schedule some responses.
        server.enqueue(MockResponse().setResponseCode(200).setBody(MockValues.getMockJson()))

        // Ask the server for its URL.
        val baseUrl = server.url("/")

        val service = MarvelService(baseUrl.toString())
        val serviceResponse = service.getHeroDetail("1011334")
        assertNotNull(serviceResponse)
        assertNotNull(serviceResponse.data)


        // check request
        val request = server.takeRequest()
        assertEquals("/v1/public/characters/1011334", request.path?.substring(0, request.path!!.indexOf("?")))

        //check response
        val mockServiceResponse = Gson().fromJson(MockValues.getMockJson(), ResultData::class.java) as ResultData

        assertEquals( mockServiceResponse.data.results[0].name,
            serviceResponse.data?.data?.results?.get(0)?.name
        )
    }


    @After
    fun tearDown() {
        server.shutdown()
    }
}