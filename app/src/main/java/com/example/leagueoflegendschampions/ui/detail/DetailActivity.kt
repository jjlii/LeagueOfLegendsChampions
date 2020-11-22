package com.example.leagueoflegendschampions.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.example.leagueoflegendschampions.databinding.ActivityDetailBinding
import com.example.leagueoflegendschampions.module.Champion
import com.example.leagueoflegendschampions.ui.commun.BaseURL
import com.example.leagueoflegendschampions.ui.commun.loadUrl
import java.lang.IllegalStateException

class DetailActivity : AppCompatActivity() {

    companion object{
        const val CHAMPION = "DetailActivity:champion"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityDetailBinding.inflate(layoutInflater).run {
            setContentView(root)
            val champion = intent.getParcelableExtra<Champion>(CHAMPION)
                    ?: throw IllegalStateException()
            championDetailToolbar.title = champion.name
            championDetailImage.loadUrl(BaseURL.IMAGE_BASE_URL +champion.image.full, 75)
            championDetailSummary.text = champion.blurb
            championDetailInfo.setChampion(champion)
        }
    }
}