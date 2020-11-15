package com.example.leagueoflegendschampions.ui

import android.os.Bundle
import android.view.View
import com.example.leagueoflegendschampions.databinding.ActivityMainBinding
import com.example.leagueoflegendschampions.module.LolDb
import com.example.leagueoflegendschampions.startActivity
import com.example.leagueoflegendschampions.toast
import kotlinx.coroutines.launch

class MainActivity : CoroutineScopeActivity() {

    private val adapter = ChampionAdapter{
        startActivity<DetailActivity>{
            putExtra(DetailActivity.CHAMPION, it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launch {
            binding.progress.visibility = View.VISIBLE
            val championList = LolDb.service.listChampionsAsync()
            adapter.championList = championList.data.values.toList()
            binding.progress.visibility = View.GONE
        }
        binding.championListView.adapter = adapter
    }
}