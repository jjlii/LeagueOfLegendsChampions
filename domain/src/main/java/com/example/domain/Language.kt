package com.example.domain

fun getLanguage(region : String, languages : List<String>): String{
    return languages.firstOrNull{
        it.contains(region)
    } ?: "en_US"
}