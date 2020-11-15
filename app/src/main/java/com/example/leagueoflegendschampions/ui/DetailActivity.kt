package com.example.leagueoflegendschampions.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.example.leagueoflegendschampions.BaseURL
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.databinding.ActivityDetailBinding
import com.example.leagueoflegendschampions.loadUrl
import com.example.leagueoflegendschampions.module.Champion

class DetailActivity : AppCompatActivity() {

    companion object{
        const val CHAMPION = "DetailActivity:champion"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<Champion>(CHAMPION)?.run {
            with(binding){
                championDetailToolbar.title = name
                championDetailImage.loadUrl(BaseURL.IMAGE_BASE_URL +image.full, 75)
                championDetailSummary.text = blurb

                championDetailInfo.text = buildSpannedString {

                    bold { append("Title: ") }
                    appendLine(title)

                    bold { append("Partype: ") }
                    appendLine(partype)

                    bold { append("Info\n") }
                    appendLine("Attack: " + info.attack)
                    appendLine("Defense: " + info.defense)
                    appendLine("Magic: "+ info.magic)
                    appendLine("Difficulty: "+info.difficulty)

                    bold { appendLine("Tags") }
                    tags.forEach {
                        appendLine(it)
                    }

                    bold { appendLine("Stats") }
                    appendLine("Hp: " + stats["hp"])
                    appendLine("Hp per level: " + stats["hpperlevel"])
                    appendLine("Mp: "+ stats["mp"])
                    appendLine("Mp per level: "+stats["mpperlevel"])
                    appendLine("Moves peed: " + stats["movespeed"])
                    appendLine("Armor: " + stats["armor"])
                    appendLine("Armor per level: "+ stats["armorperlevel"])
                    appendLine("Spell block: "+stats["spellblock"])
                    appendLine("Spell block per level: " + stats["spellblockperlevel"])
                    appendLine("Attack range: " + stats["attackrange"])
                    appendLine("Attack Speed: "+ stats["attackspeed"])

                }
            }
        }
    }
}