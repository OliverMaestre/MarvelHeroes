package com.omaestre.marvel.viewmodel

import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.repository.HeroesRepository
import com.omaestre.marvel.ui.view.details.DetailViewModel
import com.omaestre.marvel.utils.MockValues
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class DetailViewModelTest {

    private val detailViewModel : DetailViewModel = Mockito.mock(DetailViewModel::class.java)
    private val heroesRepository : HeroesRepository = Mockito.mock(HeroesRepository::class.java)

    @Test
    fun getHeroeData(): Unit = runBlocking{
        val response = MockValues.getServiceResponse()
        val status = Status.Success(response)
        Mockito.`when`(heroesRepository.getHeroDetail("2")).thenReturn(status)
        detailViewModel.getHeroData("2")
        Mockito.verify(detailViewModel,Mockito.times(1)).getHeroData("2")
    }
}