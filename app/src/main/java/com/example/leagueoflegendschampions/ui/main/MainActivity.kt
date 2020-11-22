package com.example.leagueoflegendschampions.ui.main

import android.os.Bundle
import android.view.View
import com.example.leagueoflegendschampions.databinding.ActivityMainBinding
import com.example.leagueoflegendschampions.module.ChampionRepository
import com.example.leagueoflegendschampions.ui.commun.CoroutineScopeActivity
import com.example.leagueoflegendschampions.ui.detail.DetailActivity
import com.example.leagueoflegendschampions.ui.commun.startActivity
import kotlinx.coroutines.launch

class MainActivity : CoroutineScopeActivity() {

    private val championRepository: ChampionRepository by lazy { ChampionRepository(this) }


    private val adapter = ChampionAdapter {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.CHAMPION, it)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launch {
            binding.progress.visibility = View.VISIBLE
            val championList = championRepository.getChampions()
            adapter.championList = championList.data.values.toList()
            binding.progress.visibility = View.GONE
        }
        binding.championListView.adapter = adapter
    }
}


