package com.example.leagueoflegendschampions.module

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ChampionDbResult (
    val type: String,
    val format: String,
    val version: String,
    val data: Map<String, Champion>
)

@Parcelize
data class Champion(
    val version: String,
    val id: String,
    val key: String,
    val name: String,
    val title: String,
    val blurb: String,
    val info: Info,
    val image: Image,
    val tags: List<String>,
    val partype: String,
    val stats: Map<String, Double>
) : Parcelable

@Parcelize
data class Info(
    val attack: Long,
    val defense: Long,
    val magic: Long,
    val difficulty: Long
) : Parcelable

@Parcelize
data class Image (
    val full: String,
    val sprite: String,
    val group: String,
    val x: Long,
    val y: Long,
    val w: Long,
    val h: Long
) : Parcelable