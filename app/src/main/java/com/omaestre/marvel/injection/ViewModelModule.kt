package com.omaestre.marvel.injection

import com.omaestre.marvel.repository.RepositoryInterface
import com.omaestre.marvel.ui.view.details.DetailViewModel
import com.omaestre.marvel.ui.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
