package com.example.leagueoflegendschampions.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Champion::class], version = 1)
@TypeConverters(StatsConverter::class, TagsConverter::class)
abstract class ChampionDatabase : RoomDatabase() {

    companion object{
        fun build(context: Context)=
            Room.databaseBuilder(
                context,
                ChampionDatabase::class.java, "champion-db"
            ).build()

    }

    abstract fun championDao(): ChampionDao
}