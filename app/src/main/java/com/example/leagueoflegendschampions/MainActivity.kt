package com.example.leagueoflegendschampions

import android.os.Bundle
import com.example.leagueoflegendschampions.databinding.ActivityMainBinding

class MainActivity : CoroutineScopeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}