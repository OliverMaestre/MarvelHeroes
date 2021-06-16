package com.omaestre.marvel.viewmodel

import com.omaestre.core.domain.net.Status
import com.omaestre.core.repository.HeroesRepository
import com.omaestre.marvel.ui.view.main.MainViewModel
import com.omaestre.marvel.utils.MockValues
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class MainViewModelTest {

    private val mainViewModel: MainViewModel = Mockito.mock(MainViewModel::class.java)
    private val heroesRepository: HeroesRepository = Mockito.mock(HeroesRepository::class.java)

    @Test
    fun getHeroes(): Unit = runBlocking {
        val response = MockValues.getServiceResponse()
        val status = Status.Success(response)
        Mockito.`when`(heroesRepository.getHeroes()).thenReturn(status)
        mainViewModel.getHeroes()
        Mockito.verify(mainViewModel, Mockito.times(1)).getHeroes()
    }
}