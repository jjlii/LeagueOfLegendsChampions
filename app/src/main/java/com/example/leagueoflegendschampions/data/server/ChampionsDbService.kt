package com.example.leagueoflegendschampions.data.server

import retrofit2.http.GET
import retrofit2.http.Path

interface ChampionsDbService {

    @GET("10.23.1/data/{language}/champion.json")
    suspend fun listChampionsAsync(@Path("language") language: String = "en_US"): ChampionDbResult

    @GET("languages.json")
    suspend fun languageAsync(): List<String>


}