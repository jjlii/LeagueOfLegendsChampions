package com.example.leagueoflegendschampions.module.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class Champion(
        @PrimaryKey
        val id: String,
        val name: String,
        val square: String,
        val blurb: String,
        val title: String,
        val partype: String,
        val attack: Long,
        val defense: Long,
        val magic: Long,
        val difficulty: Long,
        @TypeConverters(TagsConverter::class)
        val tags: List<String>,
        @TypeConverters(StatsConverter::class)
        val stats: Map<String, Double>,
        val favorite: Boolean
)