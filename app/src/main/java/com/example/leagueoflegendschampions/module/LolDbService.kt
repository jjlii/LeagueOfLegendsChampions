package com.example.leagueoflegendschampions.module

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LolDbService {

    @GET("{language}/champion.json")
    suspend fun listChampionsAsync(@Path("language") language: String = "en_US"): ChampionDbResult


}