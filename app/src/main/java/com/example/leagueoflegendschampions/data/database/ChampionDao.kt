package com.example.leagueoflegendschampions.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ChampionDao {

    @Query("SELECT * FROM Champion")
    fun getAllChampions(): List<Champion>

    @Query("SELECT * FROM Champion WHERE id = :id")
    fun findChampionById(id: String): Champion

    @Query("SELECT COUNT(id) FROM Champion")
    fun championCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertChampion(champion: List<Champion>)

    @Update
    fun updateChampion(champion: Champion)

}