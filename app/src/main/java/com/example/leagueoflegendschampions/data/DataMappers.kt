package com.example.leagueoflegendschampions.data

import com.example.domain.Champion as DomainChampion
import com.example.leagueoflegendschampions.data.database.Champion as RoomChampion
import com.example.leagueoflegendschampions.data.server.Champion as ServerChampion
fun DomainChampion.toRoomChampion(): RoomChampion =
        RoomChampion(
                id,
                name,
                square,
                blurb,
                title,
                partype,
                attack,
                defense,
                magic,
                difficulty,
                tags,
                stats,
                favorite
        )

fun ServerChampion.toDomainChampion() = DomainChampion(
        id = id,
        name = name,
        square = image.full,
        blurb = blurb,
        title = title,
        partype = partype,
        attack = info.attack,
        defense = info.defense,
        magic = info.magic,
        difficulty = info.difficulty,
        tags = tags,
        stats = stats,
        favorite = false
)

fun RoomChampion.toDomainChampion(): DomainChampion = DomainChampion(
        id,
        name,
        square,
        blurb,
        title,
        partype,
        attack,
        defense,
        magic,
        difficulty,
        tags,
        stats,
        favorite
)

