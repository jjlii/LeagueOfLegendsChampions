package com.example.leagueoflegendschampions.data.server

import retrofit2.http.GET
import retrofit2.http.Path

interface ChampionsDbService {

    @GET("{language}/champion.json")
    suspend fun listChampionsAsync(@Path("language") language: String = "en_US"): ChampionDbResult


}