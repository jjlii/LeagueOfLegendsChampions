package com.example.leagueoflegendschampions.ui.detail

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.example.leagueoflegendschampions.module.database.Champion

class ChampionDetailInfoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setChampion(champion: Champion) = with(champion){
        text = buildSpannedString {

            bold { append("Title: ") }
            appendLine(title)

            bold { append("Partype: ") }
            appendLine(partype)

            bold { append("Info\n") }
            appendLine("Attack: $attack")
            appendLine("Defense: $defense")
            appendLine("Magic: $magic")
            appendLine("Difficulty: $difficulty")

            bold { appendLine("Tags") }
            tags.forEach {
                appendLine(it)
            }

            bold { appendLine("Stats") }
            appendLine("Hp: " + stats["hp"])
            appendLine("Hp per level: " + stats["hpperlevel"])
            appendLine("Mp: " + stats["mp"])
            appendLine("Mp per level: " + stats["mpperlevel"])
            appendLine("Moves peed: " + stats["movespeed"])
            appendLine("Armor: " + stats["armor"])
            appendLine("Armor per level: " + stats["armorperlevel"])
            appendLine("Spell block: " + stats["spellblock"])
            appendLine("Spell block per level: " + stats["spellblockperlevel"])
            appendLine("Attack range: " + stats["attackrange"])
            appendLine("Attack Speed: " + stats["attackspeed"])

        }
    }
}