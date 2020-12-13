package com.example.leagueoflegendschampions.module.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Champion::class], version = 1)
abstract class ChampionDatabase : RoomDatabase() {

    abstract fun championDao(): Champion
}