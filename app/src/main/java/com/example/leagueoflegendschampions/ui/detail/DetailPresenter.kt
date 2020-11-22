package com.example.leagueoflegendschampions.ui.detail

import com.example.leagueoflegendschampions.module.Champion

class DetailPresenter {

    interface View{
        fun updateUI(champion: Champion?)
    }

    private var view: View? = null

    fun onCreate(view: View, champion: Champion?){
        this.view = view
        view.updateUI(champion)
    }

    fun onDestroy(){
        view = null
    }
}