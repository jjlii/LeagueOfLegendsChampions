package com.example.leagueoflegendschampions.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Champion::class], version = 1, exportSchema = false)
@TypeConverters(StatsConverter::class, TagsConverter::class)
abstract class ChampionDatabase : RoomDatabase() {

    abstract fun championDao(): ChampionDao
}