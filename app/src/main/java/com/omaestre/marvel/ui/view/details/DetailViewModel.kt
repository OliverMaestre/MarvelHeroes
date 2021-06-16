package com.omaestre.marvel.ui.view.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaestre.marvel.domain.model.ResultData
import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.repository.HeroesRepository
import com.omaestre.marvel.repository.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val heroesRepository: RepositoryInterface) : ViewModel() {

    var liveData = MutableLiveData<Status<ResultData>>()


    //region public methods
    fun getHeroData(id: String) {
        liveData.value = Status.Loading()

        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) { heroesRepository.getHeroDetail(id) }.let {
                liveData.value = it
            }
        }
    }
    //endregion
}