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
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : ScopeActivity() {

    companion object{
        const val CHAMPION = "DetailActivity:champion"
    }
    private lateinit var binding: ActivityDetailBinding
    private val  viewModel: DetailViewModel by viewModel{
        parametersOf(intent.getStringExtra(CHAMPION))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }
}