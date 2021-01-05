package com.example.leagueoflegendschampions.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Champion::class], version = 1)
@TypeConverters(StatsConverter::class, TagsConverter::class)
abstract class ChampionDatabase : RoomDatabase() {

    abstract fun championDao(): ChampionDao
}