package com.omaestre.marvel.network

import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.injection.retrofitModule
import com.omaestre.marvel.utils.MockValues
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.Mockito
import java.io.IOException
import kotlin.test.assertTrue

class MarvelServiceTest : KoinTest{

    private val realService by inject<MarvelService>()
    private lateinit var mockService: MarvelService

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(retrofitModule)
    }

    @Before
    fun setup(){
        mockService = Mockito.mock(MarvelService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun getHeroesResponse(){
        val response = realService.getHeroes()
        assertEquals(response.data?.code, 200)
        assertEquals(response.data?.status, "Ok")

        val results = response.data?.data?.results
        assertTrue {results?.isNotEmpty() == true}
    }

    @Test
    fun getMockResponse(){
        val data = MockValues.getServiceResponse()
        val status = Status.Success(data)
        Mockito.`when`(mockService.getHeroes()).thenReturn(status)

        val response = mockService.getHeroes()

        assertEquals(response.data?.code, data.code)
        assertEquals(response.data?.status, data.status)

        val results = response.data?.data?.results
        assertTrue {results?.isNotEmpty() == true}
        assertEquals(results?.size , data.data.results.size)
    }
}