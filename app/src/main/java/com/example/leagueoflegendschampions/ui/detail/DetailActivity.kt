package com.example.leagueoflegendschampions.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.data.repository.ChampionRepository
import com.example.data.repository.RegionRepository
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.databinding.ActivityDetailBinding
import com.example.leagueoflegendschampions.framework.AndroidPermissionChecker
import com.example.leagueoflegendschampions.framework.PlayServicesLocationDataSource
import com.example.leagueoflegendschampions.framework.database.RoomDadaSource
import com.example.leagueoflegendschampions.framework.server.ChampionDataSource
import com.example.leagueoflegendschampions.ui.commun.app
import com.example.leagueoflegendschampions.ui.commun.getViewModel
import com.example.usecases.FindChampionByIdUseCase
import com.example.usecases.ToggleChampionFavoriteUseCase

class DetailActivity : AppCompatActivity() {

    companion object{
        const val CHAMPION = "DetailActivity:champion"
    }
    private lateinit var binding: ActivityDetailBinding
    private lateinit var  viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        val championRepository = ChampionRepository(ChampionDataSource()
            , RoomDadaSource(app.db)
            , RegionRepository(PlayServicesLocationDataSource(app), AndroidPermissionChecker(app))
        )
        viewModel = getViewModel {
            DetailViewModel(intent.getStringExtra(CHAMPION) ?: "",
                FindChampionByIdUseCase(championRepository)
                , ToggleChampionFavoriteUseCase(championRepository)
            ) }

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }
}