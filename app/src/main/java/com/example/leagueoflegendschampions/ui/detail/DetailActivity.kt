package com.example.leagueoflegendschampions.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.databinding.ActivityDetailBinding
import com.example.leagueoflegendschampions.ui.commun.app
import com.example.leagueoflegendschampions.ui.commun.getViewModel

class DetailActivity : AppCompatActivity() {

    companion object{
        const val CHAMPION = "DetailActivity:champion"
    }
    private lateinit var binding: ActivityDetailBinding
    private lateinit var component: DetailActivityComponent
    private val  viewModel: DetailViewModel by lazy {
        getViewModel { component.detailViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = app.component.plus(DetailActivityModule(intent.getStringExtra(CHAMPION) ?: ""))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)


        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

    }
}