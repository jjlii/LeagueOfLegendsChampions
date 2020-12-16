package com.example.leagueoflegendschampions.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.databinding.ActivityDetailBinding
import com.example.leagueoflegendschampions.module.server.Champion
import com.example.leagueoflegendschampions.module.server.ChampionRepository
import com.example.leagueoflegendschampions.ui.commun.BaseURL
import com.example.leagueoflegendschampions.ui.commun.getViewModel
import com.example.leagueoflegendschampions.ui.commun.loadUrl
import com.example.leagueoflegendschampions.ui.detail.DetailViewModel.UiModel
import com.example.leagueoflegendschampions.ui.main.app

class DetailActivity : AppCompatActivity() {

    companion object{
        const val CHAMPION = "DetailActivity:champion"
    }
    private lateinit var binding: ActivityDetailBinding
    private lateinit var  viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel { DetailViewModel(intent.getStringExtra(CHAMPION) ?: "", ChampionRepository(app)) }

        viewModel.model.observe(this, Observer(::updateUi))

        binding.championDetailFavorite.setOnClickListener {
            viewModel.onFavoriteClicked()
        }
    }

    private fun updateUi(model: UiModel) =
            with(binding){
                val champion = model.champion
                championDetailToolbar.title = champion.name
                championDetailImage.loadUrl(BaseURL.SPLASH_BASE_URL + champion.id + "_0.jpg", 500)
                championDetailSummary.text = champion.blurb
                championDetailInfo.setChampion(champion)
                val icon = if(champion.favorite)
                    R.drawable.ic_favorite_on
                else
                    R.drawable.ic_favorite_off

                championDetailFavorite.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, icon))
            }
}