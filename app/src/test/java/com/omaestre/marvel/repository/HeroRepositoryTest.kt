package com.omaestre.marvel.repository

import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.utils.MockValues
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.mockito.Mockito

class HeroRepositoryTest {


    private val heroesRepository: HeroesRepository = Mockito.mock(HeroesRepository::class.java)
    private val response = MockValues.getServiceResponse()
    private val status = Status.Success(response)

    @Test
    fun getHeroes(): Unit = runBlocking {

        Mockito.`when`(heroesRepository.getHeroes()).thenReturn(status)
        val info = heroesRepository.getHeroes()
        Mockito.verify(heroesRepository, Mockito.times(1)).getHeroes()
        MatcherAssert.assertThat(status, `is`(info))
    }

    @Test
    fun getHeroData(): Unit = runBlocking {
        Mockito.`when`(heroesRepository.getHeroDetail("0")).thenReturn(status)
        val info = heroesRepository.getHeroDetail("0")
        Mockito.verify(heroesRepository, Mockito.times(1)).getHeroDetail("0")
        MatcherAssert.assertThat(status, `is`(info))
    }

}