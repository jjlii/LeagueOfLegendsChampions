package com.example.leagueoflegendschampions.ui.main

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.data.repository.ChampionRepository
import com.example.data.repository.RegionRepository
import com.example.leagueoflegendschampions.ui.commun.PermissionRequester
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.databinding.ActivityMainBinding
import com.example.leagueoflegendschampions.framework.AndroidPermissionChecker
import com.example.leagueoflegendschampions.framework.PlayServicesLocationDataSource
import com.example.leagueoflegendschampions.framework.database.RoomDadaSource
import com.example.leagueoflegendschampions.framework.server.ChampionDataSource
import com.example.leagueoflegendschampions.ui.commun.EventObserver
import com.example.leagueoflegendschampions.ui.commun.app
import com.example.leagueoflegendschampions.ui.commun.getViewModel
import com.example.leagueoflegendschampions.ui.commun.startActivity
import com.example.leagueoflegendschampions.ui.detail.DetailActivity
import com.example.usecases.GetChampionsUseCase

class MainActivity : AppCompatActivity(){


    private lateinit var component: MainActivityComponent
    private val viewModel: MainViewModel by lazy {
        getViewModel { component.mainViewModel }
    }
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : ChampionAdapter
    private val coarsePermissionRequester = PermissionRequester(this, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = app.component.plus(MainActivityModule())
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        adapter =ChampionAdapter(viewModel::onChampionClick)
        binding.championListView.adapter = adapter
        viewModel.navigation.observe(this, EventObserver { id->
            startActivity<DetailActivity>{
                putExtra(DetailActivity.CHAMPION, id)
            }
        })

        viewModel.requestLocationPermission.observe(this, EventObserver{
            coarsePermissionRequester.request {
                viewModel.onCoarsePermissionRequested()
            }
        })
    }
}




