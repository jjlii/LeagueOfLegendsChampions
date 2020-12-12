package com.example.leagueoflegendschampions.module.server

import retrofit2.http.GET
import retrofit2.http.Path

interface LolDbService {

    @GET("{language}/champion.json")
    suspend fun listChampionsAsync(@Path("language") language: String = "en_US"): ChampionDbResult


}