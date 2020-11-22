package com.example.leagueoflegendschampions.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leagueoflegendschampions.databinding.ActivityMainBinding
import com.example.leagueoflegendschampions.module.Champion
import com.example.leagueoflegendschampions.module.ChampionRepository
import com.example.leagueoflegendschampions.ui.detail.DetailActivity
import com.example.leagueoflegendschampions.ui.commun.startActivity

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private  val presenter by lazy {  MainPresenter(ChampionRepository(this))}
    private lateinit var binding : ActivityMainBinding

    private val adapter = ChampionAdapter {
        presenter.onChampionClick(it)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onCreate(this)
        binding.championListView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showProgress() {
        binding.progress.visibility = android.view.View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.visibility = android.view.View.GONE
    }

    override fun updateData(champions: List<Champion>) {
        adapter.championList = champions
    }

    override fun navigateTo(champion: Champion) {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.CHAMPION, champion)
        }
    }
}


