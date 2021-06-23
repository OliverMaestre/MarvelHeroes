package com.omaestre.marvel.viewmodel

import com.omaestre.core.functional.Status
import com.omaestre.data.repository.HeroesRepository
import com.omaestre.marvel.ui.view.main.MainViewModel
import com.omaestre.marvel.utils.MockValues
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class MainViewModelTest {

    private val mainViewModel: MainViewModel = Mockito.mock(MainViewModel::class.java)
    private val heroesRepository: com.omaestre.data.repository.HeroesRepository = Mockito.mock(com.omaestre.data.repository.HeroesRepository::class.java)

    @Test
    fun getHeroes(): Unit = runBlocking {
        val response = MockValues.getServiceResponse()
        val status = Status.Success(response)
        Mockito.`when`(heroesRepository.getHeroes()).thenReturn(status)
        mainViewModel.getHeroes()
        Mockito.verify(mainViewModel, Mockito.times(1)).getHeroes()
    }
}