package com.example.leagueoflegendschampions.framework


import com.example.domain.Champion
import com.example.leagueoflegendschampions.framework.database.Champion as DatabaseChampion
import com.example.leagueoflegendschampions.framework.server.Champion as ServerChampion
import com.example.leagueoflegendschampions.ui.commun.BaseURL


fun ServerChampion.toDomainChampion() : Champion = Champion(
        id = id,
        name = name,
        square = BaseURL.SQUARE_BASE_URL + image.full,
        blurb = blurb,
        title = title,
        partype = partype,
        attack = info.attack,
        defense = info.defense,
        magic = info.magic,
        difficulty = info.difficulty,
        tags = tags,
        stats = stats,
        splash = BaseURL.SPLASH_BASE_URL + id + "_0.jpg",
        favorite = false
)

fun DatabaseChampion.toDomainChampion() : Champion = Champion(
        id, name, square, blurb, title, partype, attack, defense, magic, difficulty, tags, stats, splash, favorite
)

fun Champion.toDatabaseChampion() : DatabaseChampion = DatabaseChampion(
        id, name, square, blurb, title, partype, attack, defense, magic, difficulty, tags, stats, splash, favorite
)

