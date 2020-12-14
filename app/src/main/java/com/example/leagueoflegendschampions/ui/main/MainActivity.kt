package com.example.leagueoflegendschampions.ui.main

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.leagueoflegendschampions.ChampionApp
import com.example.leagueoflegendschampions.PermissionRequester
import com.example.leagueoflegendschampions.databinding.ActivityMainBinding
import com.example.leagueoflegendschampions.module.server.ChampionRepository
import com.example.leagueoflegendschampions.ui.commun.getViewModel
import com.example.leagueoflegendschampions.ui.commun.startActivity
import com.example.leagueoflegendschampions.ui.detail.DetailActivity
import com.example.leagueoflegendschampions.ui.main.MainViewModel.UiModel
import com.example.leagueoflegendschampions.ui.main.MainViewModel.UiModel.*

class MainActivity : AppCompatActivity(){

    private lateinit var viewModel: MainViewModel
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : ChampionAdapter
    private val coarsePermissionRequester = PermissionRequester(this, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel { MainViewModel(ChampionRepository(app)) }

        adapter =ChampionAdapter(viewModel::onChampionClick)
        binding.championListView.adapter = adapter

        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.navigation.observe(this, Observer { event->
            event.getContentIfNotHandled()?.let {
                startActivity<DetailActivity>{
                    putExtra(DetailActivity.CHAMPION, it)
                }
            }
        })
    }

    private fun updateUi( model: UiModel){
        if (model !is Loading){
            binding.progress.visibility = View.GONE
        }
        when(model){
            is Content -> adapter.championList = model.champions
            is Loading -> binding.progress.visibility = View.VISIBLE
            is RequestLocationPermission -> coarsePermissionRequester.request {
                viewModel.onCoarsePermissionRequested()
            }
        }
    }
}

val Context.app: ChampionApp
    get() = applicationContext as ChampionApp


