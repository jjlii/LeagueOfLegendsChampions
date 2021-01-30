package com.example.leagueoflegendschampions.ui.main

import android.Manifest
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.databinding.ActivityMainBinding
import com.example.leagueoflegendschampions.ui.commun.EventObserver
import com.example.leagueoflegendschampions.ui.commun.PermissionRequester
import com.example.leagueoflegendschampions.ui.commun.startActivity
import com.example.leagueoflegendschampions.ui.detail.DetailActivity
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ScopeActivity(){

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : ChampionAdapter
    private val coarsePermissionRequester = PermissionRequester(this, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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




