package com.example.domain

data class Champion(
        val id: String,
        val name: String,
        val square: String,
        val blurb: String,
        val title: String,
        val partype: String,
        val attack: Long,
        val defense: Long,
        val magic: Long,
        val difficulty: Long,
        val tags: List<String>,
        val stats: Map<String, Double>,
        val splash :String,
        val favorite: Boolean
)