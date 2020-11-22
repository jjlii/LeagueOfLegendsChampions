package com.example.leagueoflegendschampions.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leagueoflegendschampions.databinding.ActivityDetailBinding
import com.example.leagueoflegendschampions.module.Champion
import com.example.leagueoflegendschampions.ui.commun.BaseURL
import com.example.leagueoflegendschampions.ui.commun.loadUrl

class DetailActivity : AppCompatActivity(), DetailPresenter.View {

    companion object{
        const val CHAMPION = "DetailActivity:champion"
    }
    private lateinit var binding: ActivityDetailBinding
    private val  presenter = DetailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.onCreate(this, intent.getParcelableExtra<Champion>(CHAMPION))
    }

    override fun updateUI(champion: Champion?) {
        champion?.let {
            with(binding){
                championDetailToolbar.title = champion.name
                championDetailImage.loadUrl(BaseURL.IMAGE_BASE_URL +champion.image.full, 75)
                championDetailSummary.text = champion.blurb
                championDetailInfo.setChampion(champion)
            }
        }?: throw IllegalStateException()
    }
}