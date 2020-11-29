package com.example.leagueoflegendschampions.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.leagueoflegendschampions.databinding.ActivityDetailBinding
import com.example.leagueoflegendschampions.module.Champion
import com.example.leagueoflegendschampions.ui.commun.BaseURL
import com.example.leagueoflegendschampions.ui.commun.loadUrl
import com.example.leagueoflegendschampions.ui.detail.DetailViewModel.UiModel
import java.lang.IllegalStateException

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
        val champion: Champion =
                intent.getParcelableExtra<Champion>(CHAMPION)
                        ?: throw IllegalStateException("Champion not found!")
        viewModel = ViewModelProvider(this, DetailViewModelFactory(champion)).get()

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: UiModel) =
            with(binding){
                val champion = model.champion
                championDetailToolbar.title = champion.name
                championDetailImage.loadUrl(BaseURL.IMAGE_BASE_URL +champion.image.full, 75)
                championDetailSummary.text = champion.blurb
                championDetailInfo.setChampion(champion)
            }
}