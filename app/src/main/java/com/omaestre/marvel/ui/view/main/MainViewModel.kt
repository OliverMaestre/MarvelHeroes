package com.omaestre.marvel.ui.view.main

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaestre.marvel.base.ui.BaseActivity
import com.omaestre.marvel.base.utils.Constants
import com.omaestre.marvel.domain.model.ServiceResponse
import com.omaestre.marvel.domain.net.Status
import com.omaestre.marvel.repository.HeroesRepository
import com.omaestre.marvel.ui.view.details.DetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val heroesRepository : HeroesRepository) : ViewModel(){

    var liveData = MutableLiveData<Status<ServiceResponse>>()


    //region public methods
    fun getHeroes(){
        liveData.value = Status.Loading()

        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.Main){
              //Show loading
            }
           withContext(Dispatchers.IO){ heroesRepository.getHeroes() }.let {
               liveData.value = it
           }
        }
    }

    fun goToDetail(context : BaseActivity,id: String){
        val intent = Intent(context,DetailActivity::class.java).apply {
            putExtra(Constants.EXTRAID,id)
        }
        context.startActivity(intent)
    }
    //endregion

}