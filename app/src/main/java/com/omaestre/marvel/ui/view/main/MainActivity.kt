package com.omaestre.marvel.ui.view.main

import android.os.Bundle
import android.view.View
import com.omaestre.core.base.ui.BaseActivity
import com.omaestre.core.domain.model.Hero
import com.omaestre.core.domain.net.Status
import com.omaestre.marvel.R
import com.omaestre.marvel.databinding.ActivityMainBinding
import com.omaestre.marvel.ui.adapter.ClickIntoView
import com.omaestre.marvel.ui.adapter.ItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), ClickIntoView {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private val listAdapter = ItemAdapter(this)


    //region life cycling
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
    }
    //endregion

    //region private methods
    private fun initComponents() {
        with(binding) {
            list.initialize(listAdapter)
        }

        viewModel.liveData.observe(this) { status ->
            when (status) {
                is Status.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
                is Status.Success -> {
                    binding.loading.visibility = View.GONE
                    status.data.let {
                        if (it != null) {
                            if (it.data.results.isNotEmpty()) {
                                listAdapter.updateItems(it.data.results)
                            }
                        }
                    }
                }
                else -> {
                    binding.loading.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE
                    status.message.let {
                        if (it != null) {
                            showSnackBarFailed(it)
                        }else{
                            showSnackBarFailed(getString(R.string.generic_error))
                        }
                    }
                }
            }
        }

        viewModel.getHeroes()
    }
    //endregion

    //region ClickIntoView implements
    override fun itemClicked(item: Hero) {
        viewModel.goToDetail(this, item.id.toString())
    }
    //endregion
}