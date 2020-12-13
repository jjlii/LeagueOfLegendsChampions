package com.example.leagueoflegendschampions.module.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Champion(
        @PrimaryKey
        val id: Int,
        val name: String,
        val square: String,
        val blurb: String,
        val title: String,
        val partype: String,
        val attack: Long,
        val defense: Long,
        val magic: Long,
        val difficulty: Long,
        val tags: List<String>,
        val stats: Map<String, Double>,
        val favorite: Boolean
)