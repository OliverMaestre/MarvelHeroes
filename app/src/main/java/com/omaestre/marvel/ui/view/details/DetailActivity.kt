package com.omaestre.marvel.ui.view.details

import android.os.Bundle
import android.view.View
import com.omaestre.core.base.extension.loadImage
import com.omaestre.core.base.ui.BaseActivity
import com.omaestre.core.base.utils.Constants
import com.omaestre.core.functional.Status
import com.omaestre.marvel.R
import com.omaestre.marvel.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModel<DetailViewModel>()


    //region life cycling
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
    }
    //endregion

    //region private methods
    private fun initComponents() {

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
                                val hero = it.data.results[0]
                                binding.description.text = hero.description
                                binding.name.text = hero.name
                                binding.imageHeroe.loadImage(hero.thumbnail.path + "." + hero.thumbnail.extension)

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
        //get bundle
        val id = intent.extras?.get(Constants.EXTRAID) as String
        viewModel.getHeroData(id)
    }
    //endregion
}