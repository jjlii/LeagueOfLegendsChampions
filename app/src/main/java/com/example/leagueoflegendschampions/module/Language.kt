package com.example.leagueoflegendschampions.module

class Language {


    companion object{

        private val languages = arrayListOf("en_US","cs_CZ","de_DE","el_GR","en_AU","en_GB",
                "en_PH","en_SG","es_AR","es_ES","es_MX","fr_FR","hu_HU","id_ID","it_IT","ja_JP","ko_KR",
                "pl_PL","pt_BR","ro_RO","ru_RU","th_TH","tr_TR","vn_VN","zh_CN","zh_MY","zh_TW")

        fun getLanguage(region : String): String{
            return languages.firstOrNull{
                it.contains(region)
            } ?: "en_US"
        }
    }
}