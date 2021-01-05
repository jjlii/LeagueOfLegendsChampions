package com.example.leagueoflegendschampions.framework.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object StatsConverter {

    @TypeConverter
    @JvmStatic
    fun stringToStats(value: String): Map<String, Double>{
        return Gson().fromJson(value, object : TypeToken<Map<String, Double>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun statsToString(value: Map<String, Double>): String{
        return Gson().toJson(value)
    }
}

object TagsConverter{

    @TypeConverter
    @JvmStatic
    fun stringToTags(value: String): List<String>{
        return Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun tagsToString(value: List<String>): String{
        return Gson().toJson(value)
    }

}

