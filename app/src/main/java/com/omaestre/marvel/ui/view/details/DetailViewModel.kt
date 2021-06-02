package com.omaestre.marvel.ui.view.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaestre.marvel.domain.model.ServiceResponse
import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.repository.HeroesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel (private val heroesRepository : HeroesRepository): ViewModel(){

    var liveData = MutableLiveData<Status<ServiceResponse>>()


    //region public methods
    fun getHeroeData(id : String){
        liveData.value = Status.Loading()

        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO){ heroesRepository.getHeroeDetail(id) }.let {
                liveData.value = it
            }
        }
    }
    //endregion
}