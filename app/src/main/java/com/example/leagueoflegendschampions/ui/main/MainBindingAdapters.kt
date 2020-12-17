package com.example.leagueoflegendschampions.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.leagueoflegendschampions.module.database.Champion

@BindingAdapter("items")
fun RecyclerView.setItems(champions: List<Champion>?){
    (adapter as? ChampionAdapter)?.let {
        it.championList = champions ?: emptyList()
    }
}