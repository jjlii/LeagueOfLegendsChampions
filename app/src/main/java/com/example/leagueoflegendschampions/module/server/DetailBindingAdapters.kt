package com.example.leagueoflegendschampions.module.server

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.databinding.BindingAdapter
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.module.database.Champion
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("champion")
fun TextView.updateChampionDetails(champion: Champion?) = champion?.run {
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

@BindingAdapter("favorite")
fun FloatingActionButton.setFavorite(favorite: Boolean?){
    val icon = if(favorite == true)
        R.drawable.ic_favorite_on
    else
        R.drawable.ic_favorite_off
    setImageDrawable(ContextCompat.getDrawable(context, icon))
}