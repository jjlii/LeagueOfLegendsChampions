package com.example.leagueoflegendschampions.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.databinding.ChampionItemBinding
import com.example.leagueoflegendschampions.module.Champion
import kotlin.properties.Delegates

class ChampionAdapter(private val listener: (Champion)-> Unit )
    : RecyclerView.Adapter<ChampionAdapter.ViewHolder>(){

    var championList: List<Champion> by Delegates.observable(emptyList()){ _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].id == new[newItemPosition].id
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.champion_item, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val champion = championList[position]
        holder.bind(champion)
        holder.itemView.setOnClickListener {
            listener(champion)
        }
    }

    override fun getItemCount(): Int =
        championList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ChampionItemBinding.bind(view)
        fun bind(champion: Champion){
            with(binding){
                championName.text = champion.name
            }
        }
    }
}