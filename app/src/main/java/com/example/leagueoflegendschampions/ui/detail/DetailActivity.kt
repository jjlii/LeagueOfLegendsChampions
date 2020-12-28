package com.example.leagueoflegendschampions.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.databinding.ActivityDetailBinding
import com.example.leagueoflegendschampions.module.server.ChampionRepository
import com.example.leagueoflegendschampions.ui.commun.app
import com.example.leagueoflegendschampions.ui.commun.getViewModel

class DetailActivity : AppCompatActivity() {

    companion object{
        const val CHAMPION = "DetailActivity:champion"
    }
    private lateinit var binding: ActivityDetailBinding
    private lateinit var  viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        viewModel = getViewModel { DetailViewModel(intent.getStringExtra(CHAMPION) ?: "", ChampionRepository(app)) }

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }
}