package com.example.leagueoflegendschampions.framework.server

import retrofit2.http.GET
import retrofit2.http.Path

interface ChampionDbService {

    @GET("{language}/champion.json")
    suspend fun listChampionsAsync(@Path("language") language: String = "en_US"): ChampionDbResult


}