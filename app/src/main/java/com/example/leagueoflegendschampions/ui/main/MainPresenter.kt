package com.example.leagueoflegendschampions.ui.main

import android.view.View
import com.example.leagueoflegendschampions.module.Champion
import com.example.leagueoflegendschampions.module.ChampionRepository
import com.example.leagueoflegendschampions.ui.commun.Scope
import kotlinx.coroutines.launch

class MainPresenter(private val championRepository: ChampionRepository): Scope by Scope.Impl() {

    interface View{
        fun showProgress()
        fun hideProgress()
        fun updateData(champions: List<Champion>)
        fun navigateTo(champion: Champion)
    }

    private var view: View? = null

    fun onCreate(view: View) {
        initScope()
        this.view = view

        launch {
            view.showProgress()
            val championList = championRepository.getChampions()
            view.updateData(championList.data.values.toList())
            view.hideProgress()
        }
    }

    fun onDestroy() {
        cancelScope()
        this.view = null
    }

    fun onChampionClick(champion: Champion) {
        view?.navigateTo(champion)
    }
}